package com.lcworld.module_home.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataMessageNoticeLog implements Parcelable {
    private String content;//站内信内容
    private int is_del;//是否删除，0删除，1没有删除
    private int is_read;//是否已读，0未读，1已读
    private int member_id;// 会员id
    private int notice_type;//消息类型 0:登录注册 5:订单 10:收益 20其他
    private long receive_time;//接收时间
    private long send_time;//发送时间
    private String title;//标题

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(int notice_type) {
        this.notice_type = notice_type;
    }

    public long getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(long receive_time) {
        this.receive_time = receive_time;
    }

    public long getSend_time() {
        return send_time;
    }

    public void setSend_time(long send_time) {
        this.send_time = send_time;
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
        dest.writeString(this.content);
        dest.writeInt(this.is_del);
        dest.writeInt(this.is_read);
        dest.writeInt(this.member_id);
        dest.writeInt(this.notice_type);
        dest.writeLong(this.receive_time);
        dest.writeLong(this.send_time);
        dest.writeString(this.title);
    }

    public DataMessageNoticeLog() {
    }

    protected DataMessageNoticeLog(Parcel in) {
        this.content = in.readString();
        this.is_del = in.readInt();
        this.is_read = in.readInt();
        this.member_id = in.readInt();
        this.notice_type = in.readInt();
        this.receive_time = in.readLong();
        this.send_time = in.readLong();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<DataMessageNoticeLog> CREATOR = new Parcelable.Creator<DataMessageNoticeLog>() {
        @Override
        public DataMessageNoticeLog createFromParcel(Parcel source) {
            return new DataMessageNoticeLog(source);
        }

        @Override
        public DataMessageNoticeLog[] newArray(int size) {
            return new DataMessageNoticeLog[size];
        }
    };
}
