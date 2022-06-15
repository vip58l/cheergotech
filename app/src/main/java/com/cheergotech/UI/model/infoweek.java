package com.cheergotech.UI.model;

import java.util.List;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2513:51
 * Description
 */
public class infoweek<T, K> {
    List<T> amCourse;
    List<K> pmCourse;

    public List<T> getAmCourse() {
        return amCourse;
    }

    public void setAmCourse(List<T> amCourse) {
        this.amCourse = amCourse;
    }

    public List<K> getPmCourse() {
        return pmCourse;
    }

    public void setPmCourse(List<K> pmCourse) {
        this.pmCourse = pmCourse;
    }
}
