package com.mozhimen.navigatek.start.test.chain

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.mozhimen.kotlin.utilk.android.content.UtilKIntent
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.navigatek.start.test.LoginActivity
import com.mozhimen.navigatek.start.test.NextActivity
import com.mozhimen.navigatek.start.test.commons.IPageChain
import com.mozhimen.navigatek.start.test.commons.IPageInterceptor

object JumpHelper : IUtilK {

    /**
     * 跳转到下一页并回传数据
     */
    fun jumpNextPageForResult(activity: FragmentActivity) {
        activity.startForResult(
            UtilKIntent.get<NextActivity>(activity) {
                putExtra("message", "我是上个界面传入的数据")
            }, 1000
        ) { data ->
            val message = data?.getStringExtra("message")
            Log.d(TAG, "message=$message")
        }
    }


    fun jumpNextPageLoginForResult(activity: FragmentActivity) {
        ActivityStarter.with(activity)
            .addInterceptor(object : IPageInterceptor {
                //添加登录判断拦截
                override fun intercept(chain: IPageChain) {
                    //这里就直接跳转登录了，实际使用时可以判断是否登录，如果登录直接继续后续操作chain.proceed()，否则跳转登录
                    activity.startForResult(UtilKIntent.get<LoginActivity>(activity), 1001) { data ->
                        val message = data?.getStringExtra("message")
                        Log.d(TAG, "message=$message")
                        chain.start()
                    }
                }
            })
            .startForResult(UtilKIntent.get<NextActivity>(activity) {
                putExtra("message", "我是上个界面传入的数据")
            }, 1002) { data ->
                val message = data?.getStringExtra("message")
                Log.d(TAG, "message=$message")
            }
    }
}