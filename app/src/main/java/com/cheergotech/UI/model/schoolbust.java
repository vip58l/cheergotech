package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2613:31
 * Description
 */
public class schoolbust {
    private int id;
    private int schoolId;
    private String busName;
    private String busCardId;
    private String linesInfo;
    private String workStart;
    private String workStop;
    private String manager;
    private String phones;
    private double longitude;
    private double latitude;
    private int isNearest;
    private double distance;
    private String departureStation;
    private String terminusStation;

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

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusCardId() {
        return busCardId;
    }

    public void setBusCardId(String busCardId) {
        this.busCardId = busCardId;
    }

    public String getLinesInfo() {
        return linesInfo;
    }

    public void setLinesInfo(String linesInfo) {
        this.linesInfo = linesInfo;
    }

    public String getWorkStart() {
        return workStart;
    }

    public void setWorkStart(String workStart) {
        this.workStart = workStart;
    }

    public String getWorkStop() {
        return workStop;
    }

    public void setWorkStop(String workStop) {
        this.workStop = workStop;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getIsNearest() {
        return isNearest;
    }

    public void setIsNearest(int isNearest) {
        this.isNearest = isNearest;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getTerminusStation() {
        return terminusStation;
    }

    public void setTerminusStation(String terminusStation) {
        this.terminusStation = terminusStation;
    }

    @Override
    public String toString() {
        return "schoolbust{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", busName='" + busName + '\'' +
                ", busCardId='" + busCardId + '\'' +
                ", linesInfo='" + linesInfo + '\'' +
                ", workStart='" + workStart + '\'' +
                ", workStop='" + workStop + '\'' +
                ", manager='" + manager + '\'' +
                ", phones='" + phones + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", isNearest=" + isNearest +
                ", distance=" + distance +
                ", departureStation='" + departureStation + '\'' +
                ", terminusStation='" + terminusStation + '\'' +
                '}';
    }
}
