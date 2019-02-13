package com.lcworld.library_base.extension;

/**
 * 扩展：为了使用腾讯的Dialog
 */
public class EventDialog {
    private int type;
    private String title;

    public EventDialog(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
