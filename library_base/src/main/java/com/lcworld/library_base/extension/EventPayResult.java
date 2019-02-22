package com.lcworld.library_base.extension;

/**
 * 支付结果
 */
public class EventPayResult {
    private boolean isSuccess;

    public EventPayResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
