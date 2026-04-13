package com.mozhimen.navigatek.bar.dynamic.core

import androidx.lifecycle.MutableLiveData
import com.mozhimen.cachek.room.CacheKRM
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.kotlin.utilk.kotlin.UtilKStrAsset
import com.mozhimen.navigatek.bar.dynamic.core.mos.DynamicConstants
import com.mozhimen.navigatek.bar.dynamic.core.mos.DynamicDestination
import com.mozhimen.navigatek.bar.dynamic.core.mos.DynamicPageInfo
import com.mozhimen.navigatek.bar.dynamic.core.mos.GuideKPkgConfig
import com.mozhimen.navigatek.bar.dynamic.core.mos.GuideKPkgPage
import com.mozhimen.serialk.gson.UtilGsonFormat
import com.mozhimen.taskk.executor.TaskKExecutor
import com.mozhimen.xmlk.layoutk.tab.bottom.mos.MTabBottom
import java.util.*

/**
 * @ClassName GlideKMgr
 * @Description GlideKMgr
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/5/1 18:29
 * @Version 1.0
 */
class BarDynamicMgr:IUtilK {

    companion object {
        @JvmStatic
        val instance = GlideKMgrProvider.holder
        private const val NAVIGATE_BAR_DYNAMIC_CACHE_NAME_PKG_CONFIG = "navigate_bar_dynamic_cache_name_pkg_config"
    }

    private object GlideKMgrProvider {
        val holder = BarDynamicMgr()
    }

    @Volatile
    private var _currentConfig = MutableLiveData<GuideKPkgConfig>()

    fun init(defaultConfig: GuideKPkgConfig) {
        TaskKExecutor.execute(TAG, 0) {
            readDestinationsFromJson(defaultConfig)
        }
    }

    @Synchronized
    fun getConfig(): MutableLiveData<GuideKPkgConfig> = _currentConfig

    private fun readDestinationsFromJson(defaultConfig: GuideKPkgConfig) {
        if (!/*UtilKAssets.isFileExists*/UtilKStrAsset.isAssetExists(DynamicConstants.FILE_NAME_TAB_CONFIG)) {
            getPkgConfigFromCacheK()?.let { cacheConfig ->
                _currentConfig.postValue(cacheConfig)
            } ?: kotlin.run {
                _currentConfig.postValue(defaultConfig)
                savePkgConfig2CacheK(defaultConfig)
            }
        } else {
            val content = UtilKStrAsset.strAssetName2str_use_ofBufferedReader(DynamicConstants.FILE_NAME_TAB_CONFIG)//UtilKAssets.json2String(DynamicConstants.FILE_NAME_TAB_CONFIG)
            requireNotNull(content) { "parseFile guideKTabConfig.json fail!" }
            val destination = UtilGsonFormat.strJson2t_gson(content, DynamicDestination::class.java)

            val jsonConfig = packageConfig(destination)
            getPkgConfigFromCacheK()?.let { cacheConfig ->
                if (jsonConfig.versionCode > cacheConfig.versionCode) {
                    _currentConfig.postValue(jsonConfig)
                    CacheKRM.putObj(NAVIGATE_BAR_DYNAMIC_CACHE_NAME_PKG_CONFIG, jsonConfig)
                } else {
                    _currentConfig.postValue(defaultConfig)
                }
            } ?: kotlin.run {
                _currentConfig.postValue(jsonConfig)
                savePkgConfig2CacheK(jsonConfig)
            }
        }
    }

    private fun packageConfig(destination: DynamicDestination?): GuideKPkgConfig {
        val pkgPages = ArrayList<GuideKPkgPage>()
        destination?.pages?.indices?.let {
            for (index in destination.pages.indices) {
                val page = destination.pages.filter { page -> page.pageInfo.index == index }[0]
                if (!page.enable) continue
                var bottomMo: MTabBottom? = null
                var pageInfo: DynamicPageInfo?
                when (page.tabConfig.type) {
                    DynamicConstants.ICONFONT_TEXT -> {
                        bottomMo = MTabBottom(
                            page.tabConfig.name,
                            page.tabConfig.iconFont,
                            page.tabConfig.iconNameDefault,
                            page.tabConfig.iconNameSelected,
                            page.tabConfig.iconColorDefault,
                            page.tabConfig.iconColorSelected
                        )
                    }
                    DynamicConstants.IMAGE -> {
                        bottomMo = MTabBottom(
                            page.tabConfig.name,
                            page.tabConfig.bitmapDefault,
                            page.tabConfig.bitmapSelected
                        )
                    }
                    DynamicConstants.IMAGE_TEXT -> {
                        bottomMo = MTabBottom(
                            page.tabConfig.name,
                            page.tabConfig.bitmapDefault,
                            page.tabConfig.bitmapSelected,
                            page.tabConfig.iconColorDefault,
                            page.tabConfig.iconColorSelected
                        )
                    }
                }
                bottomMo?.let { mo ->
                    pageInfo = DynamicPageInfo(
                        page.pageInfo.clazzName,
                        page.pageInfo.destType,
                        page.pageInfo.index,
                        page.pageInfo.id,
                        page.pageInfo.url
                    )
                    pkgPages.add(GuideKPkgPage(pageInfo!!, mo))
                }
            }

        }
        return GuideKPkgConfig(destination?.versionCode ?:0, destination?.indexDefault?:0, pkgPages)
    }

    private fun getPkgConfigFromCacheK(): GuideKPkgConfig? {
        return CacheKRM.getObj<GuideKPkgConfig>(NAVIGATE_BAR_DYNAMIC_CACHE_NAME_PKG_CONFIG,GuideKPkgConfig(0,0, emptyList()))
    }

    private fun savePkgConfig2CacheK(pkgConfig: GuideKPkgConfig) {
        return CacheKRM.putObj(NAVIGATE_BAR_DYNAMIC_CACHE_NAME_PKG_CONFIG, pkgConfig)
    }
}