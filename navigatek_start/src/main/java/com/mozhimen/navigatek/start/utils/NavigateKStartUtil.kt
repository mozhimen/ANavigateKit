package com.mozhimen.navigatek.start.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.mozhimen.kotlin.elemk.androidx.fragment.InvisibleProxyFragment_ofAndroid
import com.mozhimen.kotlin.elemk.androidx.fragment.InvisibleProxyFragment_ofAndroidx
import com.mozhimen.kotlin.elemk.commons.IA_Listener

/**
 * @ClassName NavigateKStartUtil
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/5 22:30
 * @Version 1.0
 */
fun androidx.fragment.app.Fragment.startForResult(
    intent: Intent,
    requestCode: Int,
    onResult: IA_Listener<Intent?>
) {
    NavigateKStartUtil.startForResult(this, intent, requestCode, onResult)
}

fun android.app.Fragment.startForResult(
    intent: Intent,
    requestCode: Int,
    onResult: IA_Listener<Intent?>
) {
    NavigateKStartUtil.startForResult(this, intent, requestCode, onResult)
}

fun Activity.startForResult(
    intent: Intent,
    requestCode: Int,
    onResult: IA_Listener<Intent?>
) {
    NavigateKStartUtil.startForResult(this, intent, requestCode, onResult)
}


//////////////////////////////////////////////////////////////

object NavigateKStartUtil {
    @JvmStatic
    fun startForResult(
        fragment: androidx.fragment.app.Fragment,
        intent: Intent,
        requestCode: Int,
        onResult: IA_Listener<Intent?>,
    ) {
        InvisibleProxyFragment_ofAndroidx.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroidx>(
            fragmentManager = fragment.childFragmentManager,
            onAction = { frg ->
                frg.startActivityForResult(intent, requestCode)
            },
            onResult = requestCode to onResult,
            null
        )
    }

    @JvmStatic
    fun startForResult(
        fragment: android.app.Fragment,
        intent: Intent,
        requestCode: Int,
        onResult: IA_Listener<Intent?>,
    ) {
        InvisibleProxyFragment_ofAndroid.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroid>(
            fragmentManager = fragment.childFragmentManager,
            onAction = { frg ->
                frg.startActivityForResult(intent, requestCode)
            },
            onResult = requestCode to onResult,
            null
        )
    }

    /**
     * Activity startForResult
     */
    @JvmStatic
    fun startForResult(
        activity: Activity,
        intent: Intent,
        requestCode: Int,
        onResult: IA_Listener<Intent?>,
    ) {
        //FragmentActivity
        if (activity is FragmentActivity) {
            InvisibleProxyFragment_ofAndroidx.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroidx>(
                fragmentManager = activity.supportFragmentManager,
                onAction = { frg ->
                    frg.startActivityForResult(intent, requestCode)
                },
                onResult = requestCode to onResult,
                null
            )
        } else {//Activity
            InvisibleProxyFragment_ofAndroid.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroid>(
                fragmentManager = activity.fragmentManager,
                onAction = { frg ->
                    frg.startActivityForResult(intent, requestCode)
                },
                onResult = requestCode to onResult,
                null
            )
        }
    }

}