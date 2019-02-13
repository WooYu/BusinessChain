package com.lcworld.module_home.bean;

public class DataGoodsInfo {

    /**
     * big : string
     * buy_count : 0
     * goods_id : 0
     * goods_name : string
     * price : 0
     * quantity : 0
     * sn : string
     * thumbnail : string
     */

    private String big;
    private int buy_count;
    private int goods_id;
    private String goods_name;
    private double price;
    private int quantity;
    private String sn;
    private String thumbnail;

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
