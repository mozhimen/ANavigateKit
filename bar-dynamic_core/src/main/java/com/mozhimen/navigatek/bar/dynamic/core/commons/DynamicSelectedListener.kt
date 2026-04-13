package com.mozhimen.navigatek.bar.dynamic.core.commons

import com.mozhimen.navigatek.bar.dynamic.core.mos.DynamicPageInfo
import com.mozhimen.xmlk.layoutk.tab.bottom.mos.MTabBottom

/**
 * @ClassName GuideKSelectedListener
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/5/3 14:54
 * @Version 1.0
 */
interface DynamicSelectedListener {
    fun onSelected(
        index: Int,
        pageInfo: DynamicPageInfo,
        prevMo: MTabBottom?,
        nextMo: MTabBottom
    )
}