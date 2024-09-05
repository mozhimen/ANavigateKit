package com.mozhimen.navigatek.provider.impls

import android.content.Context
import android.content.Intent
import com.mozhimen.kotlin.utilk.android.content.UtilKIntentWrapper
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.android.content.startContext_throw
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.navigatek.provider.commons.INavigateKData
import com.mozhimen.navigatek.provider.commons.INavigateKProvider


/**
 * @ClassName TelegramProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:54
 * @Version 1.0
 */
class NavigateKProviderTelegram : INavigateKProvider<NavigateKProviderTelegram.NavigateKDataTelegram> {
    class NavigateKDataTelegram(val name: String) : INavigateKData

    ////////////////////////////////////////////////////////////

    override fun getPackageName(): String {
        return "org.telegram.messenger"
    }

    override fun start(context: Context, data: NavigateKDataTelegram) {
        var intent: Intent
        try {
            intent = UtilKIntentWrapper.getViewStrUrl("https://t.me/${data.name}").apply { setPackage(getPackageName()) }// 指定要使用Telegram应用打开链接
            context.startContext_throw(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "startContext: ", e)
            // 处理Telegram应用未安装的情况// 可以在这里打开网页版Telegram或提示用户安装Telegram应用
            intent = UtilKIntentWrapper.getViewStrUrl("https://t.me/${data.name}")
            context.startContext(intent)
        }
    }
}