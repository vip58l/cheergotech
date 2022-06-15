package com.cheergotech.UI.model;

import static com.cheergotech.ulist.Constants.PER_USER_MODEL;

import android.content.SharedPreferences;

import com.cheergotech.Base.DemoApplication;
import com.cheergotech.ulist.Constants;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 用户数据
 */
public class UserInfo implements Serializable {

    private static UserInfo sUserInfo;
    private String account;
    private String headImg;
    private int id;
    private String nickName;
    private String phone;
    private String realName;
    private int role;
    private String schoolIco;
    private int schoolId;
    private String schoolName;
    private String uuid;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        setUserInfo(this);
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
        setUserInfo(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setUserInfo(this);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        setUserInfo(this);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        setUserInfo(this);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
        setUserInfo(this);
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
        setUserInfo(this);
    }

    public String getSchoolIco() {
        return schoolIco;
    }

    public void setSchoolIco(String schoolIco) {
        this.schoolIco = schoolIco;
        setUserInfo(this);
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
        setUserInfo(this);
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        setUserInfo(this);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
        setUserInfo(this);
    }

    /**
     * 请取文件 json解析返回对像
     *
     * @return
     */
    public synchronized static UserInfo getInstance() {
        SharedPreferences shareInfo = DemoApplication.instance().getSharedPreferences(Constants.USERINFO, 0);
        String json = shareInfo.getString(PER_USER_MODEL, "");
        sUserInfo = new Gson().fromJson(json, UserInfo.class);
        if (sUserInfo == null) {
            sUserInfo = new UserInfo();
        }
        return sUserInfo;
    }

    /**
     * 写到本地文件中json
     *
     * @param info
     */
    public void setUserInfo(UserInfo info) {
        SharedPreferences shareInfo = DemoApplication.instance().getSharedPreferences(Constants.USERINFO, 0);
        SharedPreferences.Editor editor = shareInfo.edit();
        editor.putString(PER_USER_MODEL, new Gson().toJson(info));
        editor.commit();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "loginOut='" + account + '\'' +
                ", headImg='" + headImg + '\'' +
                ", id=" + id +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", role=" + role +
                ", schoolIco='" + schoolIco + '\'' +
                ", schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
