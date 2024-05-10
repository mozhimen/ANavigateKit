package com.mozhimen.navigatek.navigation.helpers

import android.annotation.SuppressLint
import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.mozhimen.basick.utilk.android.util.w
import com.mozhimen.basick.utilk.commons.IUtilK

/**
 * @ClassName DestinationUtil
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
fun NavController.startDestinationId(destinationId: Int) {
    DestinationUtil.startDestinationId(this, destinationId)
}

////////////////////////////////////////////////////////////////////////////////

fun Activity.getDestinationId(): Int =
    DestinationUtil.getDestinationId(this)

fun Fragment.getDestinationId(): Int =
    DestinationUtil.getDestinationId(this)

fun Class<*>.getDestinationId(): Int =
    DestinationUtil.getDestinationId(this)

fun NavController.findNavigationId(destinationId: Int): Boolean =
    DestinationUtil.findNavigationId(this, destinationId)

fun NavController.getCurrentDestinationId(): Int? =
    DestinationUtil.getCurrentDestinationId(this)

////////////////////////////////////////////////////////////////////////////////

object DestinationUtil : IUtilK {

    @JvmStatic
    fun startDestinationId(navController: NavController, destinationId: Int) {
        if (!findNavigationId(navController, destinationId)) {
            "startId don't find thid id".w(TAG)
            return
        }
        if (getCurrentDestinationId(navController) == destinationId) {
            "startId current id is this id".w(TAG)
            return
        }
        navController.navigate(destinationId)
    }

    ////////////////////////////////////////////////////////////////////////////////

    @JvmStatic
    fun getDestinationId(activity: Activity): Int =
        getDestinationId(activity::class.java)

    @JvmStatic
    fun getDestinationId(fragment: Fragment): Int =
        getDestinationId(fragment::class.java)

    @JvmStatic
    fun getDestinationId(clazz: Class<*>): Int =
        kotlin.math.abs(clazz.name.hashCode())

    @SuppressLint("RestrictedApi")
    @JvmStatic
    fun findNavigationId(navController: NavController, destinationId: Int): Boolean =
        navController.findDestination(destinationId) != null

    @JvmStatic
    fun getCurrentDestinationId(navController: NavController): Int? =
        navController.currentDestination?.id
}