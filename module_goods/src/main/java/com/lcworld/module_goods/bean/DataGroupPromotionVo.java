package com.lcworld.module_goods.bean;

import java.util.List;

/**
 * 促销活动集合（包含商品
 */
public class DataGroupPromotionVo {

    private String activity_detail;//活动详情
    private int checked;//活动是否选中.1为选中,0未选中
    private double discount_price;//优惠金额
    private int is_group;//是否是组合活动,1为是组合活动，2为单品活动
    private String promotion_type;//促销活动工具类型
    private List<DataSKUVo> sku_list;//商品集合
    private double spread_price;//差额
    private double subtotal;//商品价格小计

    public String getActivity_detail() {
        return activity_detail;
    }

    public void setActivity_detail(String activity_detail) {
        this.activity_detail = activity_detail;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public int getIs_group() {
        return is_group;
    }

    public void setIs_group(int is_group) {
        this.is_group = is_group;
    }

    public String getPromotion_type() {
        return promotion_type;
    }

    public void setPromotion_type(String promotion_type) {
        this.promotion_type = promotion_type;
    }

    public List<DataSKUVo> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<DataSKUVo> sku_list) {
        this.sku_list = sku_list;
    }

    public double getSpread_price() {
        return spread_price;
    }

    public void setSpread_price(double spread_price) {
        this.spread_price = spread_price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
