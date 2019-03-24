package com.lcworld.module_exchange.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.alipay.sdk.app.PayTask
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.model.AliPayResult
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityRechargeBinding
import com.lcworld.module_exchange.viewmodel.RechargeViewModel
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.startActivity
import org.json.JSONException
import org.json.JSONObject

class RechargeAct : BaseActivityEnhance<ExchangeActivityRechargeBinding, RechargeViewModel>() {
    private lateinit var api: IWXAPI
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_recharge
    override fun initViewModel(): RechargeViewModel = ViewModelProviders.of(this).get(RechargeViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_recharge)
        binding.layoutTitle.tvRight.text = getString(R.string.exchange_recharge_record)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.layoutTitle.tvRight.setOnClickListener { startActivity<RechargeRecordAct>() }
        binding.etRechargeInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) viewModel.onFocusChange()
        }
    }

    override fun initViewObservable() {
        super.initViewObservable()
        api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1")
        initObservableAlipayOrderInfo()
        initObservableWechatOrderInfo()
    }

    private fun initObservableAlipayOrderInfo() {
        viewModel.valueAlipayOrderInfo.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                viewModel.valueAlipayOrderInfo.get()?.let {
                    alipaySendRequest(it.gateway_url)
                }
            }
        })
    }

    private fun initObservableWechatOrderInfo() {
        viewModel.valueWechatOrderInfo.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                wechatSendRequest(viewModel.valueWechatOrderInfo.get().toString())
            }
        })
    }

    //解析微信支付的参数
    private fun wechatSendRequest(orderinfo: String?) {
        try {
            val json = JSONObject(orderinfo)
            if (!json.has("retcode")) {
                return
            }
            val req = PayReq()
            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
            req.appId = json.getString("appid")
            req.partnerId = json.getString("partnerid")
            req.prepayId = json.getString("prepayid")
            req.nonceStr = json.getString("noncestr")
            req.timeStamp = json.getString("timestamp")
            req.packageValue = json.getString("package")
            req.sign = json.getString("sign")
            req.extData = "app data" // optional
            Toast.makeText(this, "正常调起支付", Toast.LENGTH_SHORT).show()
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            api.sendReq(req)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }


    //支付宝发送请求参数
    @SuppressLint("CheckResult")
    private fun alipaySendRequest(orderInfo: String) {
        io.reactivex.Observable.create<Map<String, String>> { e ->
            val alipay = PayTask(this@RechargeAct)
            val result = alipay.payV2(orderInfo, true)
            e.onNext(result)
            e.onComplete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> alipayProcessingPaymentResult(result) }
    }

    //处理支付结果
    private fun alipayProcessingPaymentResult(result: Map<String, String>) {
        val payResult = AliPayResult(result)
        /**
         * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        val resultInfo = payResult.result// 同步返回需要验证的信息
        val resultStatus = payResult.resultStatus
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show()
            viewModel.zfbCallBack()
        } else {
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show()
        }

    }
}