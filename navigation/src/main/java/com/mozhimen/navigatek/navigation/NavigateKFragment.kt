package com.mozhimen.navigatek.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mozhimen.navigatek.navigation.commons.INavigateK
import com.mozhimen.navigatek.navigation.helpers.NavigateKFragmentDelegate


/**
 * @ClassName NavigateK
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
class NavigateKFragment(activity: FragmentActivity, fragment: Fragment) : INavigateK by NavigateKFragmentDelegate(activity, fragment)