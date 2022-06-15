package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1912:24
 * Description classtask
 */
public class normalresult<T> {
    private int normalNum;
    private int abnormalNum;
    private int allNum;
    private T pageInfo;

    public int getNormalNum() {
        return normalNum;
    }

    public void setNormalNum(int normalNum) {
        this.normalNum = normalNum;
    }

    public int getAbnormalNum() {
        return abnormalNum;
    }

    public void setAbnormalNum(int abnormalNum) {
        this.abnormalNum = abnormalNum;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public T getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(T pageInfo) {
        this.pageInfo = pageInfo;
    }
}
