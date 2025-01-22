package com.mozhimen.navigatek.start.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.mozhimen.kotlin.elemk.androidx.fragment.InvisibleProxyFragment_ofAndroid
import com.mozhimen.kotlin.elemk.androidx.fragment.InvisibleProxyFragment_ofAndroidx
import com.mozhimen.kotlin.elemk.commons.IAB_Listener
import com.mozhimen.kotlin.elemk.commons.IA_Listener

/**
 * @ClassName NavigateKStartUtil
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/5 22:30
 * @Version 1.0
 */
fun androidx.fragment.app.Fragment.startForResult(
    intent: Intent,
    requestCode: Int,
    onResult: IAB_Listener<Boolean,Intent?>
) {
    NavigateKStartUtil.startForResult(this, intent, requestCode, onResult)
}

fun androidx.fragment.app.Fragment.startForResult(
    onAction: IA_Listener<InvisibleProxyFragment_ofAndroidx>,
    onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>
) {
    NavigateKStartUtil.startForResult(this, onAction, onResult)
}

fun android.app.Fragment.startForResult(
    intent: Intent,
    requestCode: Int,
    onResult: IAB_Listener<Boolean,Intent?>
) {
    NavigateKStartUtil.startForResult(this, intent, requestCode, onResult)
}

fun android.app.Fragment.startForResult(
    onAction: IA_Listener<InvisibleProxyFragment_ofAndroid>,
    onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>
) {
    NavigateKStartUtil.startForResult(this, onAction, onResult)
}

//////////////////////////////////////////////////////////////

fun FragmentActivity.startForResult_ofAndroidx(
    intent: Intent,
    requestCode: Int,
    onResult: IAB_Listener<Boolean,Intent?>,
) {
    NavigateKStartUtil.startForResult_ofAndroidx(this, intent, requestCode, onResult)
}

fun FragmentActivity.startForResult_ofAndroidx(
    onAction: IA_Listener<InvisibleProxyFragment_ofAndroidx>,
    onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>,
) {
    NavigateKStartUtil.startForResult_ofAndroidx(this, onAction, onResult)
}

fun Activity.startForResult_ofAndroid(
    intent: Intent,
    requestCode: Int,
    onResult: IAB_Listener<Boolean,Intent?>,
) {
    NavigateKStartUtil.startForResult_ofAndroid(this, intent, requestCode, onResult)
}

fun Activity.startForResult_ofAndroid(
    onAction: IA_Listener<InvisibleProxyFragment_ofAndroid>,
    onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>,
) {
    NavigateKStartUtil.startForResult_ofAndroid(this, onAction, onResult)
}

fun Activity.startForResult(
    intent: Intent,
    requestCode: Int,
    onResult: IAB_Listener<Boolean,Intent?>
) {
    NavigateKStartUtil.startForResult(this, intent, requestCode, onResult)
}

//////////////////////////////////////////////////////////////

object NavigateKStartUtil {
    @JvmStatic
    fun startForResult(
        fragment: androidx.fragment.app.Fragment,
        intent: Intent,
        requestCode: Int,
        onResult: IAB_Listener<Boolean,Intent?>,
    ) {
        startForResult(
            fragment = fragment,
            onAction = { frg -> frg.startActivityForResult(intent, requestCode) },
            onResult = requestCode to onResult
        )
    }

    @JvmStatic
    fun startForResult(
        fragment: androidx.fragment.app.Fragment,
        onAction: IA_Listener<InvisibleProxyFragment_ofAndroidx>,
        onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>
    ) {
        InvisibleProxyFragment_ofAndroidx.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroidx>(
            fragmentManager = fragment.childFragmentManager,
            onAction = onAction,
            onResult = onResult,
            onPermissionResult = null
        )
    }

    @JvmStatic
    fun startForResult(
        fragment: android.app.Fragment,
        intent: Intent,
        requestCode: Int,
        onResult: IAB_Listener<Boolean,Intent?>,
    ) {
        startForResult(
            fragment = fragment,
            onAction = { frg -> frg.startActivityForResult(intent, requestCode) },
            onResult = requestCode to onResult,
        )
    }

    @JvmStatic
    fun startForResult(
        fragment: android.app.Fragment,
        onAction: IA_Listener<InvisibleProxyFragment_ofAndroid>,
        onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>,
    ) {
        InvisibleProxyFragment_ofAndroid.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroid>(
            fragmentManager = fragment.childFragmentManager,
            onAction = onAction,
            onResult = onResult,
            onPermissionResult = null
        )
    }

    //////////////////////////////////////////////////////////////

    @JvmStatic
    fun startForResult_ofAndroidx(
        activity: FragmentActivity,
        intent: Intent,
        requestCode: Int,
        onResult: IAB_Listener<Boolean,Intent?>,
    ) {
        startForResult_ofAndroidx(
            activity = activity,
            onAction = { frg -> frg.startActivityForResult(intent, requestCode) },
            onResult = requestCode to onResult
        )
    }

    @JvmStatic
    fun startForResult_ofAndroidx(
        activity: FragmentActivity,
        onAction: IA_Listener<InvisibleProxyFragment_ofAndroidx>,
        onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>,
    ) {
        InvisibleProxyFragment_ofAndroidx.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroidx>(
            fragmentManager = activity.supportFragmentManager,
            onAction = onAction,
            onResult = onResult,
            onPermissionResult = null
        )
    }

    @JvmStatic
    fun startForResult_ofAndroid(
        activity: Activity,
        intent: Intent,
        requestCode: Int,
        onResult: IAB_Listener<Boolean,Intent?>,
    ) {
        startForResult_ofAndroid(
            activity = activity,
            onAction = { frg -> frg.startActivityForResult(intent, requestCode) },
            onResult = requestCode to onResult
        )
    }

    @JvmStatic
    fun startForResult_ofAndroid(
        activity: Activity,
        onAction: IA_Listener<InvisibleProxyFragment_ofAndroid>,
        onResult: Pair<Int, IAB_Listener<Boolean,Intent?>>,
    ) {
        InvisibleProxyFragment_ofAndroid.startInvisibleProxyFragment<InvisibleProxyFragment_ofAndroid>(
            fragmentManager = activity.fragmentManager,
            onAction = onAction,
            onResult = onResult,
            onPermissionResult = null
        )
    }

    //////////////////////////////////////////////////////////////

    @JvmStatic
    fun startForResult(
        activity: Activity,
        intent: Intent,
        requestCode: Int,
        onResult: IAB_Listener<Boolean,Intent?>,
    ) {
        //FragmentActivity
        if (activity is FragmentActivity) {
            startForResult_ofAndroidx(activity, intent, requestCode, onResult)
        } else {//Activity
            startForResult_ofAndroid(activity, intent, requestCode, onResult)
        }
    }
}