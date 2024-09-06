package com.mozhimen.navigatek.start.commons

import android.content.Intent
import com.mozhimen.kotlin.elemk.commons.IA_Listener

/**
 * @ClassName IStartBuilder
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/5 22:04
 * @Version 1.0
 */
interface IStartBuilder {
    fun addInterceptor(interceptor: IStartInterceptor): IStartBuilder
    fun addInterceptorAt(index: Int, interceptor: IStartInterceptor): IStartBuilder
    fun start(intent: Intent)
    fun startForResult(intent: Intent, requestCode: Int, callback: IA_Listener<Intent?>)
}