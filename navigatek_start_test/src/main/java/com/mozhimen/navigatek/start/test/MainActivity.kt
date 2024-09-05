package com.mozhimen.navigatek.start.test

import android.os.Bundle
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.mvvmk.bases.activity.viewbinding.BaseActivityVB
import com.mozhimen.navigatek.start.test.chain.JumpHelper
import com.mozhimen.navigatek.start.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVB<ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        vb.btnJumpNextPageParams.setOnClickListener {
            startContext<NextActivity> {
                putExtra("params", "测试参数数据")
            }
        }

        //跳转到下一页并回传数据
        vb.btnJumpNextPage.setOnClickListener {
            JumpHelper.jumpNextPageForResult(this)
        }

        //跳转到下一页(必须登录或者...)并回传数据
        vb.btnJumpNextPageNeedLogin.setOnClickListener {
            JumpHelper.jumpNextPageLoginForResult(this)
        }
    }
}