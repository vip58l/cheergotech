package com.cheergotech.UI.model;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/299:24
 * Description
 */
public class Handlertimeout extends Handler {
    public final static int delayMillis = 1000;
    private int timeout;
    private int timeoutdelay;
    private CallBcak callBcak;

    public Handlertimeout(int timeout, CallBcak callBcak) {
        this.timeout = timeout;
        this.callBcak = callBcak;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case Config.sussess:
                timeoutdelay++;
                if (timeoutdelay < timeout) {
                    sendEmptyMessageDelayed(Config.sussess, delayMillis);
                    if (callBcak != null) {
                        callBcak.OnSuccess();
                    }

                } else {
                    if (callBcak != null) {
                        timeoutdelay = 0;
                        callBcak.Onfall();
                    }

                }
                break;

        }
    }


}

