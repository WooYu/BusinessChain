package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.*
import com.blankj.utilcode.util.ToastUtils.showShort
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_exchange.ApiServiceInterf
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.TimeUtil.formatAmount
import com.lcworld.module_exchange.model.BillListEntity
import com.lcworld.module_exchange.viewmodel.recyclerItem.BillListItemViewModel
import com.lcworld.module_exchange.wrapSubscribe
import me.goldze.mvvmhabit.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.*

class BillListViewModel(application: Application) : BaseViewModelEnhance(application) {

    val observableList: ObservableList<BillListItemViewModel> = ObservableArrayList<BillListItemViewModel>()
    val itemBinding: ItemBinding<BillListItemViewModel> = ItemBinding.of(BR.viewModel, R.layout.exchange_item_bill_list)
    val adapter = BindingRecyclerViewAdapter<BillListItemViewModel>()
    val loadErrorStr = ObservableField("您该月没有账单数据")
    val showError = ObservableBoolean(false)

    private val pageNo = ObservableInt(1)
    val currentDate = ObservableField<Date>(Calendar.getInstance().time)
    val date = ObservableField(TimeUtil.formatData(currentDate.get()!!))
    val totalIncome = ObservableField("")
    val finishRefreshing = ObservableBoolean(false)
    val finishLoadMore = ObservableBoolean(false)

    //下拉刷新
    fun doRefresh() {
        pageNo.set(1)
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getBillList(
                pageNo.get(),
                TimeUtil.getMonthBegin(currentDate.get()!!),
                TimeUtil.getMonthEnd(currentDate.get()!!)
            ).compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(
                onStart = {
                    showError.set(false)
                },
                onNext = {
                    val result = (it as RequestResult<BillListEntity>).data
                    observableList.clear()
                    observableList.addAll(result.bill_list.data.map { item -> BillListItemViewModel(this, item) })
                    totalIncome.set("支出￥${formatAmount(result.pay_money)}  收入￥${formatAmount(result.income_money)}")
                },
                onError = {
                    finishRefreshing.set(!finishRefreshing.get())
                    showError.set(false)
                    showShort("数据加载失败")
                },
                onComplete = {
                    showError.set(observableList.isEmpty())
                    finishRefreshing.set(!finishRefreshing.get())
                })
    }

    //上拉加载
    fun loadMore() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getBillList(
                pageNo.get(),
                TimeUtil.getMonthBegin(currentDate.get()!!),
                TimeUtil.getMonthEnd(currentDate.get()!!)
            )
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(
                onNext = {
                    val result = (it as RequestResult<BillListEntity>).data
                    if (result.bill_list.data.isNotEmpty()) {
                        pageNo.set(result.bill_list.page_no)
                        observableList.addAll(result.bill_list.data.map { item -> BillListItemViewModel(this, item) })
                    } else {
                        showShort("没有更多数据了")
                    }
                    totalIncome.set("支出￥${formatAmount(result.pay_money)}  收入￥${formatAmount(result.income_money)}")
                },
                onError = {
                    finishLoadMore.set(!finishLoadMore.get())
                    showShort("数据加载失败")
                },
                onComplete = {
                    finishLoadMore.set(!finishLoadMore.get())
                })
    }
}