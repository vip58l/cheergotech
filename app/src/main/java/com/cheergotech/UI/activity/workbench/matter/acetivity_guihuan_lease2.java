package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1918:47
 * 事项-待办事项-租借
 */
public class acetivity_guihuan_lease2 extends BaseActivity {

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_guihuan_lease2.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_guihuan_lease2;
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
