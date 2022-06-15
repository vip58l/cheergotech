package com.cheergotech.UI.activity.Student;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 学生健康-详情-正常
 */
public class activity_temper extends BaseActivity {
    @BindView(R.id.imgage)
    ImageView imgage;
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.m1)
    TextView m1;
    @BindView(R.id.m2)
    TextView m2;
    @BindView(R.id.m3)
    TextView m3;
    @BindView(R.id.m4)
    TextView m4;
    @BindView(R.id.m5)
    TextView m5;
    @BindView(R.id.m6)
    TextView m6;
    @BindView(R.id.nickName)
    TextView nickName;
    @BindView(R.id.measuringTime)
    TextView measuringTime;
    public com.cheergotech.UI.model.healthy.healthlist healthlist;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_temper.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_temper.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_temper;
    }

    @Override
    public void iniview() {
        String stringExtra = getIntent().getStringExtra(Constants.JSON);
        healthlist = gson.fromJson(stringExtra, com.cheergotech.UI.model.healthy.healthlist.class);
        ImageLoadHelper.glideShowImageWithUrl(context, healthlist.getHeadImg(), imgage);
        nickName.setText(healthlist.getStudentName());
        measuringTime.setText(TextUtils.isEmpty(healthlist.getMeasuringTime()) ? healthlist.getHealthStatusName() : Config.stampToDate(healthlist.getMeasuringTime(),null));
        m1.setText(String.valueOf(healthlist.getHeight()).equals("null") ? "-" : String.valueOf(healthlist.getHeight()));
        m2.setText(String.valueOf(healthlist.getWeight()).equals("null") ? "-" : String.valueOf(healthlist.getWeight()));
        m3.setText(String.valueOf(healthlist.getVision()).equals("null") ? "-" : String.valueOf(healthlist.getVision()));
        m4.setText(String.valueOf(healthlist.getTemperature()).equals("null") ? "-" : String.valueOf(healthlist.getTemperature()));
        m5.setText(String.valueOf(healthlist.getHeartRate()).equals("null") ? "-" : String.valueOf(healthlist.getHeartRate()));
        m6.setText(String.valueOf(healthlist.getBloodOxygen()).equals("null") ? "-" : String.valueOf(healthlist.getBloodOxygen()));
        backtitle.setTitleName(healthlist.getStudentName());


        if (healthlist.getHeartRate() <= 0) {
            m5.setText("-");
        }
        if (healthlist.getBloodOxygen() <= 0) {
            m6.setText("-");
        }
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