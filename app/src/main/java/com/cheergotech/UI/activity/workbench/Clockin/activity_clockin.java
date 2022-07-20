package com.cheergotech.UI.activity.workbench.Clockin;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.workbench.OAapproval.activity_Initiat_approval;
import com.cheergotech.UI.dialog.DialogMessage10;
import com.cheergotech.UI.dialog.DialogMessage17;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;
import butterknife.OnClick;

public class activity_clockin extends BaseActivity {

    @BindView(R.id.tm1)
    TextView tm1;
    @BindView(R.id.time)
    TextView time;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_clockin.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_clockin;
    }

    @Override
    public void iniview() {
        tm1.setText("下班打卡");
        time.setText("13:11:22");

    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.cllin, R.id.loquer, R.id.tv_count, R.id.send_back,})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.cllin: {
                DialogMessage10 dialogMessage10 = DialogMessage10.showTo(context, "未进入考勤范围", "外勤打卡须填写外勤信息", "取消", "填写", new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int position) {
                        switch (position) {
                            case 1:
                                break;
                            case 2:
                                activity_load_position.setAction(activity);
                                break;
                        }


                    }
                });
                dialogMessage10.contextColor();
                dialogMessage10.WindowManagerCENTER();

                DialogMessage17.show(context, null);
                DialogMessage17.show(context, "已超出考勤时间", "您可以通过OA审批补卡\n系管理员操作", null);

                DialogMessage10 ss = DialogMessage10.showTo(context, "已超出规则时间", "本次考勤将判定为迟到\n确定打卡吗？", "取消", "打卡", new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int position) {
                        switch (position) {
                            case 1:
                                break;
                            case 2:
                                activity_load_position.setAction(activity);
                                break;
                        }


                    }
                });
                ss.contextColor();
                ss.WindowManagerCENTER();

                break;
            }
            case R.id.loquer:
                //查看规则
                activity_attendancerules.setAction(activity);
                break;

            case R.id.tv_count:
                //统计
                activity_count_Statistics.setAction(activity);
                //统计
                activity_xcountkin.setAction(activity);
                break;
            case R.id.send_back:
                //请假
                activity_Initiat_approval.setAction(context);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }
}