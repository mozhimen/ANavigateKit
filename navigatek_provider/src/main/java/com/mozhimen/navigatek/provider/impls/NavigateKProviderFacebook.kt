package com.mozhimen.navigatek.provider.impls

import android.content.Context
import android.content.Intent
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.android.content.UtilKIntentGet
import com.mozhimen.kotlin.utilk.android.content.UtilKPackageManager
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.android.content.startContext_throw
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.navigatek.provider.commons.INavigateKData
import com.mozhimen.navigatek.provider.commons.INavigateKProvider


/**
 * @ClassName Facebook
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:14
 * @Version 1.0
 */
class NavigateKProviderFacebook : INavigateKProvider<NavigateKProviderFacebook.NavigateKDataFacebook>, IUtilK {

    class NavigateKDataFacebook(val id: String, val name: String) : INavigateKData

    /////////////////////////////////////////////////////////////

    override fun getPackageName(): String {
        return "com.facebook.katana"
    }

    fun start(context: Context) {
        var intent = context.packageManager.getLaunchIntentForPackage(getPackageName())
        if (intent != null) {
            /* intent.flags =
     Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_CLEAR_TOP*/
            context.startContext(intent)
        } else {
            intent = UtilKIntentGet.getViewStrUrl("http://facebook.com/")// 打开url
            context.startContext(intent)
        }
    }

    /**
     * In Facebook version 11.0.0.11.23 (3002850) fb://profile/ and fb://page/ no longer work. I decompiled the Facebook app and found that you can use fb://facewebmodal/f?href=[YOUR_FACEBOOK_PAGE]. Here is the method I have been using in production:
     * @param context Context
     * @param facebookPage String
     */
    override fun start(context: Context, data: NavigateKDataFacebook) {
        val strPageUrl = "http://www.facebook.com/${data.name}"
        var intent: Intent
        try {
            intent = UtilKIntentGet.getViewStrUrl(getFacebookDetailURL(context, strPageUrl).also { UtilKLogWrapper.d(TAG, "startContext: getFacebookDetailURL $it") })
            context.startContext_throw(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "startContext: 1", e)
            // 处理Facebook应用未安装的情况// 可以在这里打开网页版Facebook或提示用户安装Facebook应用
            try {
                intent = UtilKIntentGet.getViewStrUrl("fb://page/${data.id}").apply { setPackage(getPackageName()) }// 指定要使用Facebook应用打开链接
                context.startContext_throw(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                UtilKLogWrapper.e(TAG, "startContext: 2", e)
                //
                intent = UtilKIntentGet.getViewStrUrl(strPageUrl)
                context.startContext(intent)
            }
        }
    }

    /////////////////////////////////////////////////////////////

    /**
     * 直接链接
     */
    private fun getFacebookDetailURL(context: Context, strPageUrl: String): String {
        try {
            val applicationInfo = UtilKPackageManager.getApplicationInfo(context, getPackageName())
            if (applicationInfo.enabled/*applicationInfo() >= 3002850*/) { //newer versions of fb app
                return "fb://facewebmodal/f?href=$strPageUrl"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "getFacebookDetailURL: ", e)
        }
        return strPageUrl
    }
}