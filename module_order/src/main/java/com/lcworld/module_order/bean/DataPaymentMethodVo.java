package com.lcworld.module_order.bean;

/**
 * 支付方式
 */
public class DataPaymentMethodVo {

    private String image;//支付方式图片
    private int is_retrace;//是否支持原路退回
    private String method_name;//支付方式名称
    private String plugin_id;//支付插件名称

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIs_retrace() {
        return is_retrace;
    }

    public void setIs_retrace(int is_retrace) {
        this.is_retrace = is_retrace;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getPlugin_id() {
        return plugin_id;
    }

    public void setPlugin_id(String plugin_id) {
        this.plugin_id = plugin_id;
    }
}
