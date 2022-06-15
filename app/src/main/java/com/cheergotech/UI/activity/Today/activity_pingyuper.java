package com.cheergotech.UI.activity.Today;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.schema;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 今日评语-->已评语
 */
public class activity_pingyuper extends BaseActivity {
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
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    int Status = 0;
    int id;
    schema schema;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_pingyuper.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_pingyuper.class);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    public static void setAction(Activity context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_pingyuper.class);
        intent.putExtra(Constants.id, id);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_pingyuper;
    }

    @Override
    public void iniview() {
        id = getIntent().getIntExtra(Constants.id, 0);
        widgetimage7.getLine22().setVisibility(View.VISIBLE);
        initData();
    }

    @Override
    public void initData() {
        Datamodule.getInstance().commentinfo(id, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                schema = (com.cheergotech.UI.model.schema) object;
                backtitle.setconter(String.format(getString(R.string.pingyu_msg), schema.getStudentName()));
                mtitle.setText(Html.fromHtml(String.format(getString(R.string.p2), schema.getStudentName())));
                edittitle.setText(schema.getTitle());
                searchpmter.setText(schema.getDescribes());
                switch (schema.getStatus()) {
                    case 1:
                        setSearchpmter(sendchicke1);
                        break;
                    case 2:
                        setSearchpmter(sendchicke2);
                        break;
                }
            }

            @Override
            public void Onfall() {

            }
        });

    }

    @Override
    public void OnClick(View v) {

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