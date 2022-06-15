package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1610:58
 * Description APP用户登录
 */
public class appLoginDto {
    private String account;
    private int channel;
    private String code;
    private String deviceId;
    private int osType;
    private String password;
    private String shareCode;
    private String sharer;
    private String valid;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getSharer() {
        return sharer;
    }

    public void setSharer(String sharer) {
        this.sharer = sharer;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "appLoginDto{" +
                "loginOut='" + account + '\'' +
                ", channel=" + channel +
                ", code='" + code + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", osType=" + osType +
                ", password='" + password + '\'' +
                ", shareCode='" + shareCode + '\'' +
                ", sharer='" + sharer + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }
}
