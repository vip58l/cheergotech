package com.cheergotech.UI.activity.viecode;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.ulist.Config;

import butterknife.BindView;

/**
 * 关于我们
 */
public class activity_viecode extends BaseActivity {

    @BindView(R.id.viecode)
    TextView viecode;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_viecode.class);
        context.startActivity(intent);
    }


    @Override
    protected int getview() {
        return R.layout.activity_viecode;
    }

    @Override
    public void iniview() {
        viecode.setText(String.format("当前版本 %s", Config.getVersionName(context)));

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