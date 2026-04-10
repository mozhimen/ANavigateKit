package com.mozhimen.dynavbar.commons

import com.mozhimen.dynavbar.mos.GuideKPageInfo
import com.mozhimen.uicorek.tabk.bottom.mos.TabKBottomMo

/**
 * @ClassName GuideKSelectedListener
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/5/3 14:54
 * @Version 1.0
 */
interface GuideKSelectedListener {
    fun onSelected(
        index: Int,
        pageInfo: GuideKPageInfo,
        prevMo: TabKBottomMo?,
        nextMo: TabKBottomMo
    )
}