package com.lcworld.module_goods.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.module_goods.bean.GoodsClassifyLevelBBean;
import com.lcworld.module_goods.bean.GoodsClassifyLevelBSection;

public class GoodsClassifyModel extends BaseViewModelEnhance {
    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";

    public GoodsClassifyModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getSampleData();
    }

    //给RecyclerView添加ObservableList
    public ObservableList<GoodsClassifyLevelBSection> observableList = new ObservableArrayList<>();

    public void getSampleData() {
        observableList.add(new GoodsClassifyLevelBSection(true, "Section 1"));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "家居服")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "棉袜船袜")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "睡衣")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "速生媒体")));
        observableList.add(new GoodsClassifyLevelBSection(true, "Section 2"));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "办公文具")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "宠物用品")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "厨房用品")));
        observableList.add(new GoodsClassifyLevelBSection(true, "Section 3"));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "跑步鞋")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "皮鞋")));
        observableList.add(new GoodsClassifyLevelBSection(true, "Section 4"));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "键盘")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "鼠标")));
        observableList.add(new GoodsClassifyLevelBSection(true, "Section 5"));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "双肩包")));
        observableList.add(new GoodsClassifyLevelBSection(new GoodsClassifyLevelBBean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, "单肩包")));
    }

}
