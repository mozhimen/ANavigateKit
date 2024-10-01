package com.mozhimen.navigatek.navigation.test

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mozhimen.bindk.bases.fragment.databinding.BaseFragmentVDB
import com.mozhimen.kotlin.elemk.mos.MKey
import com.mozhimen.navigatek.navigation.helpers.getDestinationId
import com.mozhimen.navigatek.navigation.test.databinding.FragmentFirstBinding
import com.mozhimen.navigatek.navigation.test.databinding.ItemNavigatekBinding
import com.mozhimen.xmlk.recyclerk.quick.RecyclerKQuickAdapterVDB

class FirstFragment : BaseFragmentVDB<FragmentFirstBinding>() {
    companion object {
        const val KEY_FIRST = "Title"
    }

    private val _datas = mutableListOf(MKey("01", "01"))
    private var _adapter: RecyclerKQuickAdapterVDB<MKey, ItemNavigatekBinding>? = null

    override fun initView(savedInstanceState: Bundle?) {
        //用法一
//        vdb.navigatekFragmentFirstTxt.setOnClickListener {
//            (requireActivity() as NavigateKActivity).navigateKProxy.startDestinationId(SecondFragment::class.java.getDestinationId())
//        }
        //用法二
        vdb.navigatekFragmentFirstTxt.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                SecondFragment::class.java.getDestinationId(),
                Bundle().apply { putString(KEY_FIRST, "这是Args数据") })
        )

        ///////////////////////////////////////////////////////////////

        vdb.navigatekFragmentFirstRecycler.layoutManager = LinearLayoutManager(requireActivity())
        _adapter = RecyclerKQuickAdapterVDB(_datas, R.layout.item_navigatek, BR.item_navigatek)
        vdb.navigatekFragmentFirstRecycler.adapter = _adapter
    }
}