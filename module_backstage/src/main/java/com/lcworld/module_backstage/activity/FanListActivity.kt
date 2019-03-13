package com.lcworld.module_backstage.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.databinding.BackAcitivityFanListBinding
import com.lcworld.module_backstage.viewmodel.FanListViewModel

/**
 * 后台
 */
class FanListActivity : BaseActivityEnhance<BackAcitivityFanListBinding, FanListViewModel>() {
    companion object {
        const val FANS_TYPE = "FANS_TYPE"
    }

    private val fansType: Int
        get() = intent.getIntExtra(FANS_TYPE, 0)

    override fun initContentView(bundle: Bundle?): Int = R.layout.back_acitivity_fan_list

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        val title = when (fansType) {
            0 -> "全部粉丝"
            1 -> "直接粉丝"
            else -> "间接粉丝"
        }
        binding.layoutTitle.tvTitle.text = title
        binding.layoutTitle.tvRight.setOnClickListener {
            startActivity(FanSearchActivity::class.java)
        }
    }
}
