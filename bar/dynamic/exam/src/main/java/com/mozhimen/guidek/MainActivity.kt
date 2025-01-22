package com.mozhimen.guidek

import android.os.Bundle
import androidx.navigation.findNavController
import com.mozhimen.basick.basek.BaseKActivity
import com.mozhimen.basick.basek.BaseKViewModel
import com.mozhimen.dynavbar.GuideK
import com.mozhimen.dynavbar.GuideKMgr
import com.mozhimen.dynavbar.commons.GuideKSelectedListener
import com.mozhimen.dynavbar.mos.GuideKPageInfo
import com.mozhimen.guidek.databinding.ActivityMainBinding
import com.mozhimen.uicorek.tabk.bottom.mos.TabKBottomMo

class MainActivity :
    BaseKActivity<ActivityMainBinding, BaseKViewModel>(R.layout.activity_main) {

    private var _currentItemId: Int = 0

    companion object {
        private const val GUIDEK_SAVED_CURRENT_ID = "GUIDEK_SAVED_CURRENT_ID"
    }

    override fun initData(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            _currentItemId = savedInstanceState.getInt(GUIDEK_SAVED_CURRENT_ID)
        }

        initView(savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.guidek_fragment_container)
        val hostFragment =
            supportFragmentManager.findFragmentById(R.id.guidek_fragment_container)

        GuideKMgr.instance.getConfig().observe(this) {
            if (it != null) {
                GuideK.buildNavGraph(
                    it,
                    this,
                    hostFragment!!.childFragmentManager,
                    navController,
                    R.id.guidek_fragment_container
                )
                GuideK.buildBottomLayout(
                    it,
                    GuideK.indexOf(it.pkgPages, _currentItemId),
                    vb.guidekBottomLayout,
                    listener = object : GuideKSelectedListener {
                        override fun onSelected(
                            index: Int,
                            pageInfo: GuideKPageInfo,
                            prevMo: TabKBottomMo?,
                            nextMo: TabKBottomMo
                        ) {
                            navController.navigate(pageInfo.id)
                            _currentItemId = pageInfo.id
                        }
                    })
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(GUIDEK_SAVED_CURRENT_ID, _currentItemId)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        this.finish()
    }
}