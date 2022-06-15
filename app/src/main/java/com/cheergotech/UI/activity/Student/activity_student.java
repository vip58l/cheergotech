package com.cheergotech.UI.activity.Student;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Student.fragment.page2;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.widgetimage16;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学生健康
 */
public class activity_student extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    viepagePagerAdapter adapter;
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.t3)
    widgetimage16 t3;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_student.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_student;
    }

    @Override
    public void iniview() {
        //0全部 1：正常 2：异常 3:未采集
        fragments.add(new page2(0, t1));
        fragments.add(new page2(2, t2));
        fragments.add(new page2(3, t3));
        viewpager.setAdapter(adapter = new viepagePagerAdapter(getSupportFragmentManager(), fragments, viepagePagerAdapter.activity_student));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                t1.setBooleanbg(false);
                t2.setBooleanbg(false);
                t3.setBooleanbg(false);
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
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setOffscreenPageLimit(4);

        t2.show(0, 1);
        t3.show(0, 0);
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.t3})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.t1:
                viewpager.setCurrentItem(0);
                break;
            case R.id.t2:
                viewpager.setCurrentItem(1);
                break;
            case R.id.t3:
                viewpager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }
}