package com.lcworld.module_goods.bean;

import java.util.List;

/**
 * 商品详情
 */
public class DataGoodsDetailInfo {

    /**
     * category_id : 0
     * category_name : string
     * disabled : 0
     * enable_quantity : 0
     * gallery_list : [{"img_id":0,"original":"string","sort":0}]
     * goods_id : 0
     * goods_name : string
     * goods_off : 0
     * goods_transfee_charge : 0
     * grade : 0
     * intro : string
     * is_auth : 0
     * last_modify : 0
     * market_enable : 0
     * meta_description : string
     * meta_keywords : string
     * page_title : string
     * param_list : [{"group_id":0,"group_name":"string","params":[{"group_id":0,"is_index":0,"option_list":["string"],"options":"string","param_id":0,"param_name":"string","param_type":0,"param_value":"string","required":0}]}]
     * price : 0
     * quantity : 0
     * seller_id : 0
     * sku_list : [{"cost":0,"enable_quantity":0,"goods_type":"string","price":0,"quantity":0,"sn":"string","spec_list":[{"spec_id":0,"spec_image":"string","spec_name":"string","spec_type":0,"spec_value":"string","spec_value_id":0}],"template_id":0,"version":0,"weight":0}]
     * sn : string
     * template_id : 0
     * thumbnail : string
     * weight : 0
     */

    private Integer category_id;
    private String category_name;
    private Integer disabled;
    private Integer enable_quantity;
    private Integer goods_id;
    private String goods_name;
    private Integer goods_off;
    private Integer goods_transfee_charge;
    private Integer grade;
    private String intro;
    private Integer is_auth;
    private Long last_modify;
    private Integer market_enable;
    private String meta_description;
    private String meta_keywords;
    private String page_title;
    private Double price;
    private Integer quantity;
    private Integer seller_id;
    private String sn;
    private Integer template_id;
    private String thumbnail;
    private Double weight;
    private int goods_type;
    private Double sprice;
    private List<DataGoodsGalleryInfo> gallery_list;
    private List<DataGoodsParamsGroupVOInfo> param_list;
    private List<DataGoodsSKUInfo> sku_list;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getEnable_quantity() {
        return enable_quantity;
    }

    public void setEnable_quantity(Integer enable_quantity) {
        this.enable_quantity = enable_quantity;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_off() {
        return goods_off;
    }

    public void setGoods_off(Integer goods_off) {
        this.goods_off = goods_off;
    }

    public Integer getGoods_transfee_charge() {
        return goods_transfee_charge;
    }

    public void setGoods_transfee_charge(Integer goods_transfee_charge) {
        this.goods_transfee_charge = goods_transfee_charge;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(Integer is_auth) {
        this.is_auth = is_auth;
    }

    public Long getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(Long last_modify) {
        this.last_modify = last_modify;
    }

    public Integer getMarket_enable() {
        return market_enable;
    }

    public void setMarket_enable(Integer market_enable) {
        this.market_enable = market_enable;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(int goods_type) {
        this.goods_type = goods_type;
    }

    public Double getSprice() {
        return sprice;
    }

    public void setSprice(Double sprice) {
        this.sprice = sprice;
    }

    public List<DataGoodsGalleryInfo> getGallery_list() {
        return gallery_list;
    }

    public void setGallery_list(List<DataGoodsGalleryInfo> gallery_list) {
        this.gallery_list = gallery_list;
    }

    public List<DataGoodsParamsGroupVOInfo> getParam_list() {
        return param_list;
    }

    public void setParam_list(List<DataGoodsParamsGroupVOInfo> param_list) {
        this.param_list = param_list;
    }

    public List<DataGoodsSKUInfo> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<DataGoodsSKUInfo> sku_list) {
        this.sku_list = sku_list;
    }
}
