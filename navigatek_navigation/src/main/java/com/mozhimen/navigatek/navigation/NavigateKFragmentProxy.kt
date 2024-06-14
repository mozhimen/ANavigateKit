package com.mozhimen.navigatek.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.mozhimen.basick.lintk.optins.OApiInit_ByLazy
import com.mozhimen.basick.elemk.androidx.lifecycle.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.basick.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.basick.utilk.androidx.lifecycle.runOnMainThread
import com.mozhimen.basick.utilk.kotlin.UtilKLazyJVM.lazy_ofNone
import com.mozhimen.navigatek.navigation.helpers.getDestinationId
import com.mozhimen.navigatek.navigation.helpers.startDestinationId
import com.mozhimen.navigatek.navigation.mos.MNavigateKConfig

/**
 * @ClassName NavigateKProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
@OApiCall_BindLifecycle
@OApiInit_ByLazy
class NavigateKFragmentProxy<A>(
    private val _activity: FragmentActivity,
    private val _fragment: A,
    private val _containerId: Int,
    private val _classes: List<Class<*>>,
    private val _navigateKConfig: MNavigateKConfig = MNavigateKConfig(),
    private val _defaultDestinationId: Int = _classes.getOrNull(0)?.getDestinationId() ?: 0
) : BaseWakeBefDestroyLifecycleObserver() where A : Fragment, A : LifecycleOwner {
    ///////////////////////////////////////////////////////////////////////////////////////

    private val _navigateKFragment: NavigateKFragment by lazy_ofNone { NavigateKFragment(_activity, _fragment) }
    private var _navController: NavController? = null

    ///////////////////////////////////////////////////////////////////////////////////////

    val navController get() = _navController!!

    ///////////////////////////////////////////////////////////////////////////////////////

    init {
        _activity.runOnMainThread(::init)
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    fun startDestinationId(destinationId: Int) {
        _navController?.startDestinationId(destinationId)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        _navController = null
        super.onDestroy(owner)
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    private fun init() {
        _navController = _navigateKFragment.setNavigateKConfig(_navigateKConfig).setupNavGraph(_containerId, _classes)
    }
}