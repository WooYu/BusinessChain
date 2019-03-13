package com.lcworld.module_backstage.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.databinding.BackAcitivityFanSearchBinding
import com.lcworld.module_backstage.viewmodel.FanSearchViewModel

/**
 * 后台
 */
class FanSearchActivity : BaseActivityEnhance<BackAcitivityFanSearchBinding, FanSearchViewModel>() {
    override fun initContentView(bundle: Bundle?): Int = R.layout.back_acitivity_fan_search

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.ivBack.setOnClickListener { finish() }
    }
}
