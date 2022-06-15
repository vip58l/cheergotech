package com.cheergotech.UI.activity.Student;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage2;
import com.cheergotech.UI.dialog.DialogMessage7;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.messageSendDTO;
import com.cheergotech.UI.model.stuinfohealthreport;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学生健康-详情-异常
 */
public class activity_temperature extends BaseActivity {
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
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.tips1)
    TextView tips1;
    @BindView(R.id.tips2)
    TextView tips2;
    @BindView(R.id.tips3)
    TextView tips3;
    public com.cheergotech.UI.model.healthy.healthlist healthlist;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_temperature.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_temperature.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_temperature;
    }

    @Override
    public void iniview() {
        String stringExtra = getIntent().getStringExtra(Constants.JSON);
        healthlist = gson.fromJson(stringExtra, com.cheergotech.UI.model.healthy.healthlist.class);
        ImageLoadHelper.glideShowImageWithUrl(context, healthlist.getHeadImg(), headImg);
        nickName.setText(healthlist.getStudentName());
        measuringTime.setText(TextUtils.isEmpty(healthlist.getMeasuringTime()) ? healthlist.getHealthStatusName() : Config.stampToDate(healthlist.getMeasuringTime(), null));

        m1.setText(String.valueOf(healthlist.getHeight()).equals("null") ? "-" : String.valueOf(healthlist.getHeight()));
        m2.setText(String.valueOf(healthlist.getWeight()).equals("null") ? "-" : String.valueOf(healthlist.getWeight()));
        m3.setText(String.valueOf(healthlist.getVision()).equals("null") ? "-" : String.valueOf(healthlist.getVision()));

        m4.setText(String.valueOf(healthlist.getTemperature()).equals("null") ? "-" : String.valueOf(healthlist.getTemperature()));
        m5.setText(String.valueOf(healthlist.getHeartRate()).equals("null") ? "-" : String.valueOf(healthlist.getHeartRate()));
        m6.setText(String.valueOf(healthlist.getBloodOxygen()).equals("null") ? "-" : String.valueOf(healthlist.getBloodOxygen()));
        backtitle.setTitleName(healthlist.getStudentName());

        if (healthlist.getHeartRate() <= 0) {
            m5.setText("-");
            m5.setTextColor(getResources().getColor(R.color.ff929dbb));
        }
        if (healthlist.getBloodOxygen() <= 0) {
            m6.setText("-");
            m6.setTextColor(getResources().getColor(R.color.ff929dbb));
        }

        //提示异常内容
        tips1.setText(TextUtils.isEmpty(healthlist.getTemperatureTips()) ? "" : healthlist.getTemperatureTips());
        tips2.setText(TextUtils.isEmpty(healthlist.getHeartRateTips()) ? "" : healthlist.getHeartRateTips());
        tips3.setText(TextUtils.isEmpty(healthlist.getBloodOxygenTips()) ? "" : healthlist.getBloodOxygenTips());
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1: {
                //发送报告
                String msg = String.format(getString(R.string.msgtitle), healthlist.getStudentName());
                DialogMessage7.show(context, msg, new CallBcak() {
                    @Override
                    public void OnSuccess() {
                        //已发送成功
                        Toasts.success(1);
                    }

                    @Override
                    public void Onfall() {
                        //已发送失败
                        Toasts.showeorr();

                    }

                    @Override
                    public void setOnClickListener(int type) {
                        switch (type) {
                            case 1:
                                //Toasts.showeorr("取消");
                                break;
                            case 2: {
                                //学生信息-学生健康-生成报告
                                stuinfohealthreport stuinfohealthreport = new stuinfohealthreport();
                                stuinfohealthreport.setBloodOxygen(healthlist.getBloodOxygen() > 0 ? healthlist.getBloodOxygen() : 0);
                                stuinfohealthreport.setHeartRate(healthlist.getHeartRate() > 0 ? healthlist.getHeartRate() : 0);
                                stuinfohealthreport.setHeight(healthlist.getHeight() > 0 ? (int) healthlist.getHeight() : 0);
                                stuinfohealthreport.setStudentId(healthlist.getId());
                                if (!TextUtils.isEmpty(healthlist.getTemperature())) {
                                    stuinfohealthreport.setTemperature(Double.parseDouble(healthlist.getTemperature()));
                                }
                                stuinfohealthreport.setVision((int) healthlist.getVision());
                                stuinfohealthreport.setWeight((int) healthlist.getWeight());
                                Datamodule.getInstance().report(stuinfohealthreport, this);
                                break;
                            }
                        }
                    }
                });
            }
            break;
            case R.id.send2: {
                //通知家长学生信息-学生健康-通知家长
                DialogMessage2.show(context, new CallBcak() {
                    @Override
                    public void setOnClickListener(Object msg) {
                        //通知家长
                        messageSendDTO message = new messageSendDTO();
                        message.setStudentId(healthlist.getId());
                        message.setContent(msg.toString());
                        //学生信息-学生健康-通知家长
                        Datamodule.getInstance().notice(message, this);
                    }

                    @Override
                    public void OnSuccess() {
                        //已发送成功
                        Toasts.success(1);
                    }

                    @Override
                    public void Onfall() {
                        //已发送失败
                        Toasts.showeorr();

                    }
                });
            }
            break;
        }
    }

    @Override
    public void OnEorr() {

    }
}