package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1611:26
 * Description 学生孩子信息
 */
public class childList {
    private int classId;
    private String className;
    private String createTime;
    private String deviceId;
    private int gradeId;
    private String gradeName;
    private String headImg;
    private int id;
    private String idNmber;
    private String nickName;
    private int numbers;
    private int schoolId;
    private String schoolName;
    private int sex;
    private String studentName;
    private String studentUuid;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNmber() {
        return idNmber;
    }

    public void setIdNmber(String idNmber) {
        this.idNmber = idNmber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(String studentUuid) {
        this.studentUuid = studentUuid;
    }

    @Override
    public String toString() {
        return "childlist2{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", createTime='" + createTime + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", id=" + id +
                ", idNmber='" + idNmber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", numbers=" + numbers +
                ", schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", sex=" + sex +
                ", studentName='" + studentName + '\'' +
                ", studentUuid='" + studentUuid + '\'' +
                '}';
    }
}
