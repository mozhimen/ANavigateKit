package com.mozhimen.navigatek.start.test.chain

import android.app.Activity
import android.content.Intent
import com.mozhimen.navigatek.start.test.commons.IPageInterceptor

object ActivityStarter {

    fun with(activity: Activity): Builder {
        return Builder(activity)
    }

    class Builder(private val activity: Activity) {

        private val taskChainManager by lazy { TaskChainBuilder.builder() }

        fun addInterceptor(interceptor: IPageInterceptor): Builder {
            taskChainManager.addTask(interceptor)
            return this
        }

        fun start(intent: Intent) {
            if (taskChainManager.manager.getTaskCount() > 0) {
                taskChainManager.setStatusListener(object : TaskChainStatusListener {
                    override fun onStatusChanged(isEndOfChain: Boolean) {
                        if (isEndOfChain) {
                            activity.startActivity(intent)
                        }
                    }
                }).execute()
            } else {
                activity.startActivity(intent)
            }
        }

        fun startForResult(intent: Intent, requestCode: Int, callback: (data: Intent?) -> Unit) {
            if (taskChainManager.manager.getTaskCount() > 0) {
                taskChainManager.setStatusListener(object : TaskChainStatusListener {
                    override fun onStatusChanged(isEndOfChain: Boolean) {
                        if (isEndOfChain) {
                            activity.startForResult(intent, requestCode, callback)
                        }
                    }
                }).execute()
            } else {
                activity.startForResult(intent, requestCode, callback)
            }
        }
    }
}
