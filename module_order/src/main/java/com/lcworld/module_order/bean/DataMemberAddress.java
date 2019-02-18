package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataMemberAddress implements Parcelable {
    private String addr;//详细地址
    private int def_addr;//是否为默认收货地址,1为默认
    private String mobile;//手机号码
    private String name;//收货人姓名
    private String ship_address_name;//地址别名
    private String tel;//联系电话(一般指座机)

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getDef_addr() {
        return def_addr;
    }

    public void setDef_addr(int def_addr) {
        this.def_addr = def_addr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShip_address_name() {
        return ship_address_name;
    }

    public void setShip_address_name(String ship_address_name) {
        this.ship_address_name = ship_address_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addr);
        dest.writeInt(this.def_addr);
        dest.writeString(this.mobile);
        dest.writeString(this.name);
        dest.writeString(this.ship_address_name);
        dest.writeString(this.tel);
    }

    public DataMemberAddress() {
    }

    protected DataMemberAddress(Parcel in) {
        this.addr = in.readString();
        this.def_addr = in.readInt();
        this.mobile = in.readString();
        this.name = in.readString();
        this.ship_address_name = in.readString();
        this.tel = in.readString();
    }

    public static final Parcelable.Creator<DataMemberAddress> CREATOR = new Parcelable.Creator<DataMemberAddress>() {
        @Override
        public DataMemberAddress createFromParcel(Parcel source) {
            return new DataMemberAddress(source);
        }

        @Override
        public DataMemberAddress[] newArray(int size) {
            return new DataMemberAddress[size];
        }
    };
}
