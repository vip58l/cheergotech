package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1815:57
 * Description
 */
public class shengpi {
    protected int biticon;
    protected String title;
    protected String describe;

    public int getBiticon() {
        return biticon;
    }

    public void setBiticon(int biticon) {
        this.biticon = biticon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "shengpi{" +
                "biticon=" + biticon +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
