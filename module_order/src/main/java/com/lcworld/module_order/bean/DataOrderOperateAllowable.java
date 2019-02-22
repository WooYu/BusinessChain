package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataOrderOperateAllowable implements Parcelable {
   private boolean  allow_apply_service;//是否允许申请售后
   private boolean allow_cancel;//是否允许被取消
   private boolean allow_comment;//是否允许被评论
   private boolean allow_complete;//是否允许被完成
   private boolean allow_confirm;//是否允许被确认
   private boolean  allow_pay;//是否允许被支付
   private boolean allow_rog;//是否允许被收货
   private boolean allow_service_cancel;//是否允许取消(售后)
   private boolean allow_ship;//是否允许被发货

    public boolean isAllow_apply_service() {
        return allow_apply_service;
    }

    public void setAllow_apply_service(boolean allow_apply_service) {
        this.allow_apply_service = allow_apply_service;
    }

    public boolean isAllow_cancel() {
        return allow_cancel;
    }

    public void setAllow_cancel(boolean allow_cancel) {
        this.allow_cancel = allow_cancel;
    }

    public boolean isAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(boolean allow_comment) {
        this.allow_comment = allow_comment;
    }

    public boolean isAllow_complete() {
        return allow_complete;
    }

    public void setAllow_complete(boolean allow_complete) {
        this.allow_complete = allow_complete;
    }

    public boolean isAllow_confirm() {
        return allow_confirm;
    }

    public void setAllow_confirm(boolean allow_confirm) {
        this.allow_confirm = allow_confirm;
    }

    public boolean isAllow_pay() {
        return allow_pay;
    }

    public void setAllow_pay(boolean allow_pay) {
        this.allow_pay = allow_pay;
    }

    public boolean isAllow_rog() {
        return allow_rog;
    }

    public void setAllow_rog(boolean allow_rog) {
        this.allow_rog = allow_rog;
    }

    public boolean isAllow_service_cancel() {
        return allow_service_cancel;
    }

    public void setAllow_service_cancel(boolean allow_service_cancel) {
        this.allow_service_cancel = allow_service_cancel;
    }

    public boolean isAllow_ship() {
        return allow_ship;
    }

    public void setAllow_ship(boolean allow_ship) {
        this.allow_ship = allow_ship;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.allow_apply_service ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_cancel ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_comment ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_complete ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_confirm ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_pay ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_rog ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_service_cancel ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_ship ? (byte) 1 : (byte) 0);
    }

    public DataOrderOperateAllowable() {
    }

    protected DataOrderOperateAllowable(Parcel in) {
        this.allow_apply_service = in.readByte() != 0;
        this.allow_cancel = in.readByte() != 0;
        this.allow_comment = in.readByte() != 0;
        this.allow_complete = in.readByte() != 0;
        this.allow_confirm = in.readByte() != 0;
        this.allow_pay = in.readByte() != 0;
        this.allow_rog = in.readByte() != 0;
        this.allow_service_cancel = in.readByte() != 0;
        this.allow_ship = in.readByte() != 0;
    }

    public static final Parcelable.Creator<DataOrderOperateAllowable> CREATOR = new Parcelable.Creator<DataOrderOperateAllowable>() {
        @Override
        public DataOrderOperateAllowable createFromParcel(Parcel source) {
            return new DataOrderOperateAllowable(source);
        }

        @Override
        public DataOrderOperateAllowable[] newArray(int size) {
            return new DataOrderOperateAllowable[size];
        }
    };
}
