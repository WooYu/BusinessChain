package com.lcworld.module_exchange.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.adapters.TextViewBindingAdapter
import android.os.Bundle
import android.view.View
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_exchange.ApiServiceInterf
import com.lcworld.module_exchange.activity.RechargeSuccessAct
import com.lcworld.module_exchange.model.RechargeResult
import com.lcworld.module_exchange.wrapSubscribe
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.goldze.mvvmhabit.utils.ToastUtils

class RechargeViewModel(application: Application) : BaseViewModelEnhance(application) {
    private var amountIndex: Int = -1
    private var payTypeIndex: Int = 0
    val amountObservableList = listOf(
        ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false),
        ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false), ObservableBoolean(false)
    )
    val payTypeList = listOf(ObservableBoolean(true), ObservableBoolean(false))
    private val amountList = listOf("10", "20", "50", "100", "200", "300", "500", "1000")
    val amount = ObservableField("")
    fun onFocusChange() {
        if (amountIndex != -1) {
            amountObservableList[amountIndex].set(false)
            amountIndex = -1
        }
    }

    val isBtnEnable = ObservableBoolean(false)

    val amountAfterTextChanged = TextViewBindingAdapter.AfterTextChanged {
        isBtnEnable.set(it.toString().isNotEmpty())
        if (amountIndex != -1 && amountList[amountIndex] != it.toString()) {
            onFocusChange()
        }
    }
    val confirmOnClickCommand = BindingCommand<Any>(BindingAction { doConfirm() })

    val valueAlipayOrderInfo = ObservableField<RechargeResult>(RechargeResult())
    val valueWechatOrderInfo = ObservableField<RechargeResult>(RechargeResult())

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
    }

    @SuppressLint("CheckResult")
    fun createOrder() {
        val service = RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
        val amount = amount.get()?:return
        service.createOrder(amount)
            .flatMap {
                service.recharge(it.data.toString(), payTypeIndex + 1)
            }
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(onNext = {
                val result = it as RequestResult<RechargeResult>
                if(!result.isOk){
                    ToastUtils.showShort("${result.code}  ${result.message}")
                    return@wrapSubscribe
                }
                when (payTypeIndex) {
                    //0 对应微信支付 其他对应支付宝
                    0 -> valueWechatOrderInfo.set(result.data)
                    else -> valueAlipayOrderInfo.set(result.data)
                }
            })
    }

    @SuppressLint("CheckResult")
    fun wxCallback() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .wxCallback()
            .compose(RxUtilsEnhanced.explicitTransform())
            .subscribe({
                val bundle = Bundle()
                bundle.putString(RechargeSuccessAct.PAY_TYPE, "微信支付")
                startActivity(RechargeSuccessAct::class.java, bundle)
            }) { ToastUtils.showShort("充值失败") }
    }

    @SuppressLint("CheckResult")
    fun zfbCallBack() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .zfbCallBack()
            .compose(RxUtilsEnhanced.explicitTransform())
            .subscribe({
                val bundle = Bundle()
                bundle.putString(RechargeSuccessAct.PAY_TYPE, "支付宝")
                startActivity(RechargeSuccessAct::class.java, bundle)
            }) { ToastUtils.showShort("充值失败") }
    }
}