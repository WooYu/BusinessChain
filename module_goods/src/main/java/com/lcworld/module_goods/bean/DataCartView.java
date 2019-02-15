package com.lcworld.module_goods.bean;

import java.util.List;

public class DataCartView {
    private DataPriceDetailVo total_price;
    private List<DataCartVo> cart_list;

    public DataPriceDetailVo getTotal_price() {
        return total_price;
    }

    public void setTotal_price(DataPriceDetailVo total_price) {
        this.total_price = total_price;
    }

    public List<DataCartVo> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<DataCartVo> cart_list) {
        this.cart_list = cart_list;
    }
}
