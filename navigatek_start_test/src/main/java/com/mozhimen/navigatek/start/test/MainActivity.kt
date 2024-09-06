package com.mozhimen.navigatek.start.test

import android.os.Bundle
import android.util.Log
import com.mozhimen.kotlin.utilk.android.app.getIntent
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.mvvmk.bases.activity.viewbinding.BaseActivityVB
import com.mozhimen.navigatek.start.test.databinding.ActivityMainBinding
import com.mozhimen.navigatek.start.utils.startForResult

class MainActivity : BaseActivityVB<ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        vb.btnJumpNextPageParams.setOnClickListener {
            startContext<NextActivity> {
                putExtra("params", "测试参数数据")
            }
        }

        //跳转到下一页并回传数据
        vb.btnJumpNextPage.setOnClickListener {
            startForResult(
                getIntent<NextActivity> {
                    putExtra("message", "我是上个界面传入的数据")
                },
                1000,
                onResult = { data ->
                    val message = data?.getStringExtra("message")
                    Log.d(TAG, "message=$message")
                })
        }

        //跳转到下一页(必须登录或者...)并回传数据
        vb.btnJumpNextPageNeedLogin.setOnClickListener {
            com.mozhimen.navigatek.start.NavigateKStart.with(this)
                .addInterceptor(object : com.mozhimen.navigatek.start.commons.IStartInterceptor {
                    //添加登录判断拦截
                    override fun intercept(chain: com.mozhimen.navigatek.start.commons.IStartChain) {
                        //这里就直接跳转登录了，实际使用时可以判断是否登录，如果登录直接继续后续操作chain.proceed()，否则跳转登录
                        startForResult(
                            getIntent<LoginActivity>(),
                            1001,
                            onResult = { data ->
                                val message = data?.getStringExtra("message")
                                Log.d(TAG, "message=$message")
                                chain.next()
                            }
                        )
                    }
                })
                .startForResult(
                    getIntent<NextActivity> {
                        putExtra("message", "我是上个界面传入的数据")
                    },
                    1002,
                    callback = { data ->
                        val message = data?.getStringExtra("message")
                        Log.d(TAG, "message=$message")
                    }
                )
        }
    }
}