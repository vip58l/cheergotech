package com.cheergotech.UI.model;

import java.util.List;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2718:43
 * Description
 */
public class submitstulist<T> {
    private List<T> list;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int total;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
