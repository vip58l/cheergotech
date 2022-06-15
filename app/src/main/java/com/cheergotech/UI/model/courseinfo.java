package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2514:41
 * Description
 */
public class courseinfo<T> {
  T courseInfo;
    int week;

  public T getCourseInfo() {
    return courseInfo;
  }

  public void setCourseInfo(T courseInfo) {
    this.courseInfo = courseInfo;
  }

  public int getWeek() {
    return week;
  }

  public void setWeek(int week) {
    this.week = week;
  }
}
