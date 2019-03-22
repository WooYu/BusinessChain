package com.lcworld.module_goods.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * 购物车中活动Vo
 */
public class DataCartPromotionVo implements Parcelable {
    private int activity_id;//活动id
    private long end_time;//活动结束时间
    private int is_check;//是否选中参与这个活动,1为是 0为否
    private String promotion_type;//活动工具类型
    private int remian_quantity;//剩余售空数量
    private long start_time;//活动开始时间
    private String title;//活动名称

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public int getIs_check() {
        return is_check;
    }

    public void setIs_check(int is_check) {
        this.is_check = is_check;
    }

    public String getPromotion_type() {
        return promotion_type;
    }

    public void setPromotion_type(String promotion_type) {
        this.promotion_type = promotion_type;
    }

    public int getRemian_quantity() {
        return remian_quantity;
    }

    public void setRemian_quantity(int remian_quantity) {
        this.remian_quantity = remian_quantity;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.activity_id);
        dest.writeLong(this.end_time);
        dest.writeInt(this.is_check);
        dest.writeString(this.promotion_type);
        dest.writeInt(this.remian_quantity);
        dest.writeLong(this.start_time);
        dest.writeString(this.title);
    }

    public DataCartPromotionVo() {
    }

    protected DataCartPromotionVo(Parcel in) {
        this.activity_id = in.readInt();
        this.end_time = in.readLong();
        this.is_check = in.readInt();
        this.promotion_type = in.readString();
        this.remian_quantity = in.readInt();
        this.start_time = in.readLong();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<DataCartPromotionVo> CREATOR = new Parcelable.Creator<DataCartPromotionVo>() {
        @Override
        public DataCartPromotionVo createFromParcel(Parcel source) {
            return new DataCartPromotionVo(source);
        }

        @Override
        public DataCartPromotionVo[] newArray(int size) {
            return new DataCartPromotionVo[size];
        }
    };
}
