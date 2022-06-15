package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2315:50
 * Description
 */
public class ClassLeaveRefuseDto {
    String conversation;
    int id;

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClassLeaveRefuseDto{" +
                "conversation='" + conversation + '\'' +
                ", id=" + id +
                '}';
    }
}
