package com.lcworld.module_goods.bean;

/**
 * 收益和商钻数
 */
public class DataOrderProfitDiamond {
    private int diamond_num;//商钻数
    private double profit;// 预期收益

    public int getDiamond_num() {
        return diamond_num;
    }

    public void setDiamond_num(int diamond_num) {
        this.diamond_num = diamond_num;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
