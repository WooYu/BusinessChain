package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityInviteBinding
import com.lcworld.module_system.viewmodel.InviteViewModel
import me.goldze.mvvmhabit.BR

/**
 * 邀请好友
 */
class InviteFriendActivity : BaseActivityEnhance<SystemActivityInviteBinding, InviteViewModel>() {
    override fun initContentView(bundle: Bundle): Int = R.layout.system_activity_invite

    override fun initVariableId(): Int = BR.viewModel
    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_invite_friends)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}
