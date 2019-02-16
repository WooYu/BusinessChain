package com.lcworld.module_order.bean;

/**
 * 发票
 */
public class DataReceiptVo {
    private String receipt_content;//发票内容
    private String receipt_title;//发票抬头
    private String tax_no;//发票税号
    private int type;//普票类型，0为个人，其他为公司

    public String getReceipt_content() {
        return receipt_content;
    }

    public void setReceipt_content(String receipt_content) {
        this.receipt_content = receipt_content;
    }

    public String getReceipt_title() {
        return receipt_title;
    }

    public void setReceipt_title(String receipt_title) {
        this.receipt_title = receipt_title;
    }

    public String getTax_no() {
        return tax_no;
    }

    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
