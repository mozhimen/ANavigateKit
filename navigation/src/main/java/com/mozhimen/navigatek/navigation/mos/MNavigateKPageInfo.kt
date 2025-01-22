package com.mozhimen.navigatek.navigation.mos

import java.io.Serializable

/**
 * @ClassName NavigateKPageInfo
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/10/12 23:17
 * @Version 1.0
 */
data class MNavigateKPageInfo(
    val destType: String,
    val id: Int,
    val clazzName: String
) : Serializable
