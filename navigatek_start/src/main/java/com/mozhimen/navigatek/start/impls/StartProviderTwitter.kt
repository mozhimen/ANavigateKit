package com.mozhimen.navigatek.start.impls

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mozhimen.basick.utilk.android.content.UtilKIntentWrapper
import com.mozhimen.basick.utilk.android.content.startContext
import com.mozhimen.basick.utilk.android.content.startContext_throw
import com.mozhimen.basick.utilk.android.util.UtilKLogWrapper
import com.mozhimen.navigatek.start.commons.IStartProvider


/**
 * @ClassName TwitterProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:44
 * @Version 1.0
 */
object StartProviderTwitter : IStartProvider {
    override val PACKAGE_NAME: String
        get() = "com.twitter.android"

    @JvmStatic
    fun startContext(context: Context, id: String, name: String) {
        var intent: Intent
        try {
            intent = UtilKIntentWrapper.getViewStrUrl("https://twitter.com/$name").apply { setPackage(PACKAGE_NAME) }// 指定要使用Twitter应用打开链接
            context.startContext_throw(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "startContext: ", e)
            // 处理Twitter应用未安装的情况// 可以在这里打开网页版Twitter或提示用户安装Twitter应用
            try {
                intent = UtilKIntentWrapper.getViewStrUrl("twitter://user?user_id=$id")//activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterName)));
                context.startContext_throw(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                UtilKLogWrapper.e(TAG, "startContext: ", e)
                //
                intent = UtilKIntentWrapper.getViewStrUrl("https://twitter.com/$name")
                context.startContext(intent)
            }
        }
    }
}