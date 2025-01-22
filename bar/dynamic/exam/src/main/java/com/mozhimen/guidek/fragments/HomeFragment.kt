package com.mozhimen.guidek.fragments

import android.util.Log
import com.mozhimen.basick.basek.BaseKFragment
import com.mozhimen.basick.basek.BaseKViewModel
import com.mozhimen.guidek.R
import com.mozhimen.guidek.annor.GuideKDestination
import com.mozhimen.guidek.databinding.FragmentGuidekHomeBinding

/**
 * @ClassName HomeFragment
 * @Description TODO
 * @Author Kolin Zhao
 * @Date 2021/9/3 13:15
 * @Version 1.0
 */
@GuideKDestination(pageUrl = "main/guidek/home", pageIndex = 0, isDefault = true)
class HomeFragment : BaseKFragment<FragmentGuidekHomeBinding,BaseKViewModel>(R.layout.fragment_guidek_home) {

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }
}