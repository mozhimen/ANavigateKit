package com.mozhimen.navigatek.start.impls

/**
 * @ClassName StartChainImpl
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/5 22:32
 * @Version 1.0
 */
class StartChainImpl : com.mozhimen.navigatek.start.commons.IStartChain {

    private val _interceptors = mutableListOf<com.mozhimen.navigatek.start.commons.IStartInterceptor>()
    private var _currentIndex = 0
    private var _iStartChainStateListener: com.mozhimen.navigatek.start.commons.IStartChainStateListener? = null

    override fun setStartChainStateListener(listener: com.mozhimen.navigatek.start.commons.IStartChainStateListener): com.mozhimen.navigatek.start.commons.IStartChain {
        _iStartChainStateListener = listener
        return this
    }

    override fun addInterceptor(interceptor: com.mozhimen.navigatek.start.commons.IStartInterceptor): com.mozhimen.navigatek.start.commons.IStartChain {
        _interceptors.add(interceptor)
        return this
    }

    override fun addInterceptorAt(index: Int, interceptor: com.mozhimen.navigatek.start.commons.IStartInterceptor): com.mozhimen.navigatek.start.commons.IStartChain {
        if (index in 0.._interceptors.size) {
            _interceptors.add(index, interceptor)
        } /*else {
            throw IndexOutOfBoundsException("Index $index out of bounds for task size ${interceptors.size}")
        }*/
        return this
    }

    override fun getInterceptors(): List<com.mozhimen.navigatek.start.commons.IStartInterceptor> {
        return _interceptors
    }

    override fun next() {
        if (_currentIndex >= _interceptors.size) {
            _iStartChainStateListener?.onPreEnd()
            exit()
            _iStartChainStateListener?.onPostEnd()
        } else {
            _iStartChainStateListener?.onPreNextRun()
            _interceptors[_currentIndex++].intercept(this)
            _iStartChainStateListener?.onPostNextRun()
        }
    }

    override fun exit() {
        _interceptors.clear()
        _currentIndex = 0
    }
}

//object TaskChainBuilder {
//
//    fun builder() = Builder()
//
//    class Builder {
//        private val taskChainManager = TaskChainManager()
//
//        fun addTask(task: IPageInterceptor) = apply {
//            taskChainManager.addInterceptor(task)
//        }
//
//        fun addTaskAt(index: Int, interceptor: IPageInterceptor) = apply {
//            taskChainManager.addInterceptorAt(index, interceptor)
//        }
//
//        fun setStatusListener(listener: IPageChainStateListener) = apply {
//            taskChainManager.setStatusListener(listener)
//        }
//
//        fun execute() {
//            if (taskChainManager.getTaskCount() > 0) {
//                taskChainManager.next()
//            }
//        }
//
//        val manager: TaskChainManager
//            get() = taskChainManager
//    }
//}