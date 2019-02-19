package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.adapters.TextViewBindingAdapter
import com.blankj.utilcode.util.ObjectUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.activity.WithdrawSuccessAct
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class WithdrawViewModel(application: Application) : BaseViewModelEnhance(application) {
    val logo = ObservableInt(R.mipmap.exchange_bank_merchants)
    val isBtnEnable = ObservableBoolean(false)
    val bankcard = ObservableField("招商银行储蓄卡（0145）")
    val amount = ObservableField("")
    val bankcardOnClickCommand = BindingCommand<Any>(BindingAction { })
    val amountAfterTextChanged = TextViewBindingAdapter.AfterTextChanged { s ->
        isBtnEnable.set(ObjectUtils.isNotEmpty(s))
    }
    val nextOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(WithdrawSuccessAct::class.java)
    })

}