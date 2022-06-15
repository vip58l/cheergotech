package com.cheergotech.UI.model;

import java.util.List;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2415:30
 * Description
 */
public class sendDTO2 {
    private int classId;
    private String describes;
    private List<Integer> receiveIds;
    private int status;
    private String title;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public List<Integer> getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(List<Integer> receiveIds) {
        this.receiveIds = receiveIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
