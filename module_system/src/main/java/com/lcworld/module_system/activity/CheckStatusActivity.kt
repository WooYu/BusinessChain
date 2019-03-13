package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityCheckStatusBinding
import me.goldze.mvvmhabit.BR

/**
 * 审核状态页
 */
class CheckStatusActivity : BaseActivityEnhance<SystemActivityCheckStatusBinding, BaseViewModelEnhance>() {
    companion object {
        const val STATUS_TYPE = "status_type"
        const val SUCCESS = "success"
        const val FAIL = "fail"
        const val CHECKING = "checking"
    }

    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_check_status

    override fun initVariableId(): Int = BR.viewModel
    override fun initData() {
        super.initData()
        val type = intent.getStringExtra(STATUS_TYPE)
        if (type.isEmpty()) {
            finish()
            return
        }
        val image = when (type) {
            SUCCESS -> R.mipmap.system_check_success
            CHECKING -> R.mipmap.system_checking
            else -> R.mipmap.system_check_fail
        }
        val tips = when (type) {
            SUCCESS -> "恭喜您，审核成功！"
            CHECKING -> "我们将在48小时内进行审\n" +
                    "核，如若超时请联系客服!"
            else -> "非常抱歉，审核失败了~"
        }

        val title = when (type) {
            SUCCESS -> "审核成功！"
            CHECKING -> "审核中"
            else -> "审核失败"
        }

        binding.ivCheckStatus.setImageResource(image)
        binding.tvTips.text = tips
        binding.layoutTitle.tvTitle.text = title
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}
