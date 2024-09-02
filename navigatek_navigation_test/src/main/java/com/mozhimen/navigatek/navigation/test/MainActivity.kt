package com.mozhimen.navigatek.navigation.test

import android.view.View
import com.mozhimen.kotlin.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.navigatek.navigation.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {

    fun goNavigateK(view: View) {
        startContext<NavigateKActivity>()
    }
}