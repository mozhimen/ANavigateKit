package com.mozhimen.navigatek.start.impls

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.mozhimen.basick.utilk.android.util.UtilKLogWrapper
import com.mozhimen.basick.utilk.android.content.UtilKIntentWrapper
import com.mozhimen.basick.utilk.android.content.UtilKPackageManager
import com.mozhimen.basick.utilk.android.content.startContext
import com.mozhimen.basick.utilk.android.content.startContext_throw
import com.mozhimen.basick.utilk.commons.IUtilK
import com.mozhimen.navigatek.start.commons.IStartProvider


/**
 * @ClassName Facebook
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:14
 * @Version 1.0
 */
object StartProviderFacebook : IStartProvider, IUtilK {
    override val PACKAGE_NAME = "com.facebook.katana"

    /////////////////////////////////////////////////////////////

    @JvmStatic
    fun startContext(context: Context) {
        var intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        if (intent != null) {
            /* intent.flags =
     Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_CLEAR_TOP*/
            context.startContext(intent)
        } else {
            intent = UtilKIntentWrapper.getViewStrUrl("http://facebook.com/")// 打开url
            context.startContext(intent)
        }
    }

    /**
     * In Facebook version 11.0.0.11.23 (3002850) fb://profile/ and fb://page/ no longer work. I decompiled the Facebook app and found that you can use fb://facewebmodal/f?href=[YOUR_FACEBOOK_PAGE]. Here is the method I have been using in production:
     * @param context Context
     * @param facebookPage String
     */
    @JvmStatic
    fun startContext(context: Context, id: String, name: String) {
        val strPageUrl = "http://www.facebook.com/${name}"
        var intent: Intent
        try {
            intent = UtilKIntentWrapper.getViewStrUrl(getFacebookDetailURL(context, strPageUrl).also { UtilKLogWrapper.d(TAG, "startContext: getFacebookDetailURL $it") })
            context.startContext_throw(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            UtilKLogWrapper.e(TAG, "startContext: 1", e)
            // 处理Facebook应用未安装的情况// 可以在这里打开网页版Facebook或提示用户安装Facebook应用
            try {
                intent = UtilKIntentWrapper.getViewStrUrl("fb://page/$id").apply { setPackage(PACKAGE_NAME) }// 指定要使用Facebook应用打开链接
                context.startContext_throw(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                UtilKLogWrapper.e(TAG, "startContext: 2", e)
                //
                intent = UtilKIntentWrapper.getViewStrUrl(strPageUrl)
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
            val applicationInfo = UtilKPackageManager.getApplicationInfo(context, PACKAGE_NAME)
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