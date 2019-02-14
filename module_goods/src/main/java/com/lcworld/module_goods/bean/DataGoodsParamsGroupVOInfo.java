package com.lcworld.module_goods.bean;

import java.util.List;

/**
 * 参数组
 */
public class DataGoodsParamsGroupVOInfo {
    private int group_id;
    private String group_name;
    private List<DataGoodsParamsVoInfo> params;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<DataGoodsParamsVoInfo> getParams() {
        return params;
    }

    public void setParams(List<DataGoodsParamsVoInfo> params) {
        this.params = params;
    }
}
