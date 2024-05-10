package com.mozhimen.navigatek.navigation.bases

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigator
import com.mozhimen.basick.utilk.commons.IUtilK
import com.mozhimen.navigatek.navigation.bases.BaseFragmentDestination

/**
 * @ClassName BaseFragmentNavigator
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/7/25 22:36
 * @Version 1.0
 */
abstract class BaseFragmentNavigator(
    protected val context: Context,
    protected val fragmentManager: FragmentManager,
    protected val containerId: Int
) : Navigator<BaseFragmentDestination>(), IUtilK