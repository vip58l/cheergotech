package com.cheergotech.UI.activity.Today;

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
import com.cheergotech.UI.dialog.DialogMessage11;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.studentunrcom;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.JsonUitl;
import com.cheergotech.ulist.Toasts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 今日评语
 */
public class activity_pingyu extends BaseActivity {
    @BindView(R.id.widgetimage7)
    com.cheergotech.widget.widgetimage7 widgetimage7;
    @BindView(R.id.edittitle)
    EditText edittitle;
    @BindView(R.id.searchpmter)
    EditText searchpmter;
    @BindView(R.id.sendchicke1)
    TextView sendchicke1;
    @BindView(R.id.sendchicke2)
    TextView sendchicke2;
    @BindView(R.id.mtitle)
    TextView mtitle;
    int Status = 0;
    String json;
    List<studentunrcom> studentunrcom;
    List<Integer> idslist = new ArrayList<>();

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_pingyu.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_pingyu.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivity(intent);
    }

    public static void setAction(Activity context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_pingyu.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_pingyu;
    }

    @Override
    public void iniview() {
        json = getIntent().getStringExtra(Constants.JSON);
        studentunrcom = JsonUitl.stringToList(json, studentunrcom.class);
        widgetimage7.getLine22().setVisibility(View.VISIBLE);
        widgetimage7.getLine22().setOnClickListener(view -> {
            //选择评论消息
            DialogMessage11.show(context, new CallBcak() {
                @Override
                public void Onfall() {

                }

                @Override
                public void setMtext(String msg) {
                    searchpmter.setText(msg);
                }

            });
        });
        initData();
    }

    @Override
    public void initData() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < studentunrcom.size(); i++) {
            com.cheergotech.UI.model.studentunrcom st = this.studentunrcom.get(i);
            if (i < studentunrcom.size() - 1) {
                sb.append(String.format("%s,", st.getStudentName()));
            } else {
                sb.append(String.format("%s", st.getStudentName()));
            }
            idslist.add(st.getStudentId());
        }
        mtitle.setText(Html.fromHtml(String.format(getString(R.string.p2), sb.toString())));
    }

    @Override
    @OnClick({R.id.send1, R.id.sendchicke1, R.id.sendchicke2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                String title = edittitle.getText().toString();
                String contect = searchpmter.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    Toasts.toastMessage(getString(R.string.pingyu1));
                    return;
                }
                if (TextUtils.isEmpty(contect)) {
                    Toasts.toastMessage(getString(R.string.pingyu2));
                    return;
                }
                if (Status == 0) {
                    Toasts.toastMessage(getString(R.string.pingyu7));
                    return;
                }
                DialogMessage10.show(context, null, null, null, null, new CallBcak() {
                    @Override
                    public void setOnClickListener(int type) {
                        switch (type) {
                            case 1:
                                break;
                            case 2:
                                Datamodule.getInstance().commentsend(idslist, title, contect, Status, this);
                                break;
                        }
                    }

                    @Override
                    public void OnSuccess() {
                        setResult(Config.setResult);
                        Toasts.toastMessage(getString(R.string.pingyu6));
                        finish();

                    }


                });
                break;
            case R.id.sendchicke1:
                Status = 1;
                dmefault(sendchicke2);
                setSearchpmter(sendchicke1);
                break;
            case R.id.sendchicke2:
                Status = 2;
                dmefault(sendchicke1);
                setSearchpmter(sendchicke2);
                break;
            default:

        }
    }

    @Override
    public void OnEorr() {

    }

    private void setSearchpmter(TextView view) {
        view.setBackground(context.getResources().getDrawable(R.drawable.it325dp));
        view.setTextColor(context.getResources().getColor(R.color.white));
    }

    /**
     * 今日评价背景切换
     *
     * @param view
     */
    private void dmefault(TextView view) {
        view.setBackground(context.getResources().getDrawable(R.drawable.item123));
        view.setTextColor(context.getResources().getColor(R.color.ff202020));
    }
}