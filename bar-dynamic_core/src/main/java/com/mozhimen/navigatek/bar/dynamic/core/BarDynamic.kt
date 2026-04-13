package com.mozhimen.navigatek.bar.dynamic.core

import android.content.ComponentName
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.fragment.DialogFragmentNavigator
import com.mozhimen.navigatek.bar.dynamic.core.commons.DynamicSelectedListener
import com.mozhimen.navigatek.bar.dynamic.core.helpers.DynamicHelper
import com.mozhimen.navigatek.bar.dynamic.core.mos.DynamicPageInfo
import com.mozhimen.navigatek.bar.dynamic.core.mos.GuideKPkgConfig
import com.mozhimen.navigatek.bar.dynamic.core.mos.GuideKPkgPage
import com.mozhimen.xmlk.layoutk.tab.bottom.LayoutKTabBottom
import com.mozhimen.xmlk.layoutk.tab.bottom.mos.MTabBottom
import com.mozhimen.xmlk.layoutk.tab.commons.ITabSelectedListener
import kotlin.math.abs

/**
 * @ClassName GuideK
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/4/29 15:24
 * @Version 1.0
 */
object BarDynamic {
    private const val TYPE_ACTIVITY = "activity"
    private const val TYPE_FRAGMENT = "fragment"
    private const val TYPE_DIALOG = "dialog"

    /**
     * 获取HashCode
     * @param clazz Class
     * @return Int
     */
    fun getHashCode(clazz: Class<*>): Int {
        return abs(clazz.canonicalName!!.hashCode())
    }

    /**
     * 获取位置
     * @param pages List<GuideKPkgPage>
     * @param currentId Int
     * @return Int
     */
    fun indexOf(pages: List<GuideKPkgPage>, currentId: Int): Int {
        val temps =
            pages.filter { page -> page.pageInfo.id == currentId }
        return if (temps.isNotEmpty()) {
            pages.indexOf(temps[0])
        } else 0
    }

    /**
     * 自动构建BottomLayout
     * @param pkgConfig GuideKPkgConfig
     * @param defaultIndex Int
     * @param tabKBottomLayout TabKBottomLayout
     * @param bottomAlpha Float
     * @param listener GuideKSelectedListener?
     */
    fun buildBottomLayout(
        pkgConfig: GuideKPkgConfig,
        defaultIndex: Int = 0,
        tabKBottomLayout: LayoutKTabBottom,
        bottomAlpha: Float = 1f,
        listener: DynamicSelectedListener? = null
    ) {
        tabKBottomLayout.setTabBottomAlpha(bottomAlpha)
        val mos = ArrayList<MTabBottom>()
        val infos = ArrayList<DynamicPageInfo>()
        pkgConfig.pkgPages.forEach {
            mos.add(it.tabKBottomMo)
            infos.add(it.pageInfo)
        }
        if (mos.isNotEmpty()) {
            tabKBottomLayout.inflateTabItem(mos)
            listener?.let {
                tabKBottomLayout.addTabItemSelectedListener(object :
                    ITabSelectedListener<MTabBottom> {
                    //                    override fun onTabSelected(index: Int, prevMo: TabKBottomMo?, nextMo: TabKBottomMo) {
//                        listener.onSelected(index, infos[index], prevMo, nextMo)
//                    }
                    override fun onTabItemSelected(index: Int, prevItem: MTabBottom?, currentItem: MTabBottom) {
                        listener.onSelected(index, infos[index], prevItem, currentItem)
                    }
                })
            }
            if (defaultIndex in pkgConfig.pkgPages.indices) {
                tabKBottomLayout.defaultSelected(mos[defaultIndex])
            } else {
                tabKBottomLayout.defaultSelected(mos[0])
            }
        }
    }


    /**
     * 构建NavGraph
     * @param activity FragmentActivity
     * @param childFragmentManager FragmentManager?
     * @param controller NavController
     * @param containerId Int
     */
    fun buildNavGraph(
        pkgConfig: GuideKPkgConfig,
        activity: FragmentActivity,
        childFragmentManager: FragmentManager?,
        controller: NavController,
        containerId: Int,
    ) {
        val iterator: Iterator<GuideKPkgPage> = pkgConfig.pkgPages.iterator()
        val provider = controller.navigatorProvider

        val graphNavigator = provider.getNavigator(NavGraphNavigator::class.java)
        val navGraph = NavGraph(graphNavigator)

        val dynamicHelper = DynamicHelper(activity, childFragmentManager!!, containerId)
        provider.addNavigator(dynamicHelper)
        while (iterator.hasNext()) {
            val page = iterator.next()
            when (page.pageInfo.destType) {
                TYPE_ACTIVITY -> {
                    val navigator = provider.getNavigator(ActivityNavigator::class.java)
                    val node: ActivityNavigator.Destination = navigator.createDestination()
                    node.id = page.pageInfo.id
                    node.setComponentName(ComponentName(activity.packageName, page.pageInfo.clazzName))
                    navGraph.addDestination(node)
                }

                TYPE_FRAGMENT -> {
                    val node = dynamicHelper.createDestination()
                    node.id = page.pageInfo.id
                    node.className = page.pageInfo.clazzName
                    navGraph.addDestination(node)
                }

                TYPE_DIALOG -> {
                    val navigator = provider.getNavigator(
                        DialogFragmentNavigator::class.java
                    )
                    val node: DialogFragmentNavigator.Destination = navigator.createDestination()
                    node.id = page.pageInfo.id
                    node.setClassName(page.pageInfo.clazzName)
                    navGraph.addDestination(node)
                }
            }
        }
        if (pkgConfig.indexDefault in pkgConfig.pkgPages.indices) {
            navGraph.setStartDestination(pkgConfig.pkgPages.filter { page -> page.pageInfo.index == pkgConfig.indexDefault }[0].pageInfo.id)
        }
        controller.graph = navGraph
    }
}