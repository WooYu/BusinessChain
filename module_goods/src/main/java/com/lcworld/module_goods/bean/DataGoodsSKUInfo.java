package com.lcworld.module_goods.bean;

import java.util.List;

/**
 * 商品属性信息
 */
public class DataGoodsSKUInfo {

    /**
     * cost : 0
     * enable_quantity : 0
     * goods_type : string
     * price : 0
     * quantity : 0
     * sn : string
     * spec_list : [{"spec_id":0,"spec_image":"string","spec_name":"string","spec_type":0,"spec_value":"string","spec_value_id":0}]
     * template_id : 0
     * version : 0
     * weight : 0
     */

    private double cost;
    private int enable_quantity;
    private String goods_type;
    private double price;
    private int quantity;
    private String sn;
    private int template_id;
    private int version;
    private double weight;
    private List<DataGoodsSpecInfo> spec_list;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getEnable_quantity() {
        return enable_quantity;
    }

    public void setEnable_quantity(int enable_quantity) {
        this.enable_quantity = enable_quantity;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
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

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<DataGoodsSpecInfo> getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(List<DataGoodsSpecInfo> spec_list) {
        this.spec_list = spec_list;
    }
}
