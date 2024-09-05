package com.mozhimen.navigatek.provider.test

import android.view.View
import com.mozhimen.mvvmk.bases.activity.viewbinding.BaseActivityVB
import com.mozhimen.navigatek.provider.NavigateKManager
import com.mozhimen.navigatek.provider.impls.NavigateKProviderFacebook
import com.mozhimen.navigatek.provider.impls.NavigateKProviderTelegram
import com.mozhimen.navigatek.provider.impls.NavigateKProviderTwitter
import com.mozhimen.navigatek.provider.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVB<ActivityMainBinding>() {
    fun goFacebook(view: View) {
        NavigateKManager.providerFacebook.start(this, NavigateKProviderFacebook.NavigateKDataFacebook("61555556888507", "profile.php?id=61555556888507"))
    }

    fun goTelegram(view: View) {
        NavigateKManager.providerTelegram.start(this, NavigateKProviderTelegram.NavigateKDataTelegram("lelejoy_top"))
    }

    fun goTwitter(view: View) {
        NavigateKManager.providerTwitter.start(this, NavigateKProviderTwitter.NavigateKDataTwitter("LeLeJoy_com", "LeLeJoy_com"))
    }
}