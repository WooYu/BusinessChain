package com.lcworld.library_base.http;

import java.util.List;

/**
 * 分页信息
 * @param <T>
 */
public class DataPage<T> {


    /**
     * data : []
     * page_no : 1
     * page_size : 2000
     * data_total : 0
     */

    private int page_no;
    private int page_size;
    private int data_total;
    private List<T> data;


    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getData_total() {
        return data_total;
    }

    public void setData_total(int data_total) {
        this.data_total = data_total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
