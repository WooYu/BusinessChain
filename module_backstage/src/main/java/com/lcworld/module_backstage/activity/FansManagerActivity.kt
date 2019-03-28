package com.lcworld.module_backstage.activity

import android.databinding.Observable
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.footer.LoadingView
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.databinding.BackAcitivityFanManagerBinding
import com.lcworld.module_backstage.viewmodel.FanListViewModel

/**
 * 粉丝管理
 */
@Route(path = RouterActivityPath.Backstage.PAGER_FANS_MANAGER)
class FansManagerActivity : BaseActivityEnhance<BackAcitivityFanManagerBinding, FanListViewModel>() {


    override fun initContentView(bundle: Bundle?): Int = R.layout.back_acitivity_fan_manager

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.layoutTitle.tvTitle.text = "粉丝管理"
        binding.layoutTitle.tvRight.setOnClickListener {
            startActivity(FanSearchActivity::class.java)
        }
        initRefreshLayout()
        initObservable()
    }

    private fun initRefreshLayout() {
        binding.refreshLayout.setHeaderView(ProgressLayout(this))
        binding.refreshLayout.setFloatRefresh(true)
        binding.refreshLayout.setOverScrollRefreshShow(false)
        binding.refreshLayout.setBottomView(LoadingView(this))
        binding.refreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                super.onLoadMore(refreshLayout)
                viewModel.loadMore()
            }

            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                super.onRefresh(refreshLayout)
                viewModel.doRefresh()
            }
        })
        binding.refreshLayout.startRefresh()
    }

    private fun initObservable() {
        viewModel.finishRefreshing.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.refreshLayout.finishRefreshing()
            }
        })
        viewModel.finishLoadMore.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.refreshLayout.finishLoadmore()
            }
        })
    }
}
