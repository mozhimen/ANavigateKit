package com.mozhimen.navigatek.start.test.commons

/**
 * @ClassName IPageInterceptor
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/5
 * @Version 1.0
 */
interface IPageInterceptor {
    fun intercept(chain: IPageChain)
}