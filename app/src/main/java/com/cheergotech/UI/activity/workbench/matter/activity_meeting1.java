package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage20;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1916:12
 * 会议
 */
public class activity_meeting1 extends BaseActivity {

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_meeting1.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_meeting;
    }

    @Override
    public void iniview() {
        DialogMessage20.show(context, null);
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
