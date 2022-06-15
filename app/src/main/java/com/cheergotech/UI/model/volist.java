package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1711:49
 * Description请假VO
 */
public class volist {
    private int id;
    private int studentId;
    private String studentName;
    private String className;
    private String headImg;
    private String titles;
    private String describes;
    private String conversation;
    private String startTime;
    private String overTime;
    private String detailsImg;
    private int status;
    private String statusName;
    private double days;
    private String createTime;

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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "volist{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", headImg='" + headImg + '\'' +
                ", titles='" + titles + '\'' +
                ", describes='" + describes + '\'' +
                ", conversation='" + conversation + '\'' +
                ", startTime='" + startTime + '\'' +
                ", overTime='" + overTime + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", days=" + days +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
