package com.lcworld.module_goods.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 商品属性下的分类信息
 */
public class DataSpecValueVo implements Parcelable {

    /**
     * spec_id : 0
     * spec_image : string
     * spec_name : string
     * spec_type : 0
     * spec_value : string
     * spec_value_id : 0
     */

    private int spec_id;//规格项id
    private String spec_image;//规格的图片
    private String spec_name;//规格名称
    private int spec_type;//该规格是否有图片，1 有 0 没有
    private String spec_value;//规格值名字
    private int spec_value_id;//规格值id

    public int getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(int spec_id) {
        this.spec_id = spec_id;
    }

    public String getSpec_image() {
        return spec_image;
    }

    public void setSpec_image(String spec_image) {
        this.spec_image = spec_image;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public int getSpec_type() {
        return spec_type;
    }

    public void setSpec_type(int spec_type) {
        this.spec_type = spec_type;
    }

    public String getSpec_value() {
        return spec_value;
    }

    public void setSpec_value(String spec_value) {
        this.spec_value = spec_value;
    }

    public int getSpec_value_id() {
        return spec_value_id;
    }

    public void setSpec_value_id(int spec_value_id) {
        this.spec_value_id = spec_value_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.spec_id);
        dest.writeString(this.spec_image);
        dest.writeString(this.spec_name);
        dest.writeInt(this.spec_type);
        dest.writeString(this.spec_value);
        dest.writeInt(this.spec_value_id);
    }

    public DataSpecValueVo() {
    }

    protected DataSpecValueVo(Parcel in) {
        this.spec_id = in.readInt();
        this.spec_image = in.readString();
        this.spec_name = in.readString();
        this.spec_type = in.readInt();
        this.spec_value = in.readString();
        this.spec_value_id = in.readInt();
    }

    public static final Parcelable.Creator<DataSpecValueVo> CREATOR = new Parcelable.Creator<DataSpecValueVo>() {
        @Override
        public DataSpecValueVo createFromParcel(Parcel source) {
            return new DataSpecValueVo(source);
        }

        @Override
        public DataSpecValueVo[] newArray(int size) {
            return new DataSpecValueVo[size];
        }
    };
}
