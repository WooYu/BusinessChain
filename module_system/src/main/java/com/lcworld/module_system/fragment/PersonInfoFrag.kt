package com.lcworld.module_system.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lcworld.library_base.base.BaseFragmentEnhance
import com.lcworld.module_system.BR
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemFragmentPersonInfoBinding
import com.lcworld.module_system.viewmodel.PersonInfoViewModel


/**
 * 订单列表
 */
class PersonInfoFrag : BaseFragmentEnhance<SystemFragmentPersonInfoBinding, PersonInfoViewModel>() {
    companion object {
        fun newInstance(): PersonInfoFrag {
            return PersonInfoFrag()
        }

        const val INDEX_0 = 0 //头像
        const val INDEX_1 = 1 //真实姓名
        const val INDEX_2 = 2 //身份证号
        const val INDEX_3 = 3 //身份证照片
    }

    override fun initContentView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?): Int {
        return R.layout.system_fragment_person_info
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
