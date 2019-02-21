package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataPromotonRule implements Parcelable {

    private DataCouponVo coupon_gift;
    private boolean free_shipping;
    private DataGiftVo goods_gift;
    private boolean invalid;
    private String invalid_reason;
    private int point_gift;
    private double reduced_price;
    private double reduced_total_price;
    private String tag;
    private String target;
    private String tips;
    private DataCouponVo use_coupon;
    private int  use_point;

    public DataCouponVo getCoupon_gift() {
        return coupon_gift;
    }

    public void setCoupon_gift(DataCouponVo coupon_gift) {
        this.coupon_gift = coupon_gift;
    }

    public boolean isFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(boolean free_shipping) {
        this.free_shipping = free_shipping;
    }

    public DataGiftVo getGoods_gift() {
        return goods_gift;
    }

    public void setGoods_gift(DataGiftVo goods_gift) {
        this.goods_gift = goods_gift;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public String getInvalid_reason() {
        return invalid_reason;
    }

    public void setInvalid_reason(String invalid_reason) {
        this.invalid_reason = invalid_reason;
    }

    public int getPoint_gift() {
        return point_gift;
    }

    public void setPoint_gift(int point_gift) {
        this.point_gift = point_gift;
    }

    public double getReduced_price() {
        return reduced_price;
    }

    public void setReduced_price(double reduced_price) {
        this.reduced_price = reduced_price;
    }

    public double getReduced_total_price() {
        return reduced_total_price;
    }

    public void setReduced_total_price(double reduced_total_price) {
        this.reduced_total_price = reduced_total_price;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public DataCouponVo getUse_coupon() {
        return use_coupon;
    }

    public void setUse_coupon(DataCouponVo use_coupon) {
        this.use_coupon = use_coupon;
    }

    public int getUse_point() {
        return use_point;
    }

    public void setUse_point(int use_point) {
        this.use_point = use_point;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coupon_gift, flags);
        dest.writeByte(this.free_shipping ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.goods_gift, flags);
        dest.writeByte(this.invalid ? (byte) 1 : (byte) 0);
        dest.writeString(this.invalid_reason);
        dest.writeInt(this.point_gift);
        dest.writeDouble(this.reduced_price);
        dest.writeDouble(this.reduced_total_price);
        dest.writeString(this.tag);
        dest.writeString(this.target);
        dest.writeString(this.tips);
        dest.writeParcelable(this.use_coupon, flags);
        dest.writeInt(this.use_point);
    }

    public DataPromotonRule() {
    }

    protected DataPromotonRule(Parcel in) {
        this.coupon_gift = in.readParcelable(DataCouponVo.class.getClassLoader());
        this.free_shipping = in.readByte() != 0;
        this.goods_gift = in.readParcelable(DataGiftVo.class.getClassLoader());
        this.invalid = in.readByte() != 0;
        this.invalid_reason = in.readString();
        this.point_gift = in.readInt();
        this.reduced_price = in.readDouble();
        this.reduced_total_price = in.readDouble();
        this.tag = in.readString();
        this.target = in.readString();
        this.tips = in.readString();
        this.use_coupon = in.readParcelable(DataCouponVo.class.getClassLoader());
        this.use_point = in.readInt();
    }

    public static final Parcelable.Creator<DataPromotonRule> CREATOR = new Parcelable.Creator<DataPromotonRule>() {
        @Override
        public DataPromotonRule createFromParcel(Parcel source) {
            return new DataPromotonRule(source);
        }

        @Override
        public DataPromotonRule[] newArray(int size) {
            return new DataPromotonRule[size];
        }
    };
}
