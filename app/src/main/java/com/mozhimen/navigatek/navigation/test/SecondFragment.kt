package com.mozhimen.navigatek.navigation.test

import android.os.Bundle
import com.mozhimen.basick.elemk.androidx.fragment.bases.databinding.BaseFragmentVDB
import com.mozhimen.basick.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.basick.lintk.optins.OApiInit_ByLazy
import com.mozhimen.navigatek.navigation.NavigateKFragmentProxy
import com.mozhimen.navigatek.navigation.helpers.getDestinationId
import com.mozhimen.navigatek.navigation.mos.MNavigateKConfig
import com.mozhimen.navigatek.navigation.test.databinding.FragmentSecondBinding

@OptIn(OApiCall_BindLifecycle::class, OApiInit_ByLazy::class)
class SecondFragment : BaseFragmentVDB<FragmentSecondBinding>() {
    private val _fragments = listOf(ThirdFragment::class.java, ForthFragment::class.java)
    private val _navigateKFragmentProxy by lazy {
        NavigateKFragmentProxy(requireActivity(),this, R.id.navigatek_fragment_container, _fragments, MNavigateKConfig(isFragmentShowHide = true))//show_hide方式
//        NavigateKProxy(this, R.id.navigatek_fragment_container, _fragments)
    }

    val navigateKProxy get() = _navigateKFragmentProxy

    @OptIn(OApiCall_BindLifecycle::class, OApiInit_ByLazy::class)
    override fun initView(savedInstanceState: Bundle?) {
        arguments?.getString(FirstFragment.KEY_FIRST,"这是原有的数据")?.let {
            vdb.navigatekFragmentSecondTxt1.text = it
        }
        vdb.navigatekFragmentSecondTxt2.setOnClickListener {
            (requireActivity() as NavigateKActivity).navigateKProxy.startDestinationId(FirstFragment::class.java.getDestinationId())
        }

        ///////////////////////////////////////////////////////////////////////

        _navigateKFragmentProxy.bindLifecycle(this)
    }
}