package com.cheergotech.UI.activity.joblist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage10;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.classTaskExaminesDTO;
import com.cheergotech.UI.model.submitstulist2;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.JsonUitl;
import com.cheergotech.ulist.Logi;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.Backtitle;
import com.cheergotech.widget.RatingStar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业详情-批量批改
 */
public class acetivity_back extends BaseActivity {
    String TAG = acetivity_back.class.getSimpleName();
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.ctitle)
    TextView ctitle;
    @BindView(R.id.edittitle)
    EditText edittitle;
    @BindView(R.id.ratingStar)
    RatingStar ratingStar;
    @BindView(R.id.sexmsg)
    TextView sexmsg;
    String json;
    List<submitstulist2> mlist;
    String[] stringArray;
    StringBuffer sb, sb2;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_back.class);
        context.startActivity(intent);
    }

    public static void setAction(Activity context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_back.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_acetivity_back;
    }

    @Override
    public void iniview() {
        stringArray = getResources().getStringArray(R.array.table3);
        json = getIntent().getStringExtra(Constants.JSON);
        mlist = JsonUitl.stringToList(json, submitstulist2.class);
        ratingStar.setCallBcak(new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int type) {
                if (type > 0) {
                    sexmsg.setText(stringArray[type - 1]);
                }
            }
        });
        initData();
    }

    @Override
    public void initData() {
        sb = new StringBuffer();
        sb2 = new StringBuffer();
        for (int i = 0; i < mlist.size(); i++) {
            submitstulist2 st = mlist.get(i);
            if (i < mlist.size()) {
                Logi.d(TAG, "initData: 11" + i);
                sb.append(String.format("%s,", st.getStudentName()));
                sb2.append(String.format("%s,", st.getStudentId()));
            } else {
                Logi.d(TAG, "initData:22 " + i);

                sb.append(String.format("%s", st.getStudentName()));
                sb2.append(String.format("%s", st.getStudentId()));

            }
        }
        ctitle.setText(Html.fromHtml(String.format(getString(R.string.pl), sb.toString())));
    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
        String msge = edittitle.getText().toString();
        classTaskExaminesDTO classTaskExaminesDTO = new classTaskExaminesDTO();
        classTaskExaminesDTO.setIds(sb2.toString());
        classTaskExaminesDTO.setCourseInfo(ratingStar.getmGrade());
        classTaskExaminesDTO.setRemarks(msge);
        String json = gson.toJson(classTaskExaminesDTO);
        switch (v.getId()) {
            case R.id.send1: {
                //作业批量退回
                if (TextUtils.isEmpty(msge)) {
                    Toasts.toastMessage(getString(R.string.toast11));
                    return;
                }
                DialogMessage10 dialogMessage10 = DialogMessage10.showTo(context, "作业退回", "是否退回 " + sb.toString() + " 的作业？", "取消", "退回", new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int type) {
                        if (type == 2) {
                            Datamodule.getInstance().classtaskreject(json, this);
                        }

                    }

                    @Override
                    public void OnSuccess() {
                        setResult(Config.setResult);
                        Toasts.success("作业退出成功");
                        finish();
                    }
                });
                dialogMessage10.contextColor();
                break;
            }
            case R.id.send2: {
                if (TextUtils.isEmpty(msge)) {
                    Toasts.toastMessage(getString(R.string.toast11));
                    return;
                }
                //作业批量提交
                Datamodule.getInstance().classtaskexamines(json, new CallBcak() {
                    @Override
                    public void OnSuccess() {
                        setResult(Config.setResult);
                        Toasts.success("已批改");
                        finish();
                    }

                    @Override
                    public void Onfall() {

                    }
                });
                break;
            }
        }
    }

    @Override
    public void OnEorr() {

    }
}