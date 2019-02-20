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
