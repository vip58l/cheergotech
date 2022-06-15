package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author 设备信息
 * Data 2022/6/110:21
 * Description
 */
public class device {
    private String detailsInfo;
    private String latitude;
    private int id;
    private String deviceId;
    private String longitude;

    public String getDetailsInfo() {
        return detailsInfo;
    }

    public void setDetailsInfo(String detailsInfo) {
        this.detailsInfo = detailsInfo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "device{" +
                "detailsInfo='" + detailsInfo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
