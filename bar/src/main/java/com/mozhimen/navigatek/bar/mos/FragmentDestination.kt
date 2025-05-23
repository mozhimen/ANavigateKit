package com.mozhimen.navigatek.bar.mos

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.mozhimen.kotlin.elemk.commons.I_AListener

open class FragmentDestination constructor(
    val id: String,
    val name: String,
    var fragmentClazzName: String,
    var onInvokeFragment: I_AListener<Fragment>,
    @DrawableRes val intResState: Int = 0,
    @DrawableRes val intResSelected: Int = 0,
    @DrawableRes val intResUnSelect: Int = 0
){
    override fun toString(): String {
        return "NavigateKDes(id='$id', name='$name', fragmentClazzName='$fragmentClazzName', onInvokeFragment=$onInvokeFragment, intResState=$intResState, intResSelected=$intResSelected, intResUnSelect=$intResUnSelect)"
    }
}