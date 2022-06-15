package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1917:46
 * Description
 */
public class studentunReviewNumber<T> {
    int unReviewNumber;
    int praisedNumber;
    int notPraiseNumber;
    int reviewNumber;
    T pageInfo;

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getUnReviewNumber() {
        return unReviewNumber;
    }

    public void setUnReviewNumber(int unReviewNumber) {
        this.unReviewNumber = unReviewNumber;
    }

    public int getPraisedNumber() {
        return praisedNumber;
    }

    public void setPraisedNumber(int praisedNumber) {
        this.praisedNumber = praisedNumber;
    }

    public int getNotPraiseNumber() {
        return notPraiseNumber;
    }

    public void setNotPraiseNumber(int notPraiseNumber) {
        this.notPraiseNumber = notPraiseNumber;
    }

    public T getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(T pageInfo) {
        this.pageInfo = pageInfo;
    }
}
