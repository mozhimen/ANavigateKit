package com.mozhimen.navigatek.navigation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.basick.utils.runOnMainThread
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone
import com.mozhimen.navigatek.navigation.helpers.getDestinationId
import com.mozhimen.navigatek.navigation.helpers.startDestinationId
import com.mozhimen.navigatek.navigation.mos.MNavigateKConfig

/**
 * @ClassName NavigateKActivityProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OApiCall_BindLifecycle
@OApiInit_ByLazy
class NavigateKActivityProxy<A>(
    private val _activity: A,
    private val _containerId: Int,
    private val _classes: List<Class<*>>,
    private val _navigateKConfig: MNavigateKConfig = MNavigateKConfig(),
    private val _defaultDestinationId: Int = _classes.getOrNull(0)?.getDestinationId() ?: 0
) : BaseWakeBefDestroyLifecycleObserver() where A : FragmentActivity, A : LifecycleOwner {
    ///////////////////////////////////////////////////////////////////////////////////////

    private val _navigateKActivity: NavigateKActivity by lazy_ofNone { NavigateKActivity(_activity) }
    private var _navController: NavController? = null

    ///////////////////////////////////////////////////////////////////////////////////////

    val navController get() = _navController!!

    ///////////////////////////////////////////////////////////////////////////////////////

    init {
        _activity.runOnMainThread{
            init()
        }
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
        _navController = _navigateKActivity.setNavigateKConfig(_navigateKConfig).setupNavGraph(_containerId, _classes)
    }
}