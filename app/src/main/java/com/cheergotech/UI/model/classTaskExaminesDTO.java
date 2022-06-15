package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2719:58
 * Description classTaskExaminesDTO
 * 作业信息-批改作业-退回重做
 */
public class classTaskExaminesDTO {
    private int courseInfo;
    private String ids;
    private String remarks;

    public int getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(int courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "classTaskExaminesDTO{" +
                "courseInfo=" + courseInfo +
                ", ids='" + ids + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
