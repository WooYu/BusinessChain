package com.lcworld.module_order.bean;

public class DataCouponVo {
    private double amount;//金额
    private int coupon_id;//优惠卷id
    private double coupon_threshold_price;//优惠券门槛金额
    private int enable;//是否可用，1为可用，0为不可用
    private long end_time;//有效期
    private int member_coupon_id;//会员优惠卷id
    private int selected;//是否被选中，1为选中，0为不选中
    private int seller_id;//卖家id
    private String use_term;//使用条件

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public double getCoupon_threshold_price() {
        return coupon_threshold_price;
    }

    public void setCoupon_threshold_price(double coupon_threshold_price) {
        this.coupon_threshold_price = coupon_threshold_price;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public int getMember_coupon_id() {
        return member_coupon_id;
    }

    public void setMember_coupon_id(int member_coupon_id) {
        this.member_coupon_id = member_coupon_id;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getUse_term() {
        return use_term;
    }

    public void setUse_term(String use_term) {
        this.use_term = use_term;
    }
}
