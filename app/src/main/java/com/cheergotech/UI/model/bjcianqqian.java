package com.cheergotech.UI.model;

import java.util.List;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2016:06
 * Description
 */
public class bjcianqqian<T> {
    private int attentionNumber;
    private String createTime;
    private String describes;
    private String detailsImg;
    private List<T> followerInfo;
    private int followers;
    private String headImg;
    private int id;
    private int isAttention;
    private int numbers;
    private String realName;
    private String titles;
    private int userId;

    public int getAttentionNumber() {
        return attentionNumber;
    }

    public void setAttentionNumber(int attentionNumber) {
        this.attentionNumber = attentionNumber;
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

    public String getDetailsImg() {
        return detailsImg;
    }

    public void setDetailsImg(String detailsImg) {
        this.detailsImg = detailsImg;
    }

    public List<T> getFollowerInfo() {
        return followerInfo;
    }

    public void setFollowerInfo(List<T> followerInfo) {
        this.followerInfo = followerInfo;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "bjcianqqian{" +
                "attentionNumber=" + attentionNumber +
                ", createTime='" + createTime + '\'' +
                ", describes='" + describes + '\'' +
                ", detailsImg='" + detailsImg + '\'' +
                ", followerInfo=" + followerInfo +
                ", followers=" + followers +
                ", headImg='" + headImg + '\'' +
                ", id=" + id +
                ", isAttention=" + isAttention +
                ", numbers=" + numbers +
                ", realName='" + realName + '\'' +
                ", titles='" + titles + '\'' +
                ", userId=" + userId +
                '}';
    }
}
