package com.cheergotech.UI.activity.leave;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.holder.holder4;
import com.cheergotech.UI.activity.viecode.activity_picbage;
import com.cheergotech.UI.dialog.DialogMessage12;
import com.cheergotech.UI.dialog.DialogMessage6;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.UI.model.volist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Logi;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.widgetimage4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 请假审批请假信息-详情
 */
public class activity_Leave2 extends BaseActivity {
    String TAG = activity_Leave2.class.getSimpleName();
    volist volist;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.studentName)
    TextView studentName;
    @BindView(R.id.describes)
    TextView describes;
    @BindView(R.id.days)
    widgetimage4 days;
    @BindView(R.id.startTime)
    widgetimage4 startTime;
    @BindView(R.id.overTime)
    widgetimage4 overTime;
    @BindView(R.id.titles)
    widgetimage4 titles;
    @BindView(R.id.status1)
    TextView status1;
    @BindView(R.id.status2)
    TextView status2;
    @BindView(R.id.className)
    TextView className;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.booter)
    LinearLayout booter;
    @BindView(R.id.accelerate)
    widgetimage4 accelerate;
    @BindView(R.id.statusname)
    TextView statusname;
    @BindView(R.id.imgageliinear)
    LinearLayout imgageliinear;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Leave2.class);
        context.startActivity(intent);
    }

    public static void setAction(Activity context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Leave2.class);
        intent.putExtra(Constants.id, id);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_leave2;
    }

    @Override
    public void iniview() {
        int id = getIntent().getIntExtra(Constants.id, 0);
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_Img, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                //图片预览
                List<imglist> mSelectedImages = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    imglist image = new imglist();
                    String path = (String) list.get(i);
                    image.setId(String.valueOf(i));
                    image.setBgpic(path);
                    image.setPic(path);
                    mSelectedImages.add(image);
                }
                activity_picbage.setAction(context, mSelectedImages, position);
            }
        }));
        Datamodule.getInstance().leaveinfo(id, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                volist = (com.cheergotech.UI.model.volist) object;
                if (TextUtils.isEmpty(volist.getHeadImg())) {
                    Glide.with(context).load(R.mipmap.avasss).into(headImg);
                } else {
                    Glide.with(context).load(volist.getHeadImg()).into(headImg);
                }
                studentName.setText(volist.getStudentName());
                describes.setText(volist.getDescribes());
                className.setText(volist.getClassName());
                days.setMsg(volist.getDays() + getString(R.string.tt));
                startTime.setMsg(String.valueOf(Config.stampToDate(String.valueOf(volist.getStartTime()), null)));
                overTime.setMsg(String.valueOf(Config.stampToDate(String.valueOf(volist.getOverTime()), null)));
                titles.setMsg(volist.getTitles());
                switch (volist.getStatus()) {
                    case 0:
                        status.setText(R.string.js1);
                        status.setVisibility(View.GONE);
                        booter.setVisibility(View.VISIBLE);
                        accelerate.setVisibility(View.GONE);

                        break;
                    case 1:
                        status.setVisibility(View.VISIBLE);
                        status.setText(R.string.leave2_top);
                        booter.setVisibility(View.GONE);
                        accelerate.setVisibility(View.GONE);
                        break;
                    case 2:
                        if (TextUtils.isEmpty(volist.getConversation())) {
                            status.setText(R.string.js2);
                        } else {
                            status.setText(volist.getConversation());
                        }
                        statusname.setText(R.string.toast13);
                        statusname.setVisibility(View.VISIBLE);
                        status.setVisibility(View.VISIBLE);
                        booter.setVisibility(View.GONE);
                        accelerate.setVisibility(View.VISIBLE);
                        break;


                }

                //图片附件
                String detailsImg = volist.getDetailsImg();


                Logi.d(TAG, "OnSuccess: " + detailsImg);
                if (!TextUtils.isEmpty(detailsImg)) {
                    List<String> getkcirclelist = holder4.getkcirclelist(detailsImg.trim());
                    if (getkcirclelist.size() > 0) {
                        list.clear();
                        list.addAll(getkcirclelist);
                        imgageliinear.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void Onfall() {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.status1, R.id.status2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.status1:
                DialogMessage12.show(context, new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int type) {
                        switch (type) {
                            case 1:
                                //放弃
                                //Toasts.toastMessage("放弃");
                                break;
                            case 2:
                                //请假列表-查看已通过的审批
                                myagree();
                                break;
                        }
                    }
                });
                break;
            case R.id.status2:
                DialogMessage6.show(context, getString(R.string.dialog_msg1), "", "", new CallBcak() {
                    @Override
                    public void setOnClickListener(int position) {

                    }

                    @Override
                    public void setMtext(String msg) {
                        if (TextUtils.isEmpty(msg)) {
                            Toasts.toastMessage(getString(R.string.js4));
                            return;
                        }
                        //添加驳回原因
                        Datamodule.getInstance().refuse(volist.getId(), msg, new CallBcak() {
                            @Override
                            public void OnSuccess() {
                                Toasts.toastMessage(getString(R.string.js5));
                                setResult(Config.setResult);
                                iniview();
                            }

                            @Override
                            public void Onfall() {

                            }
                        });
                    }
                });
                break;
        }

    }

    @Override
    public void OnEorr() {

    }


    /**
     * 请假信息-待审批-同意
     */
    private void myagree() {
        Datamodule.getInstance().agree(volist.getId(), new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void OnSuccess() {
                Toasts.toastMessage(getString(R.string.js3));
                iniview();
                setResult(Config.setResult);
            }
        });

    }

}