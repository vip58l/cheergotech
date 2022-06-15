package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2011:46
 * Description
 */
public class taskinfo {
    private String autos;
    private String className;
    private String createTime;
    private String describes;
    private String files;
    private int id;
    private String images;
    private int numbers;
    private String remarks;
    private int statusInfo;
    private String titles;
    private int toDoNumbers;
    private String updateTime;
    private String videos;

    public String getAutos() {
        return autos;
    }

    public void setAutos(String autos) {
        this.autos = autos;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(int statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public int getToDoNumbers() {
        return toDoNumbers;
    }

    public void setToDoNumbers(int toDoNumbers) {
        this.toDoNumbers = toDoNumbers;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "taskinfo{" +
                "autos='" + autos + '\'' +
                ", className='" + className + '\'' +
                ", createTime='" + createTime + '\'' +
                ", describes='" + describes + '\'' +
                ", files='" + files + '\'' +
                ", id=" + id +
                ", images='" + images + '\'' +
                ", numbers=" + numbers +
                ", remarks='" + remarks + '\'' +
                ", statusInfo=" + statusInfo +
                ", titles='" + titles + '\'' +
                ", toDoNumbers=" + toDoNumbers +
                ", updateTime='" + updateTime + '\'' +
                ", videos='" + videos + '\'' +
                '}';
    }
}
