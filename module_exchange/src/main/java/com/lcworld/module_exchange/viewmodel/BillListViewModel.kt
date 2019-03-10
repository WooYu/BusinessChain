package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.*
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_exchange.ApiServiceInterf
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.model.BillListEntity
import com.lcworld.module_exchange.model.BillListItem
import com.lcworld.module_exchange.wrapSubscribe
import java.util.*

class BillListViewModel(application: Application) : BaseViewModelEnhance(application) {

    val billList = ObservableArrayList<BillListItem>()
    val pageNo = ObservableInt(1)
    val currentDate = ObservableField<Date>(Calendar.getInstance().time)
    val date = ObservableField(TimeUtil.formatData(currentDate.get()!!))
    val totalIncome = ObservableField("")
    val isRefreshObservable = ObservableBoolean(true)
    val isLoadMoreObservable = ObservableBoolean(false)

    fun requestBillList(isLoadMore:Boolean = true){
        if (!isLoadMore){
            pageNo.set(1)
            isRefreshObservable.set(true)
            isLoadMoreObservable.set(false)
        }else{
            isRefreshObservable.set(false)
            isLoadMoreObservable.set(true)
        }
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getBillList(pageNo.get(),TimeUtil.getMonthBegin(currentDate.get()!!),TimeUtil.getMonthEnd(currentDate.get()!!))
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(onNext = {
                val result = (it as RequestResult<BillListEntity>).data
                totalIncome.set(result.data_total)
                pageNo.set(result.page_no)
               if (!isLoadMore){
                   billList.clear()
               }
                billList.addAll(result.data)
            },onComplete = {
                isRefreshObservable.set(false)
                isLoadMoreObservable.set(false)
            })

    }
}