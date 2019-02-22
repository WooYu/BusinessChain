package com.lcworld.module_order.bean;

public class DataPintuanPayOrderInfoDTO {


    /**
     * order_code : PT201902226
     * pay_price : 2909
     */

    private String order_code;//订单码
    private double pay_price;//支付金额

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public double getPay_price() {
        return pay_price;
    }

    public void setPay_price(double pay_price) {
        this.pay_price = pay_price;
    }
}
