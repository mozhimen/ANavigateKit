package com.mozhimen.navigatek.start

import com.mozhimen.navigatek.start.impls.StartProviderFacebook
import com.mozhimen.navigatek.start.impls.StartProviderTelegram
import com.mozhimen.navigatek.start.impls.StartProviderTwitter

/**
 * @ClassName NavigateKStart
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:10
 * @Version 1.0
 */
object NavigateKStart {
    fun getFaceBook(): StartProviderFacebook = StartProviderFacebook
    fun getTwitter(): StartProviderTwitter = StartProviderTwitter
    fun getTelegram(): StartProviderTelegram = StartProviderTelegram
}