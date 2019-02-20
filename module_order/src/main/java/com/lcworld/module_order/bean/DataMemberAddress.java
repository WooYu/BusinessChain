package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataMemberAddress implements Parcelable {
    private String addr;//详细地址
    private String name;//收货人姓名
    private int addr_id;

    private String city;//所属城市名称
    private int city_id;//所属城市ID
    private String country;// 收货人国籍
    private String county;//所属县(区)名称
    private int county_id;//所属县(区)ID
    private int def_addr;//是否为默认收货地址,1为默认
    private String mobile;// 手机号码
    private String province;//所属省份名称
    private int province_id;//所属省份ID
    private String ship_address_name;//地址别名
    private String tel;//联系电话(一般指座机)
    private String town;//所属城镇名称
    private int town_id;// 所属城镇ID

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(int addr_id) {
        this.addr_id = addr_id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        dest.writeString(this.addr);
        dest.writeString(this.name);
        dest.writeInt(this.addr_id);
        dest.writeString(this.city);
        dest.writeInt(this.city_id);
        dest.writeString(this.country);
        dest.writeString(this.county);
        dest.writeInt(this.county_id);
        dest.writeInt(this.def_addr);
        dest.writeString(this.mobile);
        dest.writeString(this.province);
        dest.writeInt(this.province_id);
        dest.writeString(this.ship_address_name);
        dest.writeString(this.tel);
        dest.writeString(this.town);
        dest.writeInt(this.town_id);
    }

    public DataMemberAddress() {
    }

    protected DataMemberAddress(Parcel in) {
        this.addr = in.readString();
        this.name = in.readString();
        this.addr_id = in.readInt();
        this.city = in.readString();
        this.city_id = in.readInt();
        this.country = in.readString();
        this.county = in.readString();
        this.county_id = in.readInt();
        this.def_addr = in.readInt();
        this.mobile = in.readString();
        this.province = in.readString();
        this.province_id = in.readInt();
        this.ship_address_name = in.readString();
        this.tel = in.readString();
        this.town = in.readString();
        this.town_id = in.readInt();
    }

    public static final Creator<DataMemberAddress> CREATOR = new Creator<DataMemberAddress>() {
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
