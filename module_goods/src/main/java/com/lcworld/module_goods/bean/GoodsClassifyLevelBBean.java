package com.lcworld.module_goods.bean;

/**
 * 产品分类级别一：服饰、母婴等
 */
public class GoodsClassifyLevelBBean {
    private String imgurl;
    private String name;

    public GoodsClassifyLevelBBean(String imgurl, String name) {
        this.imgurl = imgurl;
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
