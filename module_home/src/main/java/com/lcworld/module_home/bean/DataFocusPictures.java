package com.lcworld.module_home.bean;

public class DataFocusPictures {

    /**
     * client_type : string
     * operation_param : string
     * operation_type : string
     * order_num : 0
     * pic_url : string
     */

    private String client_type;
    private String operation_param;
    private String operation_type;
    private int order_num;
    private String pic_url;
    @Deprecated
    private String operation_url;
    private String id;

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public String getOperation_param() {
        return operation_param;
    }

    public void setOperation_param(String operation_param) {
        this.operation_param = operation_param;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getOperation_url() {
        return operation_url;
    }

    public void setOperation_url(String operation_url) {
        this.operation_url = operation_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
