package com.mozhimen.navigatek.start.commons

/**
 * @ClassName IPageInterceptor
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/5
 * @Version 1.0
 */
interface IStartInterceptor {
    fun intercept(chain: IStartChain)
}