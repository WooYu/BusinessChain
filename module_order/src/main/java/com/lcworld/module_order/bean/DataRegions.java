package com.lcworld.module_order.bean;

public class DataRegions {
    private int id;
    private int cod;//是否支持货到付款,1支持，0不支持
    private String local_name;//名称
    private int parent_id;//父地区id
    private int region_grade;//级别
    private String region_path;//路径
    private String zipcode;
    private boolean beChosen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getRegion_grade() {
        return region_grade;
    }

    public void setRegion_grade(int region_grade) {
        this.region_grade = region_grade;
    }

    public String getRegion_path() {
        return region_path;
    }

    public void setRegion_path(String region_path) {
        this.region_path = region_path;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isBeChosen() {
        return beChosen;
    }

    public void setBeChosen(boolean beChosen) {
        this.beChosen = beChosen;
    }
}
