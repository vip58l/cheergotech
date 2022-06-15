package com.cheergotech.UI.model;

import java.util.List;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2415:21
 * Description
 */
public class sendDTO {
    private int characterLevel;
    private int classId;
    private String describes;
    private int extracurricularLevel;
    private int interpersonalLevel;
    private List<Integer> receiveIds;
    private int sportsLevel;
    private int studyLevel;
    private int synthesisLevel;



    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

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

    public int getExtracurricularLevel() {
        return extracurricularLevel;
    }

    public void setExtracurricularLevel(int extracurricularLevel) {
        this.extracurricularLevel = extracurricularLevel;
    }

    public int getInterpersonalLevel() {
        return interpersonalLevel;
    }

    public void setInterpersonalLevel(int interpersonalLevel) {
        this.interpersonalLevel = interpersonalLevel;
    }

    public List<Integer> getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(List<Integer> receiveIds) {
        this.receiveIds = receiveIds;
    }

    public int getSportsLevel() {
        return sportsLevel;
    }

    public void setSportsLevel(int sportsLevel) {
        this.sportsLevel = sportsLevel;
    }

    public int getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(int studyLevel) {
        this.studyLevel = studyLevel;
    }

    public int getSynthesisLevel() {
        return synthesisLevel;
    }

    public void setSynthesisLevel(int synthesisLevel) {
        this.synthesisLevel = synthesisLevel;
    }
}
