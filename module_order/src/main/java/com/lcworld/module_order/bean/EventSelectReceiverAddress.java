package com.lcworld.module_order.bean;

/**
 * 选中配送地址
 */
public class EventSelectReceiverAddress {
    private DataMemberAddress memberAddress;

    public EventSelectReceiverAddress(DataMemberAddress memberAddress) {
        this.memberAddress = memberAddress;
    }

    public DataMemberAddress getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(DataMemberAddress memberAddress) {
        this.memberAddress = memberAddress;
    }
}
