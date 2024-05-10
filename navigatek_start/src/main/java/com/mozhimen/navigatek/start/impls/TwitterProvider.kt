package com.mozhimen.navigatek.start.impls

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mozhimen.basick.utilk.android.content.startContext
import com.mozhimen.navigatek.start.commons.IProvider


/**
 * @ClassName TwitterProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:44
 * @Version 1.0
 */
object TwitterProvider : IProvider {
    override val PACKAGE_NAME: String
        get() = "com.twitter.android"

    @JvmStatic
    fun startContext(context: Context, id: String, name: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$name"))
            intent.setPackage(PACKAGE_NAME) // 指定要使用Twitter应用打开链接
            context.startContext(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            // 处理Twitter应用未安装的情况
            // 可以在这里打开网页版Twitter或提示用户安装Twitter应用
            try {
                //activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterName)));
                context.startContext(Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=$id")))
            } catch (e: ActivityNotFoundException) {
                try {
                    context.startContext(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$name")))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}