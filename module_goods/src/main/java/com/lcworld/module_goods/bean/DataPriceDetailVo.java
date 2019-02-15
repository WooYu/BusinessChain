package com.lcworld.module_goods.bean;

/**
 * 价格明细
 */
public class DataPriceDetailVo {

    private double cash_back;//返现金额，不含优惠卷
    private double coupon_price;//优惠卷抵扣金额
    private double discount_price;//优惠金额
    private double exchange_point;// 使用的积分
    private double freight_price;//配送费
    private double full_minus;//满减金额
    private double goods_price;// 商品价格，优惠后的
    private int is_free_freight;//是否免运费,1为免运费
    private double original_price;//商品原价，没有优惠过的
    private double total_price;//总价

    public double getCash_back() {
        return cash_back;
    }

    public void setCash_back(double cash_back) {
        this.cash_back = cash_back;
    }

    public double getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(double coupon_price) {
        this.coupon_price = coupon_price;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public double getExchange_point() {
        return exchange_point;
    }

    public void setExchange_point(double exchange_point) {
        this.exchange_point = exchange_point;
    }

    public double getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(double freight_price) {
        this.freight_price = freight_price;
    }

    public double getFull_minus() {
        return full_minus;
    }

    public void setFull_minus(double full_minus) {
        this.full_minus = full_minus;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public int getIs_free_freight() {
        return is_free_freight;
    }

    public void setIs_free_freight(int is_free_freight) {
        this.is_free_freight = is_free_freight;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
