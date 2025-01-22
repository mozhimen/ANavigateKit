package com.mozhimen.navigatek.provider

import com.mozhimen.navigatek.provider.impls.NavigateKProviderFacebook
import com.mozhimen.navigatek.provider.impls.NavigateKProviderTelegram
import com.mozhimen.navigatek.provider.impls.NavigateKProviderTwitter

/**
 * @ClassName NavigateKStart
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/29 1:10
 * @Version 1.0
 */
object NavigateKManager {
    val providerFacebook by lazy { NavigateKProviderFacebook() }
    val providerTwitter by lazy { NavigateKProviderTwitter() }
    val providerTelegram by lazy { NavigateKProviderTelegram() }
}