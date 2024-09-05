package com.mozhimen.navigatek.start.test.chain

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.mozhimen.kotlin.elemk.androidx.fragment.InvisibleProxyFragment_ofAndroid
import com.mozhimen.kotlin.elemk.androidx.fragment.InvisibleProxyFragment_ofAndroidx
import com.mozhimen.kotlin.elemk.androidx.fragment.startInvisibleProxyFragment
import com.mozhimen.kotlin.elemk.commons.IA_Listener

fun androidx.fragment.app.Fragment.startForResult(
    intent: Intent,
    requestCode: Int,
    callBack: IA_Listener<Intent?>,
) {
    startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroidx>(
        fragmentManager = this.childFragmentManager,
        onAction = { frg ->
            frg.startActivityForResult(intent, requestCode)
        },
        onResult = requestCode to callBack,
        null
    )
}

fun android.app.Fragment.startForResult(
    intent: Intent,
    requestCode: Int,
    callBack: IA_Listener<Intent?>,
) {
    startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroid>(
        fragmentManager = this.childFragmentManager,
        onAction = { frg ->
            frg.startActivityForResult(intent, requestCode)
        },
        onResult = requestCode to callBack,
        null
    )
}

/**
 * Activity startForResult
 */
fun Activity.startForResult(
    intent: Intent,
    requestCode: Int,
    callBack: IA_Listener<Intent?>,
) {
    //FragmentActivity
    if (this is FragmentActivity) {
        startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroidx>(
            fragmentManager = this.supportFragmentManager,
            onAction = { frg ->
                frg.startActivityForResult(intent, requestCode)
            },
            onResult = requestCode to callBack,
            null
        )
    } else {//Activity
        startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroid>(
            fragmentManager = this.fragmentManager,
            onAction = { frg ->
                frg.startActivityForResult(intent, requestCode)
            },
            onResult = requestCode to callBack,
            null
        )
    }
}




