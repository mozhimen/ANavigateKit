package com.mozhimen.navigatek.bar

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mozhimen.kotlin.elemk.androidx.fragment.impls.FragmentsStoreProxy
import com.mozhimen.kotlin.elemk.commons.IA_Listener
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.androidx.fragment.UtilKFragmentManager
import com.mozhimen.kotlin.utilk.androidx.fragment.addAndHideFragments
import com.mozhimen.kotlin.utilk.androidx.fragment.findFragmentByTag
import com.mozhimen.kotlin.utilk.androidx.fragment.showAndHideFragments
import com.mozhimen.navigatek.bar.mos.FragmentDestination

/**
 * @ClassName NavigateKBarProxy
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/8
 * @Version 1.0
 */
@OApiInit_ByLazy
class NavigateKBarFragmentProxy : FragmentsStoreProxy() {
    private var _currentFragmentDestination: FragmentDestination? = null
    private var _fragmentDestinations: List<FragmentDestination> = ArrayList()

    fun putFragmentDestinations(destinations: List<FragmentDestination>) {
        _fragmentDestinations = destinations
    }

    fun getFragmentDestinations(): List<FragmentDestination> =
        _fragmentDestinations

    fun getFirstFragmentDestination(): FragmentDestination =
        _fragmentDestinations[0]

    fun setCurrentFragmentDestination(fragmentDestination: FragmentDestination) {
        _currentFragmentDestination = fragmentDestination
    }

    fun getCurrentFragmentDestination(): FragmentDestination? =
        _currentFragmentDestination

    @OptIn(OApiInit_ByLazy::class)
    @SuppressLint("CommitTransaction")
    fun showFragment(
        fragmentActivity: FragmentActivity,
        @IdRes fragmentContainerViewId: Int,
        fd: FragmentDestination,
        container: ViewGroup,
        onLastItemView: IA_Listener<View>? = null,
        onCurrItemView: IA_Listener<View>? = null,
    ) {
        if (getCurrentFragmentDestination() == fd) return//已经选中

        //last
        val lastFd = getCurrentFragmentDestination()
        var lastFragment: Fragment? = null
        if (lastFd != null) {
            val lastItemView = getItemView(container, lastFd)
            onLastItemView?.invoke(lastItemView)
            lastFragment = getFragment(fragmentActivity, lastFd)
        }

        //curr
        val currItemView: View = getItemView(container, fd)
        onCurrItemView?.invoke(currItemView)

        //frg
        var currFragment: Fragment? = getFragment(fragmentActivity, fd)
        if (null == currFragment) {
            currFragment = _fragments[fd.id] ?: run {
                fd.onInvokeFragment.invoke().also { _fragments[fd.id] = it }
            }
            fragmentActivity.addAndHideFragments(currFragment, fd.id, lastFragment, getCurrentFragmentDestination()?.id, fragmentContainerViewId)
        } else {
            fragmentActivity.showAndHideFragments(currFragment, fd.id, lastFragment, getCurrentFragmentDestination()?.id, fragmentContainerViewId)
        }
        setCurrentFragmentDestination(fd)
    }

    fun getFd(itemView: View): FragmentDestination =
        itemView.tag as FragmentDestination

    fun getItemView(container: ViewGroup, fd: FragmentDestination): View =
        container.findViewWithTag(fd)

    fun getFragment(fragmentActivity: FragmentActivity, fd: FragmentDestination): Fragment? =
        fragmentActivity.findFragmentByTag(fd.id)

    fun getVisibleFragment(
        fragmentActivity: FragmentActivity,
        @IdRes fragmentContainerViewId: Int,
    ): Fragment? =
        UtilKFragmentManager.findFragmentById(fragmentActivity, fragmentContainerViewId).also { v -> UtilKLogWrapper.d(TAG, "getVisibleFragment: $v tag ${v?.tag}") }
}