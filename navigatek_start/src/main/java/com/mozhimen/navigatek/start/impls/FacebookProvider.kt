package com.mozhimen.navigatek.start.impls

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.mozhimen.basick.utilk.android.util.UtilKLogWrapper
import com.mozhimen.basick.elemk.android.content.cons.CIntent
import com.mozhimen.basick.utilk.android.content.UtilKPackageManager
import com.mozhimen.basick.utilk.android.content.startContext
import com.mozhimen.basick.utilk.commons.IUtilK
import com.mozhimen.navigatek.start.commons.IProvider


/**
 * @ClassName Facebook
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:14
 * @Version 1.0
 */
object FacebookProvider : IProvider, IUtilK {
    override val PACKAGE_NAME = "com.facebook.katana"

    /////////////////////////////////////////////////////////////

    @JvmStatic
    fun startContext(context: Context) {
        var intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        if (intent == null) {
            intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse("http://facebook.com/")// 打开url
            context.startContext(intent)
        } else {
            /* intent.flags =
                 Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_CLEAR_TOP*/
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
        try {
            context.startContext(Intent(CIntent.ACTION_VIEW, Uri.parse(getFacebookDetailURL(context, strPageUrl).also { UtilKLogWrapper.d(TAG, "startContext: getFacebookDetailURL $it") })))
        } catch (e: Exception) {
            e.printStackTrace()
            // 处理Facebook应用未安装的情况
            // 可以在这里打开网页版Facebook或提示用户安装Facebook应用
            try {
                val intent = Intent(CIntent.ACTION_VIEW, Uri.parse("fb://page/$id"))
                intent.setPackage(PACKAGE_NAME) // 指定要使用Facebook应用打开链接
                context.startContext(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    context.startContext(Intent(Intent.ACTION_VIEW, Uri.parse(strPageUrl)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return strPageUrl
    }
}