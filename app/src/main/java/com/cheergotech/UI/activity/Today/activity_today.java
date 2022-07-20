package com.cheergotech.UI.activity.Today;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Today.fragfment.page;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.model.studentunrcom;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.Backtitle;
import com.cheergotech.widget.widgetimage13;
import com.cheergotech.widget.widgetimage16;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 今日评语-发布评语
 */
public class activity_today extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.ck2)
    CheckBox ck2;
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.t3)
    widgetimage16 t3;
    @BindView(R.id.t4)
    widgetimage16 t4;
    @BindView(R.id.widgetimage13)
    widgetimage13 widgetimage13;
    //0：全部 1：已表扬 2:待改进 3：未评
    private page page0;
    private page page1;
    private page page2;
    private page page3;
    private int itemtype;
    private page page1111;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_today.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_today;
    }

    @Override
    public void iniview() {
        //获取当天时间
        //backtitle.setconter(Config.stampToDatehmm(String.valueOf(System.currentTimeMillis())));
        backtitle.setCallBcak(new CallBcak() {
            @Override
            public void setOnboolean(boolean b) {
                Toasts.show("" + b);
            }
        });
        ck2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                page1111 = (com.cheergotech.UI.activity.Today.fragfment.page) fragments.get(itemtype);
                page1111.setIstrue(b);
            }
        });
        initData();

    }

    @Override
    public void initData() {
        if (fragments.size() == 0) {
            //类型 0：全部 1：已表扬 2:待改进 3：未评
            fragments.add(page0 = new page(0, t2, t3, t4));
            fragments.add(page1 = new page(3, t2, t3, t4));
            fragments.add(page2 = new page(1, t2, t3, t4));
            fragments.add(page3 = new page(2, t2, t3, t4));
            page1111=page0;
        }
        viewPager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                itemtype = position;
                page1111 = (com.cheergotech.UI.activity.Today.fragfment.page) fragments.get(position);
                ck2.setChecked(page1111.isIstrue());

                t1.setBooleanbg(false);
                t2.setBooleanbg(false);
                t3.setBooleanbg(false);
                t4.setBooleanbg(false);
                switch (position) {
                    case 0:
                        t1.setBooleanbg(true);
                        break;
                    case 1:
                        t2.setBooleanbg(true);
                        break;
                    case 2:
                        t3.setBooleanbg(true);
                        break;
                    case 3:
                        t4.setBooleanbg(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(4);
        widgetimage13.setCallback(new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int type) {
                switch (type) {
                    case 1:
                        itemtype = 0;
                        viewPager.setCurrentItem(0);
                        break;
                    case 2:
                        itemtype = 1;
                        viewPager.setCurrentItem(1);
                        break;
                    case 3:
                        itemtype = 2;
                        viewPager.setCurrentItem(2);
                        break;
                    case 4:
                        itemtype = 3;
                        viewPager.setCurrentItem(3);
                        break;
                }
                page1111 = (com.cheergotech.UI.activity.Today.fragfment.page) fragments.get(itemtype);
            }
        });

        t2.show(0, 1, 0);
        t3.show(0, 1, 0);
        t4.show(0, 1, 0);
    }

    @Override
    @OnClick({R.id.ck, R.id.ck2, R.id.ck3, R.id.send1, R.id.t1, R.id.t2, R.id.t3, R.id.t4})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                if (page1111 == null) {
                    return;
                }
                List<Object> objects = new ArrayList<>();
                for (Object o : page1111.list) {
                    studentunrcom studentunrcom = (com.cheergotech.UI.model.studentunrcom) o;
                    if (studentunrcom.isIsboolean()) {
                        if (studentunrcom.getStatus() == 3) {
                            objects.add(studentunrcom);
                        }
                    }
                }
                if (objects.size() == 0) {
                    Toasts.toastMessage(getString(R.string.toast3));
                    return;
                }
                activity_pingyu.setAction(activity, gson.toJson(objects));
                break;
            case R.id.ck:
            case R.id.ck2:
                break;
            case R.id.t1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.t2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.t3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.t4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.ck3:
                ck2.setChecked(ck2.isChecked() ? true : false);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.sussess && resultCode == Config.setResult) {
            page0.OnLoad(1);
            page1.OnLoad(1);
            page2.OnLoad(1);
            page3.OnLoad(1);
        }
    }
}