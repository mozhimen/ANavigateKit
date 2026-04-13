package com.mozhimen.navigatek.bar.dynamic.core.mos

import java.io.Serializable

/**
 * @ClassName Destination
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/4/29 22:10
 * @Version 1.0
 */
internal data class DynamicDestination(
    val versionCode: Int,
    val indexDefault: Int,
    val pages: List<DynamicPage>
) : Serializable

internal data class DynamicPage(
    val enable: Boolean,
    val pageInfo: DynamicPageInfo,
    val tabConfig: DynamicTabConfig,
    val title: String
) : Serializable

data class DynamicPageInfo(
    val clazzName: String,
    val destType: String,
    val index: Int,
    val id: Int,
    val url: String
) : Serializable

internal data class DynamicTabConfig(
    val name: String,
    val bitmapDefault: String,
    val bitmapSelected: String,
    val iconColorDefault: String,
    val iconColorSelected: String,
    val iconFont: String,
    val iconNameDefault: String,
    val iconNameSelected: String,
    val type: String
) : Serializable