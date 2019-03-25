package com.lcworld.module_order.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DataKeyboard implements MultiItemEntity {
    public static final int Type_Figure = 1;
    public static final int Type_Empty = 2;
    public static final int Type_Delete = 3;
    private int itemType;
    private String content;

    public DataKeyboard(int itemType, String content) {
        this.itemType = itemType;
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getContent() {
        return content;
    }

}
