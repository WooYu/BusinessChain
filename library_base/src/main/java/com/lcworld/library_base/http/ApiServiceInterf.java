package com.lcworld.library_base.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceInterf {
    //系统设置
    //系统文本获取
    //根据类型获取文本
    @GET("sys/txt")
    Observable<RequestResultImp> sysTxt(@Query("type") int type);
}
