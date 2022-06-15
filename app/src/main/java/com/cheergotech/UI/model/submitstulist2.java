package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2718:43
 * Description
 */
public class submitstulist2 {
    private int id;
    private int status;
    private String statusName;
    private int studentId;
    private String studentName;
    private boolean isboolean;

    public boolean isIsboolean() {
        return isboolean;
    }

    public void setIsboolean(boolean isboolean) {
        this.isboolean = isboolean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "submitstulist2{" +
                "id=" + id +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", isboolean=" + isboolean +
                '}';
    }
}
