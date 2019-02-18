package com.lcworld.module_order;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.module_order.bean.*;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiServiceInterf {
    //会员
    //会员地址相关API
    //查询当前会员的某个地址
    @GET("members/address/{id}")
    Observable<RequestResult<DataMemberAddress>> membersAddress(@Path("id") int id);

    //交易
    //购物车接口模块
    //获取结算页面购物车详情
    @GET("trade/carts/checked")
    Observable<RequestResult<DataCartView>> tradeCartsChecked();

    //结算参数接口模块
    //获取结算参数
    @GET("trade/checkout-params")
    Observable<RequestResult<DataOrderDTO>> tradeCheckoutParams();


    //交易接口模块
    //创建交易
    @POST("trade/create")
    Observable<RequestResult<DataTradeVo>> tradeCreate(@Query("client") String client);

    //拼团采集
    //拼团订单API
    //提交订单
    @POST("pintuan/order")
    Observable<RequestResultImp> pinTuanOrder(@Query("sku_id") int sku_id, @Query("proportion_id") int proportion_id, @Query("count") int count);

    //计算预期利润和商钻
    @GET("pintuan/order/getProfit")
    Observable<RequestResult<DataOrderProfitDiamond>> pinTuanOrderGetProfit(@Query("sku_id") int sku_id, @Query("proportion_id") int proportion_id, @Query("count") int count);

    //获取拼团年化收益率列表
    @GET("pintuan/order/getProportion")
    Observable<RequestResult<List<DataProportionDTO>>> pinTuanOrderGetProportion();


}
