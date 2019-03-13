package com.lcworld.module_exchange.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.ObservableList
import android.graphics.Color
import android.os.Bundle
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.footer.LoadingView
import com.lcodecore.tkrefreshlayout.header.GoogleDotView
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.databinding.ExchangeActivityBillListBinding
import com.lcworld.module_exchange.viewmodel.BillListViewModel
import com.lcworld.module_exchange.viewmodel.recyclerItem.BillListItemViewModel
import java.util.*

class BillListAct : BaseActivityEnhance<ExchangeActivityBillListBinding, BillListViewModel>() {
    private val endDate: Calendar by lazy { Calendar.getInstance() }
    private val startDate: Calendar = getStartDate()
    private lateinit var timePick: TimePickerView

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_bill_list
    override fun initViewModel(): BillListViewModel =
        ViewModelProviders.of(this).get(BillListViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_bill)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.tvDate.setOnClickListener {
            if (!::timePick.isInitialized) timePick = getTimePick()
            timePick.show()
        }
        initRefreshLayout()
        initObservable()
        viewModel.doRefresh()
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
    }

    private fun initObservable() {
        viewModel.currentDate.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.date.set(TimeUtil.formatData(viewModel.currentDate.get()!!))
                viewModel.observableList.clear()
                viewModel.doRefresh()
            }
        })
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

    @SuppressLint("ResourceAsColor")
    private fun getTimePick(): TimePickerView {
        return TimePickerBuilder(this, OnTimeSelectListener { date, _ ->
            viewModel.date.set(TimeUtil.formatData(date))
            viewModel.currentDate.set(date)
        })
            .setType(booleanArrayOf(true, true, false, false, false, false))
            .setCancelText("取消")
            .setSubmitText("确认")
            .setCancelColor(R.color.colorAccent)
            .setSubmitColor(Color.BLACK)
            .setContentTextSize(15)
            .setOutSideCancelable(false)
            .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
            .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
            .setDividerColor(R.color.line_colorc)
            .setDate(endDate)
            .setRangDate(startDate, endDate)//起始终止年月日设定
            .setLineSpacingMultiplier(1.6f)
            .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
            .build()
    }

    private fun getStartDate(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = endDate.time
        calendar.add(Calendar.MONTH, -6)
        return calendar
    }
}