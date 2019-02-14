package com.lcworld.module_goods;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.module_goods.bean.DataGoodsDetailInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServiceInterf {
    //商品
    //商品相关API
    //浏览商品的详情,静态部分使用
    @GET("goods/{goods_id}")
    Observable<RequestResult<DataGoodsDetailInfo>> goodsDetail(@Path("goods_id") int goods_id);

}
