package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataConsigneeVo implements Parcelable {
    private String address;//详细地址
    private String city;//市
    private int city_id;//市ID
    private int consignee_id;//id
    private String county;//区
    private int county_id;//区ID
    private String mobile;//手机号
    private String name;//收货人姓名
    private String province;//省
    private int province_id;//省ID
    private String telephone;//电话
    private String town;//街道
    private int town_id;//街道ID

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getConsignee_id() {
        return consignee_id;
    }

    public void setConsignee_id(int consignee_id) {
        this.consignee_id = consignee_id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getCounty_id() {
        return county_id;
    }

    public void setCounty_id(int county_id) {
        this.county_id = county_id;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getTown_id() {
        return town_id;
    }

    public void setTown_id(int town_id) {
        this.town_id = town_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeInt(this.city_id);
        dest.writeInt(this.consignee_id);
        dest.writeString(this.county);
        dest.writeInt(this.county_id);
        dest.writeString(this.mobile);
        dest.writeString(this.name);
        dest.writeString(this.province);
        dest.writeInt(this.province_id);
        dest.writeString(this.telephone);
        dest.writeString(this.town);
        dest.writeInt(this.town_id);
    }

    public DataConsigneeVo() {
    }

    protected DataConsigneeVo(Parcel in) {
        this.address = in.readString();
        this.city = in.readString();
        this.city_id = in.readInt();
        this.consignee_id = in.readInt();
        this.county = in.readString();
        this.county_id = in.readInt();
        this.mobile = in.readString();
        this.name = in.readString();
        this.province = in.readString();
        this.province_id = in.readInt();
        this.telephone = in.readString();
        this.town = in.readString();
        this.town_id = in.readInt();
    }

    public static final Parcelable.Creator<DataConsigneeVo> CREATOR = new Parcelable.Creator<DataConsigneeVo>() {
        @Override
        public DataConsigneeVo createFromParcel(Parcel source) {
            return new DataConsigneeVo(source);
        }

        @Override
        public DataConsigneeVo[] newArray(int size) {
            return new DataConsigneeVo[size];
        }
    };
}
