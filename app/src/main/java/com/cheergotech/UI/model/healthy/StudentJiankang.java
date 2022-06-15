package com.cheergotech.UI.model.healthy;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1714:54
 * Description 学生健康信息VO-外
 */
public class StudentJiankang<T> {
    private int abnormalNum;
    private T pageInfo;
    private int unCollectNum;

    public int getAbnormalNum() {
        return abnormalNum;
    }

    public void setAbnormalNum(int abnormalNum) {
        this.abnormalNum = abnormalNum;
    }

    public T getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(T pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getUnCollectNum() {
        return unCollectNum;
    }

    public void setUnCollectNum(int unCollectNum) {
        this.unCollectNum = unCollectNum;
    }
}
