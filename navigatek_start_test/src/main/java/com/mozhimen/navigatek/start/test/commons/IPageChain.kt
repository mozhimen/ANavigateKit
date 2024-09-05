package com.mozhimen.navigatek.start.test.commons

/**
 * @ClassName IStartChain
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/5
 * @Version 1.0
 */
interface IPageChain {
    fun addInterceptor(interceptor: IPageInterceptor)
    fun addInterceptorAt(index: Int, interceptor: IPageInterceptor)
    fun start()
}