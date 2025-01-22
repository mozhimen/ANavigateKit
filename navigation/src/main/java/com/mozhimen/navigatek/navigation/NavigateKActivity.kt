package com.mozhimen.navigatek.navigation

import androidx.fragment.app.FragmentActivity
import com.mozhimen.navigatek.navigation.commons.INavigateK
import com.mozhimen.navigatek.navigation.helpers.NavigateKActivityDelegate


/**
 * @ClassName NavigateK
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
class NavigateKActivity(activity: FragmentActivity) : INavigateK by NavigateKActivityDelegate(activity)