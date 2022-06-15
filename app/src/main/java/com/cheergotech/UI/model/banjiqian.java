package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2015:27
 * Description 班级圈-消息
 */
public class banjiqian {
    private int circleId;
    private String createTime;
    private String describe;
    private String fromName;
    private int id;
    private int isView;
    private int receiveId;

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsView() {
        return isView;
    }

    public void setIsView(int isView) {
        this.isView = isView;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    @Override
    public String toString() {
        return "banjiqian{" +
                "circleId=" + circleId +
                ", createTime='" + createTime + '\'' +
                ", describe='" + describe + '\'' +
                ", fromName='" + fromName + '\'' +
                ", id=" + id +
                ", isView=" + isView +
                ", receiveId=" + receiveId +
                '}';
    }
}
