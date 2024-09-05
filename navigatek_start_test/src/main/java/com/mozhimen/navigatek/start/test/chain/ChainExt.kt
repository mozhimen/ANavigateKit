package com.mozhimen.navigatek.start.test.chain

import com.mozhimen.navigatek.start.test.commons.IPageChain
import com.mozhimen.navigatek.start.test.commons.IPageInterceptor

interface TaskChainStatusListener {
    fun onStatusChanged(isEndOfChain: Boolean)
}

class TaskChainManager : IPageChain {

    private val interceptors = mutableListOf<IPageInterceptor>()
    private var currentIndex = 0
    private var statusListener: TaskChainStatusListener? = null

    fun setStatusListener(listener: TaskChainStatusListener) {
        this.statusListener = listener
    }

    override fun addInterceptor(interceptor: IPageInterceptor) {
        interceptors.add(interceptor)
    }

    override fun addInterceptorAt(index: Int, interceptor: IPageInterceptor) {
        if (index in 0..interceptors.size) {
            interceptors.add(index, interceptor)
        } else {
            throw IndexOutOfBoundsException("Index $index out of bounds for task size ${interceptors.size}")
        }
    }

    fun getTaskCount() = interceptors.size

    override fun start() {
        if (currentIndex >= interceptors.size) {
            statusListener?.onStatusChanged(true)
        } else {
            statusListener?.onStatusChanged(false)
            interceptors[currentIndex++].intercept(this)
        }
    }

    fun reset() {
        interceptors.clear()
        currentIndex = 0
    }
}

object TaskChainBuilder {

    fun builder() = Builder()

    class Builder {
        private val taskChainManager = TaskChainManager()

        fun addTask(task: IPageInterceptor) = apply {
            taskChainManager.addInterceptor(task)
        }

        fun addTaskAt(index: Int, interceptor: IPageInterceptor) = apply {
            taskChainManager.addInterceptorAt(index, interceptor)
        }

        fun setStatusListener(listener: TaskChainStatusListener) = apply {
            taskChainManager.setStatusListener(listener)
        }

        fun execute() {
            if (taskChainManager.getTaskCount() > 0) {
                taskChainManager.start()
            }
        }

        val manager: TaskChainManager
            get() = taskChainManager
    }
}
