package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2719:44
 * Description作业信息-详情
 */
public class classtaskinfo {
    private String autos;
    private int courseInfo;
    private String createTime;
    private String describes;
    private String files;
    private int id;
    private String images;
    private String remarks;
    private int status;
    private String studentName;
    private int taskId;
    private String titles;
    private String updateTime;
    private String videos;

    public String getAutos() {
        return autos;
    }

    public void setAutos(String autos) {
        this.autos = autos;
    }

    public int getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(int courseInfo) {
        this.courseInfo = courseInfo;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "classtaskinfo{" +
                "autos='" + autos + '\'' +
                ", courseInfo=" + courseInfo +
                ", createTime='" + createTime + '\'' +
                ", describes='" + describes + '\'' +
                ", files='" + files + '\'' +
                ", id=" + id +
                ", images='" + images + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status=" + status +
                ", studentName='" + studentName + '\'' +
                ", taskId=" + taskId +
                ", titles='" + titles + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", videos='" + videos + '\'' +
                '}';
    }
}

