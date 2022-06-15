package com.cheergotech.UI.activity.Web;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.cheergotech.ulist.Toasts;

//Javascript调用本类方法
//html js调用需要在方法写上aa.xx()
public class JsInterface {
    private String TAG = JsInterface.class.getSimpleName();
    private Context context;

    public JsInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public static void showToast(String msg) {
        Toasts.toastMessage(msg);
    }

    @JavascriptInterface
    public static void myToashow(String msg) {
        Toasts.toastMessage(msg);
    }




}