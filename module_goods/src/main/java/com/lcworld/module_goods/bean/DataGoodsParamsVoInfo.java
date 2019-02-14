package com.lcworld.module_goods.bean;

import java.util.List;

public class DataGoodsParamsVoInfo {

    /**
     * group_id : 0
     * is_index : 0
     * option_list : ["string"]
     * options : string
     * param_id : 0
     * param_name : string
     * param_type : 0
     * param_value : string
     * required : 0
     */

    private int group_id;
    private int is_index;
    private String options;
    private int param_id;
    private String param_name;
    private int param_type;
    private String param_value;
    private int required;
    private List<String> option_list;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getIs_index() {
        return is_index;
    }

    public void setIs_index(int is_index) {
        this.is_index = is_index;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getParam_id() {
        return param_id;
    }

    public void setParam_id(int param_id) {
        this.param_id = param_id;
    }

    public String getParam_name() {
        return param_name;
    }

    public void setParam_name(String param_name) {
        this.param_name = param_name;
    }

    public int getParam_type() {
        return param_type;
    }

    public void setParam_type(int param_type) {
        this.param_type = param_type;
    }

    public String getParam_value() {
        return param_value;
    }

    public void setParam_value(String param_value) {
        this.param_value = param_value;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public List<String> getOption_list() {
        return option_list;
    }

    public void setOption_list(List<String> option_list) {
        this.option_list = option_list;
    }
}
