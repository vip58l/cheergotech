package com.cheergotech.ulist;

import android.util.Log;

import com.cheergotech.BuildConfig;

public class Logi {

    public static void d(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }

    }


    /**
     * 请求数据
     * @param TAG
     * @param msg
     */
    public static void request(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "请求:" + msg);
        }

    }

    /**
     * 响应数据
     * @param TAG
     * @param msg
     */
    public static void response(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "响应:" + msg);
        }

    }

    public static void e(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }

    }


}
