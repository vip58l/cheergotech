package com.cheergotech.Base;

import android.app.Application;
import android.os.Environment;
import android.os.StrictMode;

import com.cheergotech.BuildConfig;
import com.cheergotech.ulist.BackgroundTasks;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

public class DemoApplication extends Application {

    public static DemoApplication demoApplication;

    public static DemoApplication instance() {
        if (demoApplication == null) {
            demoApplication = new DemoApplication();
        }
        return demoApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        demoApplication = this;
        BackgroundTasks.initInstance();
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_APPID, false);

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder =new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


    }


    //录音文件
    public static String UrlAudio = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "EhmFile/media/audio/";

    private void initFile() {
        //录音文件
        File audioFile = new File(UrlAudio);
        if (!audioFile.exists()) {
            audioFile.mkdirs();
        } else if (!audioFile.isDirectory()) {
            audioFile.delete();
            audioFile.mkdirs();
        }

        //拍摄图片文件
        File imageFile = new File(UrlAudio);
        if (!imageFile.exists()) {
            imageFile.mkdirs();
        } else if (!imageFile.isDirectory()) {
            imageFile.delete();
            imageFile.mkdirs();
        }

    }
}