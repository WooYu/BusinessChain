package com.lcworld.module_system.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityAboutusBinding
import com.lcworld.module_system.databinding.SystemActivityInfoInputBinding
import me.goldze.mvvmhabit.BR

/**
 * 信息录入修改页面
 */
class InfoInputActivity : BaseActivityEnhance<SystemActivityInfoInputBinding, BaseViewModelEnhance>() {
    companion object {
        const val TITLE = "title"
        const val HINT = "hint"
        const val TYPE = "type"
    }

    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_info_input

    override fun initVariableId(): Int = BR.viewModel
    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = intent.getStringExtra(TITLE) ?: ""
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.etInput.apply {
            hint = intent.getStringExtra(HINT) ?: ""
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    binding.btConfirm.isEnabled = (s ?: "").isEmpty()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })
        }
    }
}
