package com.lcworld.module_order;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.module_order.bean.*;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.List;

public interface ApiServiceInterf {
    //会员
    //会员地址相关API
    //添加会员地址  , @Query("town_id") int town_id, @Query("town") String town,
    @POST("members/address")
    Observable<RequestResult<DataMemberAddress>> membersAddressCreate(@Query("name") String name, @Query("mobile") String mobile
            , @Query("province_id") int province_id, @Query("province") String province, @Query("city_id") int city_id
            , @Query("city") String city, @Query("county_id") int county_id, @Query("county") String county
            , @Query("addr") String addr, @Query("def_addr") int def_addr);

    //查询当前会员的某个地址
    @GET("members/address/{id}")
    Observable<RequestResult<DataMemberAddress>> membersAddress(@Path("id") int id);

    //修改会员地址
    @PUT("members/address/{id}")
    Observable<RequestResult<DataMemberAddress>> memberAddressModify(
            @Path("id") int id, @Query("name") String name, @Query("mobile") String mobile
            , @Query("province_id") int province_id, @Query("province") String province, @Query("city_id") int city_id
            , @Query("city") String city, @Query("county_id") int county_id, @Query("county") String county
            , @Query("addr") String addr, @Query("def_addr") int def_addr);

    //删除会员地址
    @DELETE("members/address/{id}")
    Observable<RequestResultImp> membersAddressDelete(@Path("id") int id);

    //设置地址为默认
    @PUT("members/address/{id}/default")
    Observable<RequestResult<DataMemberAddress>> memberAddressDefault(@Path("id") int id);

    //查询当前会员地址列表
    @GET("members/addresses")
    Observable<RequestResult<List<DataMemberAddress>>> membersAddresses();


    //交易
    //购物车接口模块
    //获取结算页面购物车详情
    @GET("trade/carts/checked")
    Observable<RequestResult<DataCartView>> tradeCartsChecked();

    //结算参数接口模块
    //获取结算参数
    @GET("trade/checkout-params")
    Observable<RequestResult<DataOrderDTO>> tradeCheckoutParams();

    //设置收货地址id
    @POST("trade/checkout-params/address-id/{address_id}")
    Observable<RequestResultImp> tradeCheckoutParamsAddressId(@Path("address_id") int address_id);


    //交易接口模块
    //创建交易
    @POST("trade/create")
    @FormUrlEncoded
    Observable<RequestResult<DataTradeVo>> tradeCreate(@Field("client") String client);


    //支付
    //订单支付API
    //APP对一个交易发起支付
    @GET("order/pay/app/{trade_type}/{sn}")
    Observable<RequestResultImp> orderPayInitiate(@Path("sn") String sn, @Path("trade_type") String trade_type
            , @Query("payment_plugin_id") String payment_plugin_id, @Query("pay_mode") String pay_mode
            , @Query("client_type") String client_type);

    //主动查询支付结果
    @GET("order/pay/order/pay/query/{trade_type}/{sn}")
    Observable<RequestResultImp> orderPayQueryResult(@Path("sn") String sn, @Query("trade_type") String trade_type
            , @Query("payment_plugin_id") String payment_plugin_id, @Query("pay_mode") String pay_mode
            , @Query("client_type") String client_type);

    //查询支持的支付方式
    @GET("order/pay/{client_type}")
    Observable<RequestResult<List<DataPaymentMethodVo>>> orderPayMethod(@Path("client_type") String client_type);


    //商币支付API
    //对一个交易发起支付
    @GET("order/shangbi/pay/{payment_plugin_id}/{sn}")
    Observable<RequestResultImp> orderShangBiPayInitiate(@Path("sn") String sn, @Path("payment_plugin_id") String payment_plugin_id);



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


    //default
    //地区API
    //获取某地区的子地区
    @Headers({"url_name:module_base"})
    @GET("regions/{id}/children")
    Observable<RequestResult<List<DataRegions>>> regionsIdChildren(@Path("id") int id);

}
