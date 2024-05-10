package com.mozhimen.navigatek.start.impls

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mozhimen.basick.utilk.android.content.startContext
import com.mozhimen.navigatek.start.commons.IProvider


/**
 * @ClassName TelegramProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:54
 * @Version 1.0
 */
object TelegramProvider : IProvider {
    override val PACKAGE_NAME: String
        get() = "org.telegram.messenger"

    @JvmStatic
    fun startContext(context: Context, name: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$name"))
            intent.setPackage(PACKAGE_NAME) // 指定要使用Telegram应用打开链接
            context.startContext(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            // 处理Telegram应用未安装的情况
            // 可以在这里打开网页版Telegram或提示用户安装Telegram应用
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$name"))
            context.startContext(intent)
        }
    }
}