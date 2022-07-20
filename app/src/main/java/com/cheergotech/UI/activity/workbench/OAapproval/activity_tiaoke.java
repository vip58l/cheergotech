package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1819:26
 * Description 调课
 */
public class activity_tiaoke extends BaseActivity {

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_tiaoke.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_tiaoke;
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
