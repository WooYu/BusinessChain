package com.lcworld.module_goods.bean;

import java.util.List;
import java.util.Map;

//购物车展示Vo
public class DataCartVo {
    private String cart_type;
    private int checked;//购物车页展示时，店铺内的商品是否全选状态.1为店铺商品全选状态,0位非全选
    private List<DataCouponVo> coupon_list;
    private List<DataCouponVo> gift_coupon_list;
    private List<DataGiftVo> gift_list;
    private int gift_point;//赠送积分
    private int invalid;//是否失效：0:正常 1:已失效
    private DataPriceDetailVo price;
    private List<DataGroupPromotionVo> promotion_list;
    private String promotion_notice;//已参与的的促销活动提示，直接展示给客户
    private List<DataPromotonRule> rule_list;
    private int seller_id;//卖家id
    private String seller_name;//卖家店名
    private Map<String, DataShipTemplateChildDTO> ship_template_child_map;
    private int shipping_type_id;//选中的配送方式id
    private String shipping_type_name;//选中的配送方式名称
    private List<DataSKUVo> sku_list;
    private double weight;//购物车重量

    public String getCart_type() {
        return cart_type;
    }

    public void setCart_type(String cart_type) {
        this.cart_type = cart_type;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public List<DataCouponVo> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<DataCouponVo> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public List<DataCouponVo> getGift_coupon_list() {
        return gift_coupon_list;
    }

    public void setGift_coupon_list(List<DataCouponVo> gift_coupon_list) {
        this.gift_coupon_list = gift_coupon_list;
    }

    public List<DataGiftVo> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<DataGiftVo> gift_list) {
        this.gift_list = gift_list;
    }

    public int getGift_point() {
        return gift_point;
    }

    public void setGift_point(int gift_point) {
        this.gift_point = gift_point;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    public DataPriceDetailVo getPrice() {
        return price;
    }

    public void setPrice(DataPriceDetailVo price) {
        this.price = price;
    }

    public List<DataGroupPromotionVo> getPromotion_list() {
        return promotion_list;
    }

    public void setPromotion_list(List<DataGroupPromotionVo> promotion_list) {
        this.promotion_list = promotion_list;
    }

    public String getPromotion_notice() {
        return promotion_notice;
    }

    public void setPromotion_notice(String promotion_notice) {
        this.promotion_notice = promotion_notice;
    }

    public List<DataPromotonRule> getRule_list() {
        return rule_list;
    }

    public void setRule_list(List<DataPromotonRule> rule_list) {
        this.rule_list = rule_list;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public Map<String, DataShipTemplateChildDTO> getShip_template_child_map() {
        return ship_template_child_map;
    }

    public void setShip_template_child_map(Map<String, DataShipTemplateChildDTO> ship_template_child_map) {
        this.ship_template_child_map = ship_template_child_map;
    }

    public int getShipping_type_id() {
        return shipping_type_id;
    }

    public void setShipping_type_id(int shipping_type_id) {
        this.shipping_type_id = shipping_type_id;
    }

    public String getShipping_type_name() {
        return shipping_type_name;
    }

    public void setShipping_type_name(String shipping_type_name) {
        this.shipping_type_name = shipping_type_name;
    }

    public List<DataSKUVo> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<DataSKUVo> sku_list) {
        this.sku_list = sku_list;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
