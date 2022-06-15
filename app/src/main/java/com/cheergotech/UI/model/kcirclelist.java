package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1914:32
 * Description 班级圈
 */
public class kcirclelist<T> {
    private int id;
    private int userId;
    private String realName;
    private String headImg;
    private String titles;
    private String describes;
    private String detailsImg;
    private int followers;
    private int numbers;
    private int attentionNumber;
    private int isAttention;
    private long createTime;
    private int roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
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

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getAttentionNumber() {
        return attentionNumber;
    }

    public void setAttentionNumber(int attentionNumber) {
        this.attentionNumber = attentionNumber;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "kcirclelist{" +
                "id=" + id +
                ", userId=" + userId +
                ", realName='" + realName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", titles='" + titles + '\'' +
                ", describes='" + describes + '\'' +
                ", detailsImg='" + detailsImg + '\'' +
                ", followers=" + followers +
                ", numbers=" + numbers +
                ", attentionNumber=" + attentionNumber +
                ", isAttention=" + isAttention +
                ", createTime=" + createTime +
                ", roles=" + roles +
                '}';
    }
}
