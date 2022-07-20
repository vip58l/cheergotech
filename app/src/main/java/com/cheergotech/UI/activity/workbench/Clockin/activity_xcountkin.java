package com.cheergotech.UI.activity.workbench.Clockin;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.ulist.Config;

/**
 * 统计
 */
public class activity_xcountkin extends BaseActivity {

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_xcountkin.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_xcountkin;
    }

    @Override
    public void iniview() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

    }
}