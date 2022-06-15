package com.cheergotech.UI.activity.Term;

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
import com.cheergotech.UI.dialog.DialogMessage11;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.sendDTO;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.JsonUitl;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.RatingStar;
import com.cheergotech.widget.widgetimage7;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 未评语 学期评语详情页
 */
public class activity_termo extends BaseActivity {
    List<com.cheergotech.UI.model.studentunrcom> studentunrcom;
    List<Integer> idslist = new ArrayList<>();
    String json;
    @BindView(R.id.mtitle)
    TextView mtitle;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.p1)
    RatingStar p1;
    @BindView(R.id.p2)
    RatingStar p2;
    @BindView(R.id.p3)
    RatingStar p3;
    @BindView(R.id.p4)
    RatingStar p4;
    @BindView(R.id.p5)
    RatingStar p5;
    @BindView(R.id.p6)
    RatingStar p6;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;

    @BindView(R.id.widgetimage7)
    widgetimage7 widgetimage7;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_termo.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_termo.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivity(intent);
    }

    public static void setAction(Activity context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_termo.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_termo;
    }

    @Override
    public void iniview() {
        json = getIntent().getStringExtra(Constants.JSON);
        studentunrcom = JsonUitl.stringToList(json, com.cheergotech.UI.model.studentunrcom.class);
        widgetimage7.getLine22().setVisibility(View.VISIBLE);
        widgetimage7.getLine22().setOnClickListener(view -> {
            //选择评论消息
            DialogMessage11.show(context, new CallBcak() {
                @Override
                public void Onfall() {

                }

                @Override
                public void setMtext(String msg) {
                    edit.setText(msg);
                }

            });
        });

        //点击切换评语内容
        p1.settextMsg(tv1);
        p2.settextMsg(tv2);
        p3.settextMsg(tv3);
        p4.settextMsg(tv4);
        p5.settextMsg(tv5);
        p6.settextMsg(tv6);
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
    @OnClick({R.id.booter})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.booter:
                String smsg = edit.getText().toString();
                if (TextUtils.isEmpty(smsg)) {
                    Toasts.toastMessage(getString(R.string.msg1));
                    return;
                }
                sendDTO sendDTO = new sendDTO();
                sendDTO.setClassId(msgconfig.getClassId());
                sendDTO.setDescribes(smsg);
                sendDTO.setReceiveIds(idslist);     //学生ID集合
                sendDTO.setExtracurricularLevel(p3.getmGrade()); //课外评级
                sendDTO.setInterpersonalLevel(p5.getmGrade());   //人际评级
                sendDTO.setSportsLevel(p4.getmGrade());          //体育评级
                sendDTO.setStudyLevel(p2.getmGrade());           //学习评级
                sendDTO.setSynthesisLevel(p1.getmGrade());       //综合评级(1-5)
                sendDTO.setCharacterLevel(p6.getmGrade());      //性格评级
                Datamodule.getInstance().semestersend(sendDTO, new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void OnSuccess() {
                        Toasts.showShort(getString(R.string.takl));
                        setResult(Config.setResult);
                        finish();
                    }
                });
                break;
        }
    }

    @Override
    public void OnEorr() {


    }

}