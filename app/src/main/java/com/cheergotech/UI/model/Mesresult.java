package com.cheergotech.UI.model;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1611:31
 * Description 所谓的这些T、E、K、V、？全都属于java泛型的通配符
 * E - Element (在集合中使用，因为集合中存放的是元素) Collection
 * T - Type（Java 类） public T Test1(T t){}
 * K - Key（键） Map<K,V>
 * V - Value（值）
 * N - Number（数值类型）List
 * ？ - 表示不确定的java类型 List<? super Integer> intgerList;
 */
public class Mesresult<T, k> {
    private String accessToken;
    private String roleName;
    private k childList;
    private T userInfo;
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public k getChildList() {
        return childList;
    }

    public void setChildList(k childList) {
        this.childList = childList;
    }
}
