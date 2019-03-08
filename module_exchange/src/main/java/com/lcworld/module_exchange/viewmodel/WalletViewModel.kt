package com.lcworld.module_exchange.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_exchange.ApiServiceInterf
import com.lcworld.module_exchange.activity.BillListAct
import com.lcworld.module_exchange.activity.RechargeAct
import com.lcworld.module_exchange.activity.WithDrawAct
import com.lcworld.module_exchange.model.AccountBalance
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.goldze.mvvmhabit.utils.KLog

class WalletViewModel(application: Application) : BaseViewModelEnhance(application) {
    val balance = ObservableField("￥")
    val income = ObservableField("结算收益￥")
    val rechargeOnClickCommand = BindingCommand<Any>(BindingAction { doRecharge() })
    val withDrawOnClickCommand = BindingCommand<Any>(BindingAction { doWithDraw() })
    val bankcardOnClickCommand = BindingCommand<Any>(BindingAction { bankcardItemClick() })
    val billOnClickCommand = BindingCommand<Any>(BindingAction { billItemClick() })
    val orderOnClickCommand = BindingCommand<Any>(BindingAction { orderItemClick() })
    val chainOnClickCommand = BindingCommand<Any>(BindingAction { chainItemClick() })

    //充值点击跳转
    private fun doRecharge() {
        startActivity(RechargeAct::class.java)
    }

    //提现点击跳转
    private fun doWithDraw() {
        startActivity(WithDrawAct::class.java)
    }

    private fun bankcardItemClick() {
    }

    //账单点击跳转
    private fun billItemClick() {
        startActivity(BillListAct::class.java)
    }

    //订单点击跳转
    private fun orderItemClick() {
        ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Review).navigation();
    }

    private fun chainItemClick() {

    }


    //请求余额
    @SuppressLint("CheckResult")
    fun requestBalance() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .requestBalance()
            .compose(RxUtilsEnhanced.explicitTransform())
            .subscribe({
                val result = it as RequestResult<AccountBalance>
                balance.set("￥${result.data.totle_price}")
                income.set("结算收益￥${result.data.account_price}")
            }) { KLog.e(it.message.toString()) }

    }
}