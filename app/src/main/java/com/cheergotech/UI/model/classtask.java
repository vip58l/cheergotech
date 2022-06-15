package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2010:40
 * Description
 */
public class classtask {
    private int allNum;
    private int checkNum;
    private String createTime;
    private String describes;
    private int id;
    private int statusInfo;
    private int submitNum;
    private String titles;
    private String updateTime;

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(int statusInfo) {
        this.statusInfo = statusInfo;
    }

    public int getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(int submitNum) {
        this.submitNum = submitNum;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "classtask{" +
                "allNum=" + allNum +
                ", checkNum=" + checkNum +
                ", createTime='" + createTime + '\'' +
                ", describes='" + describes + '\'' +
                ", id=" + id +
                ", statusInfo=" + statusInfo +
                ", submitNum=" + submitNum +
                ", titles='" + titles + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
