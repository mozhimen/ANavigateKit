package com.mozhimen.navigatek.start.impls

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mozhimen.kotlin.utilk.android.content.UtilKIntentWrapper
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.android.content.startContext_throw
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.navigatek.start.commons.IStartProvider


/**
 * @ClassName TelegramProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:54
 * @Version 1.0
 */
object StartProviderTelegram : IStartProvider {
    override val PACKAGE_NAME: String
        get() = "org.telegram.messenger"

    @JvmStatic
    fun startContext(context: Context, name: String) {
        var intent: Intent
        try {
            intent = UtilKIntentWrapper.getViewStrUrl("https://t.me/$name").apply { setPackage(PACKAGE_NAME) }// 指定要使用Telegram应用打开链接
            context.startContext_throw(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "startContext: ", e)
            // 处理Telegram应用未安装的情况// 可以在这里打开网页版Telegram或提示用户安装Telegram应用
            intent = UtilKIntentWrapper.getViewStrUrl("https://t.me/$name")
            context.startContext(intent)
        }
    }
}