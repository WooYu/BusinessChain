package com.lcworld.module_home;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultPageImp;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.lcworld.module_home.bean.DataSpellDeals;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiServiceInterf {
    //楼层
    //焦点图相关API
    //查询焦点图列表
    @GET("focus-pictures")
    Observable<RequestResult<List<DataFocusPictures>>> focusPictures(@Query("client_type") String client_type, @Query("page_type") String page_type);

    //首页
    //拼团采集
    //广告图列表
    @GET("home/pintuan")
    Observable<RequestResult<List<DataSpellDeals>>> homePintuan();


    //商品
    //商品检索相关API
    //查询会员商品列表
    @GET("goods/search/queryGoods")
    Observable<RequestResultPageImp<DataGoodsInfo>> goodsSearchQueryGoods(@Query("page_no") int page_no, @Query("page_size") int page_size);

    //标签商品相关API
    //查询标签商品列表
    @GET("goods/tags/{mark}/goods")
    Observable<RequestResult<List<DataGoodsInfo>>> tagGoods(@Path("mark") String mark, @Query("num") int num);

}
