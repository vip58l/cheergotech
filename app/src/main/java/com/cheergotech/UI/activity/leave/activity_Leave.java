package com.cheergotech.UI.activity.leave;

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
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.UI.model.volist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.widget.widgetimage4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 请假详情
 */
public class activity_Leave extends BaseActivity {
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
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.className)
    TextView className;
    @BindView(R.id.imgageliinear)
    LinearLayout imgageliinear;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Leave.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Leave.class);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_leave;
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
                switch (volist.getStatus()) {
                    case 0:
                        status.setText(R.string.q1);
                        break;
                    case 1:
                        status.setText(R.string.q2);
                        break;
                    case 2:
                        status.setText(R.string.q3);
                        break;
                }
                days.setMsg(volist.getDays() + getString(R.string.tt));
                startTime.setMsg(String.valueOf(Config.stampToDate(String.valueOf(volist.getStartTime()), null)));
                overTime.setMsg(String.valueOf(Config.stampToDate(String.valueOf(volist.getOverTime()), null)));
                titles.setMsg(volist.getTitles());

                //图片附件
                String detailsImg = volist.getDetailsImg();
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
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

    }

}