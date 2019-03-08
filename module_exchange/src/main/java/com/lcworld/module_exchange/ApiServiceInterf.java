package com.lcworld.module_exchange;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.module_exchange.model.AccountBalance;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface ApiServiceInterf {
    //钱包
    //余额
    @GET("order/balance/pay/selectBalance")
    Observable<RequestResult<AccountBalance>> requestBalance();

    //创建订单
    @GET("order/balance/pay/create")
    Observable<RequestResultImp> createOrder(@Query("price") String price);

    //对一个交易发起支付
    @GET("order/balance/pay/payTrade")
    Observable<RequestResultImp> payTrade(@Query("sn") String sn, @Query("payment_plugin_id ") String paymentPluginId);

    //充值
    @GET("order/balance/pay/recharge")
    Observable<RequestResultImp> recharge(@Query("order_id") String orderId, @Query("type ") int type);

}
