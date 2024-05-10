package com.mozhimen.navigatek.start

import com.mozhimen.navigatek.start.impls.FacebookProvider
import com.mozhimen.navigatek.start.impls.TelegramProvider
import com.mozhimen.navigatek.start.impls.TwitterProvider

/**
 * @ClassName NavigateKStart
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:10
 * @Version 1.0
 */
object NavigateKStart {
    fun getFaceBook(): FacebookProvider = FacebookProvider
    fun getTwitter(): TwitterProvider = TwitterProvider
    fun getTelegram(): TelegramProvider = TelegramProvider
}