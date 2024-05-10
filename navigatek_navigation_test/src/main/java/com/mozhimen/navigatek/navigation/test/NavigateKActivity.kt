package com.mozhimen.navigatek.navigation.test

import android.os.Bundle
import androidx.navigation.ui.NavigationUI
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB
import com.mozhimen.basick.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.basick.lintk.optins.OApiInit_ByLazy
import com.mozhimen.navigatek.navigation.NavigateKActivityProxy
import com.mozhimen.navigatek.navigation.mos.MNavigateKConfig
import com.mozhimen.navigatek.navigation.test.databinding.ActivityNavigatekBinding

@OptIn(OApiCall_BindLifecycle::class, OApiInit_ByLazy::class)
class NavigateKActivity : BaseActivityVDB<ActivityNavigatekBinding>() {

    private val _fragments = listOf(FirstFragment::class.java, SecondFragment::class.java)
    private val _navigateKActivityProxy by lazy {
        NavigateKActivityProxy(this, R.id.navigatek_fragment_container, _fragments, MNavigateKConfig(isFragmentShowHide = true))//show_hide方式
//        NavigateKProxy(this, R.id.navigatek_fragment_container, _fragments)
    }

    val navigateKProxy get() = _navigateKActivityProxy

    override fun initView(savedInstanceState: Bundle?) {
//        setSupportActionBar(vdb.navigatekToolbar)//如果没有toolbar
        _navigateKActivityProxy.bindLifecycle(this)
        NavigationUI.setupActionBarWithNavController(this, _navigateKActivityProxy.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return _navigateKActivityProxy.navController.navigateUp()
    }
}