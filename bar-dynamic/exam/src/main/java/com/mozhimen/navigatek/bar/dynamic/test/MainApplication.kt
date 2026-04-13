package com.mozhimen.guidek

import android.app.Application
import com.mozhimen.navigatek.bar.dynamic.core.BarDynamic
import com.mozhimen.navigatek.bar.dynamic.core.BarDynamicMgr
import com.mozhimen.dynavbar.mos.GuideKPageInfo
import com.mozhimen.navigatek.bar.dynamic.core.mos.GuideKPkgConfig
import com.mozhimen.navigatek.bar.dynamic.core.mos.GuideKPkgPage
import com.mozhimen.guidek.fragments.HomeFragment
import com.mozhimen.uicorek.tabk.bottom.mos.TabKBottomMo

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //guidek
        BarDynamicMgr.instance.init(_guidekConfig)
    }

    private val _guidekConfig = GuideKPkgConfig(
        0,
        0,
        arrayListOf(
            GuideKPkgPage(
                GuideKPageInfo(
                    "com.mozhimen.guidek.fragments.HomeFragment",
                    "fragment",
                    0,
                    BarDynamic.getHashCode(HomeFragment::class.java),
                    "main/guidek/home"
                ),
                TabKBottomMo(
                    "首页",
                    "fonts/iconfont.ttf",
                    "&#xe98d;",
                    "&#xe98d;",
                    "#ff000000",
                    "#ff287FF1"
                )
            )
        )
    )
}