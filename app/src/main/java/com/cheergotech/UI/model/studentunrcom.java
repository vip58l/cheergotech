package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1917:49 学期评语--今日评语-
 * Description
 */
public class studentunrcom {
    private String id;
    private int studentId;
    private String studentName;
    private int status;
    private String statusName;
    private boolean isboolean;
    public boolean isIsboolean() {
        return isboolean;
    }

    public void setIsboolean(boolean isboolean) {
        this.isboolean = isboolean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "studentunrcom{" +
                "id='" + id + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", isboolean=" + isboolean +
                '}';
    }
}
