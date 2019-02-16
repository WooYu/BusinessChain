package com.lcworld.module_order.bean;

import java.util.List;

public class DataTradeVo {

    private DataConsigneeVo consignee;
    private List<DataCouponVo> coupon_list;
    private List<DataGiftVo> gift_list;
    private int member_id;//会员id
    private String member_name;//会员昵称
    private List<DataOrderDTO> order_list;
    private String payment_type;//支付方式
    private DataPriceDetailVo price_detail;
    private String trade_sn;

    public DataConsigneeVo getConsignee() {
        return consignee;
    }

    public void setConsignee(DataConsigneeVo consignee) {
        this.consignee = consignee;
    }

    public List<DataCouponVo> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<DataCouponVo> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public List<DataGiftVo> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<DataGiftVo> gift_list) {
        this.gift_list = gift_list;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public List<DataOrderDTO> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<DataOrderDTO> order_list) {
        this.order_list = order_list;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public DataPriceDetailVo getPrice_detail() {
        return price_detail;
    }

    public void setPrice_detail(DataPriceDetailVo price_detail) {
        this.price_detail = price_detail;
    }

    public String getTrade_sn() {
        return trade_sn;
    }

    public void setTrade_sn(String trade_sn) {
        this.trade_sn = trade_sn;
    }
}
