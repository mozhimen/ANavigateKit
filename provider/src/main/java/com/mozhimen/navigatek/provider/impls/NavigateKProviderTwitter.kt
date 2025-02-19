package com.mozhimen.navigatek.provider.impls

import android.content.Context
import android.content.Intent
import com.mozhimen.kotlin.utilk.android.content.UtilKIntentGet
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.android.content.startContext_throw
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.navigatek.provider.commons.INavigateKData
import com.mozhimen.navigatek.provider.commons.INavigateKProvider


/**
 * @ClassName TwitterProvider
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:44
 * @Version 1.0
 */
class NavigateKProviderTwitter : INavigateKProvider<NavigateKProviderTwitter.NavigateKDataTwitter> {
    class NavigateKDataTwitter(val id: String, val name: String) : INavigateKData

    override fun getPackageName(): String {
        return "com.twitter.android"
    }

    override fun start(context: Context, data: NavigateKDataTwitter) {
        var intent: Intent
        try {
            intent = UtilKIntentGet.getIntent_ACTION_VIEW("https://twitter.com/${data.name}").apply { setPackage(getPackageName()) }// 指定要使用Twitter应用打开链接
            context.startContext_throw(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "startContext: ", e)
            // 处理Twitter应用未安装的情况// 可以在这里打开网页版Twitter或提示用户安装Twitter应用
            try {
                intent = UtilKIntentGet.getIntent_ACTION_VIEW("twitter://user?user_id=${data.id}")//activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterName)));
                context.startContext_throw(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                UtilKLogWrapper.e(TAG, "startContext: ", e)
                //
                intent = UtilKIntentGet.getIntent_ACTION_VIEW("https://twitter.com/${data.name}")
                context.startContext(intent)
            }
        }
    }
}