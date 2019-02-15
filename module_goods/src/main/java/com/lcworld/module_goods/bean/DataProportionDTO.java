package com.lcworld.module_goods.bean;

/**
 * 拼团年化收益率实体
 */
public class DataProportionDTO {
    private int day;//天数
    private int id;//年化收益率id
    private String proportion;//年化收益率（存储的是百分比后的小数值，比如年化收益率为8%，那么存储的值为0.08）

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
