package com.lcworld.module_home;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultPageImp;
import com.lcworld.module_home.bean.*;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.List;

public interface ApiServiceInterf {
    //会员
    //会员站内消息历史相关API
    //查询会员站内消息历史列表
    @GET("members/member-nocice-logs")
    Observable<RequestResultPageImp<DataMessageNoticeLog>> membersMemberNociceLogs(@Query("read") int read
            , @Query("page_no") int page_no, @Query("page_size") int page_size, @Query("type") int type);

    //删除会员站内消息历史
//    @Delete("members/member-nocice-logs/{ids}")


    //楼层
    //焦点图相关API
    //查询焦点图列表
    @GET("focus-pictures")
    Observable<RequestResult<List<DataFocusPictures>>> focusPictures(@Query("client_type") String client_type, @Query("page_type") String page_type);

    //热门关键字相关API
    //查询热门关键字列表
    @GET("pages/hot-keywords")
    Observable<RequestResult<List<DataHotKeyword>>> pagesHotKeywords(@Query("num") int num);


    //首页
    //招商加盟
    //获取招商加盟信息
    @GET("home/group")
    Observable<RequestResult<DataGroupDTO>> homeGroup();

    //申请加盟
    @GET("home/group")
    Observable<RequestResult<DataGroupDTO>> homeGroup(
            @Query("groupName") String groupName, @Query("groupPhone") String groupPhone, @Query("userName") String userName
            , @Query("userPosition") String userPosition, @Query("userPhone") String userPhone
            , @Query("creditCode") String creditCode, @Query("groupAddress") String groupAddress
            , @Query("businessLicense") String businessLicense, @Query("groupLog") String groupLog);


    //拼团采集
    //广告图列表
    @GET("home/pintuan")
    Observable<RequestResult<List<DataSpellDeals>>> homePintuan();


    //商品
    //商品检索相关API
    //查询商品列表
    @GET("goods/search")
    Observable<RequestResultPageImp<DataGoodsInfo>> goodsSearch(@Query("page_no") int page_no, @Query("page_size") int page_size
            , @Query("keyword") String keyword, @Query("category") Integer category, @Query("brand") Integer brand
            , @Query("price") String price, @Query("sort") String sort, @Query("prop") String prop
            , @Query("seller_id") Integer seller_id, @Query("shop_cat_id") Integer shop_cat_id);

    //查询会员商品列表
    @GET("goods/search/queryGoods")
    Observable<RequestResultPageImp<DataGoodsInfo>> goodsSearchQueryGoods(@Query("page_no") int page_no, @Query("page_size") int page_size);

    //查询商品分词对应数量
    @GET("goods/search/words")
    Observable<RequestResult<List<DataGoodsWords>>> goodsSearchWords(@Query("keyword") String keyword);

    //标签商品相关API
    //查询标签商品列表
    @GET("goods/tags/{mark}/goods")
    Observable<RequestResult<List<DataGoodsInfo>>> tagGoods(@Path("mark") String mark, @Query("num") int num);


    //default
    //上传图片api
    //文件上传
    @Headers({"url_name:module_base"})
    @Multipart
    @POST("uploaders")
    Observable<RequestResult<DataFileVo>> fileUploaders(
            @Part("scene") String scene,
            @Part MultipartBody.Part file);

//    @Part MultipartBody.Part file, @Query("scene") String scene
}
