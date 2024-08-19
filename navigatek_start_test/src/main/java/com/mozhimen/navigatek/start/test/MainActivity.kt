package com.mozhimen.navigatek.start.test

import android.view.View
import com.mozhimen.basick.elemk.androidx.appcompat.bases.viewbinding.BaseActivityVB
import com.mozhimen.navigatek.start.NavigateKStart
import com.mozhimen.navigatek.start.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVB<ActivityMainBinding>() {

    fun goFacebook(view: View) {
        NavigateKStart.getFaceBook().startContext(this,"61555556888507","profile.php?id=61555556888507")
    }

    fun goTelegram(view: View) {
        NavigateKStart.getTelegram().startContext(this, "lelejoy_top")
    }

    fun goTwitter(view: View) {
        NavigateKStart.getTwitter().startContext(this, "LeLeJoy_com", "LeLeJoy_com")
    }
}