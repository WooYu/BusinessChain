package com.lcworld.module_system.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lcworld.library_base.base.BaseFragmentEnhance
import com.lcworld.module_system.BR
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemFragmentCompanyInfoBinding
import com.lcworld.module_system.viewmodel.CompanyInfoViewModel


/**
 * 订单列表
 */
class CompanyInfoFrag : BaseFragmentEnhance<SystemFragmentCompanyInfoBinding, CompanyInfoViewModel>() {

    companion object {
        fun newInstance(): CompanyInfoFrag {
            return CompanyInfoFrag()
        }

        const val INDEX_0 = 0 //企业LOGO
        const val INDEX_1 = 1 //企业名称
        const val INDEX_2 = 2 //联系人
        const val INDEX_3 = 3 //职务
        const val INDEX_4 = 4 //手机号
        const val INDEX_5 = 5 //统一信用代码
        const val INDEX_6 = 6 //办公地址
        const val INDEX_7 = 7 //营业执照
    }

    override fun initContentView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?): Int {
        return R.layout.system_fragment_company_info
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
