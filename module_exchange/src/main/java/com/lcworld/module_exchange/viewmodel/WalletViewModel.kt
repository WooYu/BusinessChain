package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.activity.RechargeAct
import com.lcworld.module_exchange.activity.BankCardListAct
import com.lcworld.module_exchange.activity.NoBankcardAct
import com.lcworld.module_exchange.activity.WithDrawAct
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class WalletViewModel(application: Application) : BaseViewModelEnhance(application) {
    val balance = ObservableField("￥")
    val income = ObservableField("￥")
    val rechargeOnClickCommand = BindingCommand<Any>(BindingAction { doRecharge() })
    val withDrawOnClickCommand = BindingCommand<Any>(BindingAction { doWithDraw() })
    val bankcardOnClickCommand = BindingCommand<Any>(BindingAction { bankcardItemClick() })
    val billOnClickCommand = BindingCommand<Any>(BindingAction { billItemClick() })
    val orderOnClickCommand = BindingCommand<Any>(BindingAction { orderItemClick() })
    val chainOnClickCommand = BindingCommand<Any>(BindingAction { chainItemClick() })

    private fun doRecharge() {
        startActivity(RechargeAct::class.java)
    }

    private fun doWithDraw() {
        startActivity(WithDrawAct::class.java)
    }

    private fun bankcardItemClick() {
        startActivity(NoBankcardAct::class.java)
    }

    private fun billItemClick() {
        startActivity(BankCardListAct::class.java)
    }

    private fun orderItemClick() {

    }

    private fun chainItemClick() {

    }
}