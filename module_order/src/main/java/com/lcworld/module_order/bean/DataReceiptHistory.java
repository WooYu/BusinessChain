package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataReceiptHistory implements Parcelable {

    private long add_time;//开票时间
    private String bank_account;//银行账户
    private String bank_name;//开户银行
    private int member_id;//会员id
    private String member_name;//会员名称
    private String order_sn;//订单编号
    private double receipt_amount;//发票金额
    private String receipt_content;//发票内容
    private String receipt_title;//发票抬头
    private String receipt_type;//发票类型
    private String reg_addr;//注册地址
    private String reg_tel;//注册电话
    private int seller_id;//卖家id
    private String tax_no;//税号

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public double getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(double receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

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

    public String getReceipt_type() {
        return receipt_type;
    }

    public void setReceipt_type(String receipt_type) {
        this.receipt_type = receipt_type;
    }

    public String getReg_addr() {
        return reg_addr;
    }

    public void setReg_addr(String reg_addr) {
        this.reg_addr = reg_addr;
    }

    public String getReg_tel() {
        return reg_tel;
    }

    public void setReg_tel(String reg_tel) {
        this.reg_tel = reg_tel;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getTax_no() {
        return tax_no;
    }

    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.add_time);
        dest.writeString(this.bank_account);
        dest.writeString(this.bank_name);
        dest.writeInt(this.member_id);
        dest.writeString(this.member_name);
        dest.writeString(this.order_sn);
        dest.writeDouble(this.receipt_amount);
        dest.writeString(this.receipt_content);
        dest.writeString(this.receipt_title);
        dest.writeString(this.receipt_type);
        dest.writeString(this.reg_addr);
        dest.writeString(this.reg_tel);
        dest.writeInt(this.seller_id);
        dest.writeString(this.tax_no);
    }

    public DataReceiptHistory() {
    }

    protected DataReceiptHistory(Parcel in) {
        this.add_time = in.readLong();
        this.bank_account = in.readString();
        this.bank_name = in.readString();
        this.member_id = in.readInt();
        this.member_name = in.readString();
        this.order_sn = in.readString();
        this.receipt_amount = in.readDouble();
        this.receipt_content = in.readString();
        this.receipt_title = in.readString();
        this.receipt_type = in.readString();
        this.reg_addr = in.readString();
        this.reg_tel = in.readString();
        this.seller_id = in.readInt();
        this.tax_no = in.readString();
    }

    public static final Parcelable.Creator<DataReceiptHistory> CREATOR = new Parcelable.Creator<DataReceiptHistory>() {
        @Override
        public DataReceiptHistory createFromParcel(Parcel source) {
            return new DataReceiptHistory(source);
        }

        @Override
        public DataReceiptHistory[] newArray(int size) {
            return new DataReceiptHistory[size];
        }
    };
}
