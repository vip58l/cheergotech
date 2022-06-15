package com.cheergotech.UI.model.healthy;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1717:34
 * Description
 */
public class healthlist {
    private int id;
    private String studentName;
    private String nickName;
    private String headImg;
    private double height;
    private double weight;
    private double vision;
    private String temperature;
    private String temperatureTips;
    private double bmi;
    private int heartRate;
    private String heartRateTips;
    private int bloodOxygen;
    private String bloodOxygenTips;
    private int healthStatus;
    private String healthStatusName;
    private String measuringTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVision() {
        return vision;
    }

    public void setVision(double vision) {
        this.vision = vision;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureTips() {
        return temperatureTips;
    }

    public void setTemperatureTips(String temperatureTips) {
        this.temperatureTips = temperatureTips;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getHeartRateTips() {
        return heartRateTips;
    }

    public void setHeartRateTips(String heartRateTips) {
        this.heartRateTips = heartRateTips;
    }

    public int getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(int bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public String getBloodOxygenTips() {
        return bloodOxygenTips;
    }

    public void setBloodOxygenTips(String bloodOxygenTips) {
        this.bloodOxygenTips = bloodOxygenTips;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getHealthStatusName() {
        return healthStatusName;
    }

    public void setHealthStatusName(String healthStatusName) {
        this.healthStatusName = healthStatusName;
    }

    public String getMeasuringTime() {
        return measuringTime;
    }

    public void setMeasuringTime(String measuringTime) {
        this.measuringTime = measuringTime;
    }

    @Override
    public String toString() {
        return "healthlist{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", vision=" + vision +
                ", temperature='" + temperature + '\'' +
                ", temperatureTips='" + temperatureTips + '\'' +
                ", bmi=" + bmi +
                ", heartRate=" + heartRate +
                ", heartRateTips='" + heartRateTips + '\'' +
                ", bloodOxygen=" + bloodOxygen +
                ", bloodOxygenTips='" + bloodOxygenTips + '\'' +
                ", healthStatus=" + healthStatus +
                ", healthStatusName='" + healthStatusName + '\'' +
                ", measuringTime='" + measuringTime + '\'' +
                '}';
    }
}
