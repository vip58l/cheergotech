package com.cheergotech.UI.model;

import android.content.SharedPreferences;

import com.cheergotech.Base.DemoApplication;
import com.cheergotech.ulist.Constants;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1415:48
 * Description 默认选中配置
 */
public class Msgconfig {
    public static Msgconfig msgconfig;
    private boolean login;               //登录状态
    private boolean check_register;      //注册选中
    private boolean check_login;         //登录选中
    private String accessToken;          //token
    private String roleName;             //角色名
    private List<childList> childList;   //list保存孩子信息
    private int classId;                 //班级ID
    private String Names;                //班级名称

    public String getNames() {
        return this.Names;
    }

    public void setNames(String names) {
        this.Names = names;
        setMsgconfig(this);
    }

    public boolean isCheck_login() {
        return check_login;
    }

    public void setCheck_login(boolean check_login) {
        this.check_login = check_login;
        setMsgconfig(this);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        setMsgconfig(this);
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
        setMsgconfig(this);
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
        setMsgconfig(this);
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
        setMsgconfig(this);
    }

    public boolean isCheck_register() {
        return check_register;
    }

    public void setCheck_register(boolean check_register) {
        this.check_register = check_register;
        setMsgconfig(this);
    }

    public List<com.cheergotech.UI.model.childList> getChildList() {
        return childList;
    }

    public void setChildList(List<com.cheergotech.UI.model.childList> childList) {
        this.childList = childList;
        setMsgconfig(this);
    }

    /**
     * 请取文件 json解析返回对像
     *
     * @return
     */
    public synchronized static Msgconfig getInstance() {
        SharedPreferences shareInfo = DemoApplication.instance().getSharedPreferences(Constants.Msgconfig, 0);
        String json = shareInfo.getString(Constants.Msg_config, "");
        msgconfig = new Gson().fromJson(json, Msgconfig.class);
        if (msgconfig == null) {
            msgconfig = new Msgconfig();
        }
        return msgconfig;
    }

    /**
     * 写到本地文件中json
     *
     * @param info
     */
    public void setMsgconfig(Msgconfig info) {
        SharedPreferences shareInfo = DemoApplication.instance().getSharedPreferences(Constants.Msgconfig, 0);
        SharedPreferences.Editor editor = shareInfo.edit();
        editor.putString(Constants.Msg_config, new Gson().toJson(info));
        editor.commit();
    }


    @Override
    public String toString() {
        return "Msgconfig{" +
                "login=" + login +
                ", check_register=" + check_register +
                ", check_login=" + check_login +
                ", accessToken='" + accessToken + '\'' +
                ", roleName='" + roleName + '\'' +
                ", childList=" + childList +
                '}';
    }
}
