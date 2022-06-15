package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2911:39
 * Description
 */
public class subadvice {
    private String contacts;
    private String createTime;
    private String describes;
    private String detailsImg;
    private int id;
    private int sources;
    private String titles;

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSources() {
        return sources;
    }

    public void setSources(int sources) {
        this.sources = sources;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "subadvice{" +
                "contacts='" + contacts + '\'' +
                ", createTime='" + createTime + '\'' +
                ", describes='" + describes + '\'' +
                ", detailsImg='" + detailsImg + '\'' +
                ", id=" + id +
                ", sources=" + sources +
                ", titles='" + titles + '\'' +
                '}';
    }
}
