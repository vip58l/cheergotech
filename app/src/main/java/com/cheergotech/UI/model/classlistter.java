package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2015:36
 * Description 教师-获取班级信息
 */
public class classlistter {
    private String classLogo;
    private String classUuid;
    private String createTime;
    private String createUser;
    private int gradeId;
    private int id;
    private int isDelete;
    private int levers;
    private int mortersId;
    private String names;
    private int online;
    private String position;
    private String remarks;
    private int schoolId;
    private int teacherId;
    private int types;
    private String updateTime;
    private String updateUser;

    public String getClassLogo() {
        return classLogo;
    }

    public void setClassLogo(String classLogo) {
        this.classLogo = classLogo;
    }

    public String getClassUuid() {
        return classUuid;
    }

    public void setClassUuid(String classUuid) {
        this.classUuid = classUuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getLevers() {
        return levers;
    }

    public void setLevers(int levers) {
        this.levers = levers;
    }

    public int getMortersId() {
        return mortersId;
    }

    public void setMortersId(int mortersId) {
        this.mortersId = mortersId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "classlistter{" +
                "classLogo='" + classLogo + '\'' +
                ", classUuid='" + classUuid + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", gradeId=" + gradeId +
                ", id=" + id +
                ", isDelete=" + isDelete +
                ", levers=" + levers +
                ", mortersId=" + mortersId +
                ", names='" + names + '\'' +
                ", online=" + online +
                ", position='" + position + '\'' +
                ", remarks='" + remarks + '\'' +
                ", schoolId=" + schoolId +
                ", teacherId=" + teacherId +
                ", types=" + types +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
