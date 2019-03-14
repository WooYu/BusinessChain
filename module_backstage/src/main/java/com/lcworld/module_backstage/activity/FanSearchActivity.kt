package com.lcworld.module_backstage.activity

import android.databinding.Observable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.footer.LoadingView
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
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
        initEditText()
        initRefreshLayout()
        initObservable()
    }

    private fun initEditText() {
        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchCondition.set(s.toString())
                viewModel.doRefresh()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
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
