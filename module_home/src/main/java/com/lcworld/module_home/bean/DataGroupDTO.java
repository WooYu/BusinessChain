package com.lcworld.module_home.bean;

/**
 * 企业实体
 */
public class DataGroupDTO {
    private String business;// 营业执照
    private String credit_code;//统一社会信用代码
    private String group_address;//办公地址
    private String group_log;//logo
    private String group_name;//企业名称
    private String group_phone;//企业联系电话
    private int id;//id
    private int status;// -1未申请 0已申请待审核 10审核成功 20审核失败 30冻结
    private String user_name;//联系人姓名
    private String user_phone;//联系人电话
    private String user_position;//联系人职位

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCredit_code() {
        return credit_code;
    }

    public void setCredit_code(String credit_code) {
        this.credit_code = credit_code;
    }

    public String getGroup_address() {
        return group_address;
    }

    public void setGroup_address(String group_address) {
        this.group_address = group_address;
    }

    public String getGroup_log() {
        return group_log;
    }

    public void setGroup_log(String group_log) {
        this.group_log = group_log;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_phone() {
        return group_phone;
    }

    public void setGroup_phone(String group_phone) {
        this.group_phone = group_phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }
}
