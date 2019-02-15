package com.lcworld.module_goods.bean;

public class DataGiftVo {
    private int actual_store;//库存
    private long create_time;//活动时间
    private int disabled;//是否禁用
    private int enable_store;//可用库存
    private String gift_img;//赠品图片
    private String gift_name;//赠品名称
    private double gift_price;//赠品金额
    private int gift_type;//赠品类型
    private int goods_id;//活动商品id
    private int seller_id;//商家id
    private int  gift_id;//赠品ID
    private int gift_num;//数量

    public int getActual_store() {
        return actual_store;
    }

    public void setActual_store(int actual_store) {
        this.actual_store = actual_store;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public int getEnable_store() {
        return enable_store;
    }

    public void setEnable_store(int enable_store) {
        this.enable_store = enable_store;
    }

    public String getGift_img() {
        return gift_img;
    }

    public void setGift_img(String gift_img) {
        this.gift_img = gift_img;
    }

    public String getGift_name() {
        return gift_name;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public double getGift_price() {
        return gift_price;
    }

    public void setGift_price(double gift_price) {
        this.gift_price = gift_price;
    }

    public int getGift_type() {
        return gift_type;
    }

    public void setGift_type(int gift_type) {
        this.gift_type = gift_type;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public int getGift_num() {
        return gift_num;
    }

    public void setGift_num(int gift_num) {
        this.gift_num = gift_num;
    }
}
