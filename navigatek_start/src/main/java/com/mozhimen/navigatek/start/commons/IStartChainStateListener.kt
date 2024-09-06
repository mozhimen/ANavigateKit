package com.mozhimen.navigatek.start.commons

/**
 * @ClassName IPageChainStateListener
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/5
 * @Version 1.0
 */
interface IStartChainStateListener {
    fun onPreNextRun(){}
    fun onPostNextRun(){}
    fun onPreEnd(){}
    fun onPostEnd(){}
}