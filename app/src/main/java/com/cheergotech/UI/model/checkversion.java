package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/6/114:41
 * Description
 */
public class checkversion {
    private String loadUrl;
    private int serverCode;
    private String titles;

    public String getLoadUrl() {
        return loadUrl;
    }

    public void setLoadUrl(String loadUrl) {
        this.loadUrl = loadUrl;
    }

    public int getServerCode() {
        return serverCode;
    }

    public void setServerCode(int serverCode) {
        this.serverCode = serverCode;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "checkversion{" +
                "loadUrl='" + loadUrl + '\'' +
                ", serverCode=" + serverCode +
                ", titles='" + titles + '\'' +
                '}';
    }
}
