package com.lcworld.module_backstage.activity

import android.os.Bundle
import android.view.View
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.databinding.BackAcitivityFanDetailBinding
import com.lcworld.module_backstage.model.FansItem
import com.lcworld.module_backstage.viewmodel.FanDeatilViewModel

/**
 * 后台
 */
class FansDetailActivity : BaseActivityEnhance<BackAcitivityFanDetailBinding, FanDeatilViewModel>() {
    companion object {
        const val FANS_DETAIL = "FANS_DETAIL"
    }

    private val fansItem: FansItem
        get() = intent.getParcelableExtra(FANS_DETAIL) ?: FansItem()

    override fun initContentView(bundle: Bundle?): Int = R.layout.back_acitivity_fan_detail

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.layoutTitle.tvTitle.text = "粉丝详情"
        binding.layoutTitle.tvRight.visibility = View.GONE
    }
}
