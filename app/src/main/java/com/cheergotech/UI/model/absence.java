package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1814:22
 * Description
 */
public class absence {
    private int id;
    private int schoolId;
    private int sex;
    private int gradeId;
    private int classId;
    private String studentUuid;
    private String studentName;
    private String nickName;
    private String deviceId;
    private String headImg;
    private String familyPhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(String studentUuid) {
        this.studentUuid = studentUuid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    @Override
    public String toString() {
        return "absence{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", sex=" + sex +
                ", gradeId=" + gradeId +
                ", classId=" + classId +
                ", studentUuid='" + studentUuid + '\'' +
                ", studentName='" + studentName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", headImg='" + headImg + '\'' +
                ", familyPhone='" + familyPhone + '\'' +
                '}';
    }
}
