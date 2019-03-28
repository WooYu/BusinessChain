package com.lcworld.module_system.activity

import android.databinding.Observable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
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
        viewModel.getVipMsg()
    }

    private fun initLevelRecycler() {
        setLayoutManager()
    }

    private fun setLayoutManager(spanCount: Int = 6) {
        val manager = GridLayoutManager(this, spanCount)
        binding.recyclerLevel.layoutManager = manager
        binding.recyclerLevel.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        binding.recyclerLevel.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.listSizeObservable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                setLayoutManager(viewModel.listSizeObservable.get() / 3)
            }
        })
    }
}
