package com.lcworld.module_goods;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.module_goods.bean.*;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiServiceInterf {
    //交易
    //购物车接口模块
    //向购物车中添加一个产品
    @POST("trade/carts")
    Observable<RequestResult<DataSKUVo>> tradeCarts(@Query("sku_id") int sku_id, @Query("num") int num);

    //立即购买
    @POST("trade/carts/buy")
    Observable<RequestResultImp> tradeCartsBuy(@Query("sku_id") int sku_id, @Query("num") int num);

    //获取结算页面购物车详情
    @GET("trade/carts/checked")
    Observable<RequestResult<DataCartView>> tradeCartsChecked();

    //交易接口
    //创建交易
    @POST("trade/create")
    Observable<DataTradeVo> tradeCreate(@Query("client") String client);

    //商品
    //商品相关API
    //浏览商品的详情,静态部分使用
    @GET("goods/{goods_id}")
    Observable<RequestResult<DataGoodsDetailInfo>> goodsDetail(@Path("goods_id") int goods_id);

    //获取sku信息，商品详情页动态部分
    @GET("goods/{goods_id}/skus")
    Observable<RequestResult<List<DataSKUVo>>> goodsDetailSKUS(@Path("goods_id") int goods_id);

    //拼团采集
    //拼团订单API
    //提交订单
    @POST("pintuan/order")
    Observable<RequestResultImp> pinTunaOrder(@Query("sku_id") int sku_id, @Query("proportion_id") int proportion_id, @Query("count") int count);

    //计算预期利润和商钻
    @GET("pintuan/order/getProfit")
    Observable<RequestResult<DataOrderProfitDiamond>> pinTunaOrderGetProfit(@Query("sku_id") int sku_id, @Query("proportion_id") int proportion_id, @Query("count") int count);

    //获取拼团年化收益率列表
    @GET("pintuan/order/getProportion")
    Observable<RequestResult<List<DataProportionDTO>>> pinTunaOrderGetProportion();

}
