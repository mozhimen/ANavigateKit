package com.mozhimen.navigatek.start

import android.app.Activity
import android.content.Intent
import com.mozhimen.kotlin.elemk.commons.IA_Listener
import com.mozhimen.navigatek.start.impls.StartChainImpl
import com.mozhimen.navigatek.start.utils.startForResult

object NavigateKStart {

    fun with(activity: Activity): Builder {
        return Builder(activity)
    }

    class Builder(private val _activity: Activity) : com.mozhimen.navigatek.start.commons.IStartBuilder {

        private val _startChainImpl = StartChainImpl()

        override fun addInterceptor(interceptor: com.mozhimen.navigatek.start.commons.IStartInterceptor): Builder {
            _startChainImpl.addInterceptor(interceptor)
            return this
        }

        override fun addInterceptorAt(index: Int, interceptor: com.mozhimen.navigatek.start.commons.IStartInterceptor): Builder {
            _startChainImpl.addInterceptorAt(index, interceptor)
            return this
        }

        override fun start(intent: Intent) {
            if (_startChainImpl.getInterceptors().isNotEmpty()) {
                _startChainImpl.setStartChainStateListener(object : com.mozhimen.navigatek.start.commons.IStartChainStateListener {
                    override fun onPostEnd() {
                        _activity.startActivity(intent)
                    }
                }).next()
            } else {
                _activity.startActivity(intent)
            }
        }

        override fun startForResult(intent: Intent, requestCode: Int, callback: IA_Listener<Intent?>) {
            if (_startChainImpl.getInterceptors().isNotEmpty()) {
                _startChainImpl.setStartChainStateListener(object : com.mozhimen.navigatek.start.commons.IStartChainStateListener {
                    override fun onPostEnd() {
                        _activity.startForResult(intent, requestCode, callback)
                    }
                }).next()
            } else {
                _activity.startForResult(intent, requestCode, callback)
            }
        }
    }
}
