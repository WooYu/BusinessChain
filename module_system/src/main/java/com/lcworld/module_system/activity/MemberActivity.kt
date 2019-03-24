package com.lcworld.module_system.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityMemberBinding
import com.lcworld.module_system.viewmodel.MemberViewModel
import me.goldze.mvvmhabit.BR

/**
 * 会员
 */
@Route(path = RouterActivityPath.Tool.PAGER_MEMBER)
class MemberActivity : BaseActivityEnhance<SystemActivityMemberBinding, MemberViewModel>() {
    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_member

    override fun initVariableId(): Int = BR.viewModel
    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_member)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        initLevelRecycler()
    }

    private fun initLevelRecycler() {
        val manager = GridLayoutManager(this,3)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerLevel.layoutManager = manager
    }
}
