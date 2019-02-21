package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DataTradeVo implements Parcelable {

    private DataConsigneeVo consignee;
    private List<DataCouponVo> coupon_list;
    private List<DataGiftVo> gift_list;
    private int member_id;//会员id
    private String member_name;//会员昵称
    private List<DataOrderDTO> order_list;
    private String payment_type;//支付方式
    private DataPriceDetailVo price_detail;
    private String trade_sn;

    public DataConsigneeVo getConsignee() {
        return consignee;
    }

    public void setConsignee(DataConsigneeVo consignee) {
        this.consignee = consignee;
    }

    public List<DataCouponVo> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<DataCouponVo> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public List<DataGiftVo> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<DataGiftVo> gift_list) {
        this.gift_list = gift_list;
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

    public List<DataOrderDTO> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<DataOrderDTO> order_list) {
        this.order_list = order_list;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public DataPriceDetailVo getPrice_detail() {
        return price_detail;
    }

    public void setPrice_detail(DataPriceDetailVo price_detail) {
        this.price_detail = price_detail;
    }

    public String getTrade_sn() {
        return trade_sn;
    }

    public void setTrade_sn(String trade_sn) {
        this.trade_sn = trade_sn;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.consignee, flags);
        dest.writeTypedList(this.coupon_list);
        dest.writeTypedList(this.gift_list);
        dest.writeInt(this.member_id);
        dest.writeString(this.member_name);
        dest.writeTypedList(this.order_list);
        dest.writeString(this.payment_type);
        dest.writeParcelable(this.price_detail, flags);
        dest.writeString(this.trade_sn);
    }

    public DataTradeVo() {
    }

    protected DataTradeVo(Parcel in) {
        this.consignee = in.readParcelable(DataConsigneeVo.class.getClassLoader());
        this.coupon_list = in.createTypedArrayList(DataCouponVo.CREATOR);
        this.gift_list = in.createTypedArrayList(DataGiftVo.CREATOR);
        this.member_id = in.readInt();
        this.member_name = in.readString();
        this.order_list = in.createTypedArrayList(DataOrderDTO.CREATOR);
        this.payment_type = in.readString();
        this.price_detail = in.readParcelable(DataPriceDetailVo.class.getClassLoader());
        this.trade_sn = in.readString();
    }

    public static final Parcelable.Creator<DataTradeVo> CREATOR = new Parcelable.Creator<DataTradeVo>() {
        @Override
        public DataTradeVo createFromParcel(Parcel source) {
            return new DataTradeVo(source);
        }

        @Override
        public DataTradeVo[] newArray(int size) {
            return new DataTradeVo[size];
        }
    };
}
