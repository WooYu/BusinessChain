package com.lcworld.module_exchange.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.adapters.TextViewBindingAdapter
import android.view.View
import com.blankj.utilcode.util.ObjectUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_exchange.ApiServiceInterf
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.goldze.mvvmhabit.utils.KLog

class RechargeViewModel(application: Application) : BaseViewModelEnhance(application) {
    private var amountIndex: Int = -1
    private var payTypeIndex: Int = 0
    val amountObservableList = listOf(
        ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false),
        ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false)
    )
    val payTypeList = listOf(ObservableBoolean(true), ObservableBoolean(false))
    val amountList = listOf("10", "20", "50", "100", "200", "300", "500", "1000")
    val amount = ObservableField("")

    val onFocusChangeCommand = BindingCommand<Any>(BindingAction {
        if (amountIndex != -1) {
            amountObservableList[amountIndex].set(false)
            amountIndex = -1
        }
    })

    val isBtnEnable = ObservableBoolean(false)

    val amountAfterTextChanged = TextViewBindingAdapter.AfterTextChanged { s ->
        isBtnEnable.set(ObjectUtils.isNotEmpty(s))
    }
    val confirmOnClickCommand = BindingCommand<Any>(BindingAction { doConfirm() })

    fun onAmountSelect(view: View) {
        val index = (view.tag as String).toIntOrNull() ?: return
        if (amountIndex == index) return
        amountIndex = index
        amountObservableList.find { it.get() }?.set(false)
        amountObservableList[index].set(true)
        amount.set(amountList[index])

    }

    fun onPayTypeSelect(view: View) {
        val index = (view.tag as String).toIntOrNull() ?: return
        if (index == payTypeIndex) return
        payTypeIndex = index
        payTypeList.find { it.get() }?.set(false)
        payTypeList[index].set(true)
    }

    private fun doConfirm() {
        createOrder()
//        startActivity(RechargeSuccessAct::class.java)
    }

    @SuppressLint("CheckResult")
    fun createOrder() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .createOrder(amount.get())
            .flatMap { result1 ->
                RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
                    .payTrade(result1.toString(), result1.toString())
                    .flatMap { result2 ->
                        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
                            .recharge(result2.toString(), 0)
                    }
            }
            .compose(RxUtilsEnhanced.explicitTransform())
            .subscribe({
                KLog.e(it.toString())
            }) { KLog.e(it.message.toString()) }
    }
}