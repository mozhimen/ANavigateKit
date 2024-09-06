package com.mozhimen.navigatek.start.commons

/**
 * @ClassName IStartChain
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/5
 * @Version 1.0
 */
interface IStartChain {
    fun setStartChainStateListener(listener: IStartChainStateListener): IStartChain
    fun addInterceptor(interceptor: IStartInterceptor): IStartChain
    fun addInterceptorAt(index: Int, interceptor: IStartInterceptor): IStartChain
    fun next()
    fun exit()
    fun getInterceptors(): List<IStartInterceptor>
}