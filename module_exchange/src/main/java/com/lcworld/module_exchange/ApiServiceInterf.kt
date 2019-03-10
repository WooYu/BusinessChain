package com.lcworld.module_exchange

import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RequestResultImp
import com.lcworld.module_exchange.model.AccountBalance
import com.lcworld.module_exchange.model.BillListEntity
import com.lcworld.module_exchange.model.RechargeResult
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServiceInterf {
    //钱包
    //余额
    @GET("order/balance/pay/selectBalance")
    fun requestBalance(): Observable<RequestResult<AccountBalance>>

    //创建订单
    @GET("order/balance/pay/create")
    fun createOrder(@Query("price") price: String): Observable<RequestResultImp>

    //对一个交易发起支付
    @GET("order/balance/pay/payTrade")
    fun payTrade(@Query("sn") sn: String, @Query("payment_plugin_id ") paymentPluginId: String): Observable<RequestResultImp>

    //充值
    @GET("order/balance/pay/recharge")
    fun recharge(@Query("order_id") orderId: String, @Query("type") type: Int): Observable<RequestResult<RechargeResult>>

    //微信回调
    @GET("order/balance/pay/wxCallback")
    fun wxCallback(): Observable<RequestResultImp>

    //支付宝回调
    @GET("order/balance/pay/zfbCallBack")
    fun zfbCallBack(): Observable<RequestResultImp>

    //查询会员账单列表
    @GET("trade/bill")
    fun getBillList(@Query("page_no") pageNo: Int,@Query("start_time") startTime: String,
                    @Query("end_time") endTime: String,@Query("page_size") pageSize: Int = 10): Observable<RequestResult<BillListEntity>>

}
