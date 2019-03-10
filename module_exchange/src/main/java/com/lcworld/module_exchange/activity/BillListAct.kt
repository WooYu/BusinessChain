package com.lcworld.module_exchange.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.ObservableList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.activity.BillDetailAct.Companion.ITEM
import com.lcworld.module_exchange.adapter.BillListAdapter
import com.lcworld.module_exchange.databinding.ExchangeActivityBillListBinding
import com.lcworld.module_exchange.model.BillListItem
import com.lcworld.module_exchange.viewmodel.BillListViewModel
import org.jetbrains.anko.startActivity
import java.util.*

class BillListAct : BaseActivityEnhance<ExchangeActivityBillListBinding, BillListViewModel>() {
    private val endDate: Calendar by lazy { Calendar.getInstance() }
    private val startDate: Calendar = getStartDate()
    private lateinit var timePick: TimePickerView
    private lateinit var billAdapter: BillListAdapter

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_bill_list
    override fun initViewModel(): BillListViewModel =
        ViewModelProviders.of(this).get(BillListViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_bill)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        timePick = getTimePick()
        binding.tvDate.setOnClickListener {
            timePick.show()
        }

        initViewRefreshLayout()
        initViewRecyclerView()
        initObservable()
        viewModel.requestBillList(false)
    }

    private fun initObservable() {
        viewModel.currentDate.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.date.set(TimeUtil.formatData(viewModel.currentDate.get()!!))
            }

        })
        viewModel.isRefreshObservable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.swipeLayout.isRefreshing = viewModel.isRefreshObservable.get()
            }

        })
        viewModel.isLoadMoreObservable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                billAdapter.setEnableLoadMore(viewModel.isLoadMoreObservable.get())
            }

        })
        viewModel.billList.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<BillListItem>>(){
            override fun onChanged(sender: ObservableList<BillListItem>?) {
            }

            override fun onItemRangeRemoved(sender: ObservableList<BillListItem>?, positionStart: Int, itemCount: Int) {
                billAdapter.setNewData(listOf())
            }

            override fun onItemRangeMoved(
                sender: ObservableList<BillListItem>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
            }

            override fun onItemRangeInserted(
                sender: ObservableList<BillListItem>?,
                positionStart: Int,
                itemCount: Int
            ) {
                billAdapter.setNewData(viewModel.billList)
            }

            override fun onItemRangeChanged(sender: ObservableList<BillListItem>?, positionStart: Int, itemCount: Int) {
            }

        })
    }

    private fun initViewRefreshLayout() {
        binding.swipeLayout.setColorSchemeColors(resources.getColor(R.color.dominant_hue))
        binding.swipeLayout.setOnRefreshListener { viewModel.requestBillList(false) }
    }

    private fun initViewRecyclerView() {
        billAdapter = BillListAdapter(R.layout.exchange_item_bill_list, viewModel.billList)
        billAdapter.setOnLoadMoreListener({
            viewModel.pageNo.set(viewModel.pageNo.get()+1)
            viewModel.requestBillList()
        }, binding.recycler)
        billAdapter.setOnItemClickListener{adapter,_,position ->
            startActivity<BillDetailAct>(Pair(ITEM,adapter.data[position]))
        }
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = billAdapter
    }

    @SuppressLint("ResourceAsColor")
    private fun getTimePick():TimePickerView{
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
            .setLabel("","","","","","")//默认设置为年月日时分秒
            .build()
    }

    private fun getStartDate():Calendar{
        val calendar = Calendar.getInstance()
        calendar.time = endDate.time
        calendar.add(Calendar.MONTH, -6)
        return calendar
    }
}