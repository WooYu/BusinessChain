package com.lcworld.library_base.http;

/**
 * 事件：请求的加载框
 */
public class EventRequestLoadingBox {
    public EventRequestLoadingBox(boolean bShow) {
        this.bShow = bShow;
    }

    private boolean bShow;

    public boolean isbShow() {
        return bShow;
    }

    public void setbShow(boolean bShow) {
        this.bShow = bShow;
    }
}
