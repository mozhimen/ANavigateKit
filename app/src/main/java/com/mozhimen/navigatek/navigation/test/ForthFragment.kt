package com.mozhimen.navigatek.navigation.test

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mozhimen.uik.databinding.bases.viewdatabinding.fragment.BaseFragmentVDB
import com.mozhimen.kotlin.elemk.mos.MKey
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.navigatek.navigation.helpers.getDestinationId
import com.mozhimen.navigatek.test.databinding.FragmentThirdBinding
import com.mozhimen.navigatek.test.databinding.ItemNavigatekBinding
import com.mozhimen.xmlk.recyclerk.quick.RecyclerKQuickAdapterVDB
import com.mozhimen.navigatek.test.R
import com.mozhimen.navigatek.test.BR

class ForthFragment : BaseFragmentVDB<FragmentThirdBinding>() {
    private val _datas = mutableListOf(MKey("01", "01"))
    private var _adapter: RecyclerKQuickAdapterVDB<MKey, ItemNavigatekBinding>? = null

    @OptIn(OApiCall_BindLifecycle::class, OApiInit_ByLazy::class)
    override fun initView(savedInstanceState: Bundle?) {
        arguments?.getString(FirstFragment.KEY_FIRST,"这是原有的数据")?.let {
            vdb.navigatekFragmentSecondTxt1.text = it
        }
        vdb.navigatekFragmentSecondTxt2.setOnClickListener {
            (parentFragment?.parentFragment as? SecondFragment)?.navigateKProxy?.startDestinationId(ThirdFragment::class.java.getDestinationId())
        }

        ///////////////////////////////////////////////////////////////////////

        vdb.navigatekFragmentSecondRecycler.layoutManager = LinearLayoutManager(requireActivity())
        _adapter = RecyclerKQuickAdapterVDB<MKey, ItemNavigatekBinding>(_datas, R.layout.item_navigatek, BR.item_navigatek)
        vdb.navigatekFragmentSecondRecycler.adapter = _adapter
    }
}