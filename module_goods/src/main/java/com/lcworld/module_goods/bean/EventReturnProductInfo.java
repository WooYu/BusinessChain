package com.lcworld.module_goods.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 返回sku信息
 */
public class EventReturnProductInfo implements Parcelable {

    private int buynum;
    private int skuid;

    public EventReturnProductInfo(int buynum, int skuid) {
        this.buynum = buynum;
        this.skuid = skuid;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public int getSkuid() {
        return skuid;
    }

    public void setSkuid(int skuid) {
        this.skuid = skuid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.buynum);
        dest.writeInt(this.skuid);
    }

    public EventReturnProductInfo() {
    }

    protected EventReturnProductInfo(Parcel in) {
        this.buynum = in.readInt();
        this.skuid = in.readInt();
    }

    public static final Parcelable.Creator<EventReturnProductInfo> CREATOR = new Parcelable.Creator<EventReturnProductInfo>() {
        @Override
        public EventReturnProductInfo createFromParcel(Parcel source) {
            return new EventReturnProductInfo(source);
        }

        @Override
        public EventReturnProductInfo[] newArray(int size) {
            return new EventReturnProductInfo[size];
        }
    };
}
