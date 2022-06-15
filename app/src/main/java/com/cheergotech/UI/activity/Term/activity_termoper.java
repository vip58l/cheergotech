package com.cheergotech.UI.activity.Term;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.semesterinfo;
import com.cheergotech.UI.model.sendDTO;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.RatingStar;
import com.cheergotech.widget.widgetimage7;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已评学期评语详情页
 */
public class activity_termoper extends BaseActivity {
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
    @BindView(R.id.widgetimage7)
    widgetimage7 widgetimage7;

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

    int id;
    semesterinfo semesterinfo;


    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_termoper.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_termoper.class);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_termoper;
    }

    @Override
    public void iniview() {
        id = getIntent().getIntExtra(Constants.id, 0);
        widgetimage7.getLine22().setVisibility(View.VISIBLE);
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
        Datamodule.getInstance().semesterinfo(id, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                semesterinfo = (com.cheergotech.UI.model.semesterinfo) object;
                mtitle.setText(Html.fromHtml(String.format(getString(R.string.p2), semesterinfo.getStudentName())));
                edit.setText(semesterinfo.getDescribes());
                p1.setnumber(semesterinfo.getSynthesisLevel());
                p2.setnumber(semesterinfo.getStudyLevel());
                p3.setnumber(semesterinfo.getExtracurricularLevel());
                p4.setnumber(semesterinfo.getSportsLevel());
                p5.setnumber(semesterinfo.getInterpersonalLevel());
                p6.setnumber(semesterinfo.getCharacterLevel());

                p1.setEnabled(false);
                p2.setEnabled(false);
                p3.setEnabled(false);
                p4.setEnabled(false);
                p5.setEnabled(false);
                p6.setEnabled(false);
            }
        });
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
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 200);
                    }
                });
                break;
        }
    }

    @Override
    public void OnEorr() {

    }

}