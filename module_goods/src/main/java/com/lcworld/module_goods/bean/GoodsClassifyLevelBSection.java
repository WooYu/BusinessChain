package com.lcworld.module_goods.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class GoodsClassifyLevelBSection extends SectionEntity<GoodsClassifyLevelBBean> {
    private String name;

    public GoodsClassifyLevelBSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public GoodsClassifyLevelBSection(GoodsClassifyLevelBBean goodsClassifyLevelBBean) {
        super(goodsClassifyLevelBBean);
    }

    public String getName() {
        return header;
    }

    public void setName(String name) {
        this.header = name;
        this.name = name;
    }

}
