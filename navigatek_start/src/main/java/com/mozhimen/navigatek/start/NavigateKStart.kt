package com.mozhimen.navigatek.start

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.mozhimen.kotlin.elemk.commons.IABC_Listener

/**
 * @ClassName NavigateKStart
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/5
 * @Version 1.0
 */
object NavigateKStart {
    @JvmStatic
    fun Activity.startForResult(
        activity: Activity,
        requestCode: Int, intent: Intent,
        onResult: IABC_Listener<Int, Int, Intent?>,
    ) {
        //FragmentActivity
        if (activity is FragmentActivity) {
            val fragment = XFragmentForResult()
            fragment.intent = intent
            fragment.requestCode = requestCode
            fragment.onActivityResult = { requestCode, resultCode, data ->
                onResult?.invoke(requestCode, resultCode, data)
                if (fragment.isAdded) {
                    supportFragmentManager.beginTransaction().remove(fragment)
                }
            }
            supportFragmentManager.beginTransaction().apply {
                if (fragment.isAdded) {
                    remove(fragment)
                }
                add(fragment, fragment.toString())
            }.commitNowAllowingStateLoss()
            supportFragmentManager.executePendingTransactions()
        } else {//Activity
            val fragment = FragmentForResult()
            fragment.intent = intent
            fragment.requestCode = requestCode
            fragment.onActivityResult = { requestCode, resultCode, data ->
                onResult?.invoke(requestCode, resultCode, data)
                if (fragment.isAdded) {
                    fragmentManager.beginTransaction().remove(fragment)
                }
            }
            fragmentManager.beginTransaction().apply {
                if (fragment.isAdded) {
                    remove(fragment)
                }
                add(fragment, fragment.toString())
            }.commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
    }
}