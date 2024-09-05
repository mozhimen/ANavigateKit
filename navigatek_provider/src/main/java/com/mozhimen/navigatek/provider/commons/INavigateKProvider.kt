package com.mozhimen.navigatek.provider.commons

import android.content.Context
import com.mozhimen.kotlin.utilk.commons.IUtilK

/**
 * @ClassName IProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:16
 * @Version 1.0
 */
interface INavigateKProvider<T : INavigateKData> : IUtilK {
    fun getPackageName(): String
    fun start(context: Context, data: T)
}