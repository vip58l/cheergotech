package com.cheergotech.UI.model;

/**
 * 校车途经点
 */
public class LinesInfobus {
    private int serial;
    private double latitude;
    private double longitude;
    private String siteName;
    private String address;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LinesInfobus{" +
                "serial=" + serial +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", siteName='" + siteName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
