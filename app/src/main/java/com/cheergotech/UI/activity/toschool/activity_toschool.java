package com.cheergotech.UI.activity.toschool;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.activity.toschool.fragment.page1;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.widgetimage16;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 到校人数
 */
public class activity_toschool extends BaseActivity {
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.t3)
    widgetimage16 t3;
    @BindView(R.id.t4)
    TextView t4;
    @BindView(R.id.t5)
    TextView t5;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_toschool.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_toschool;
    }

    @Override
    public void iniview() {
        // 0：全部 2：正常 4：迟到
        if (fragments.size() == 0) {
            fragments.add(new page1(0, t4, t5));
            fragments.add(new page1(4, t4, t5));
        }
        viewPager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                paycolor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.t3})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.t1:
                paycolor(0);
                break;
            case R.id.t2:
                // paycolor(1);
                break;
            case R.id.t3:
                paycolor(1);
                break;
        }

    }

    @Override
    public void OnEorr() {

    }


    private void paycolor(int type) {
        {
            viewPager.setCurrentItem(type);
            t1.setBooleanbg(false);
            t2.setBooleanbg(false);
            t3.setBooleanbg(false);
        }
        switch (type) {

            case 0: {
                page1 page1 = (com.cheergotech.UI.activity.toschool.fragment.page1) fragments.get(0);
                t4.setTextColor(getResources().getColor(R.color.shapa));
                t4.setText(String.valueOf(page1.normalresult.getNormalNum()));
                t1.setBooleanbg(true);
                break;
            }

            case 1: {
                page1 page1 = (com.cheergotech.UI.activity.toschool.fragment.page1) fragments.get(1);
                t4.setTextColor(getResources().getColor(R.color.shape));
                t4.setText(String.valueOf(page1.normalresult.getAbnormalNum()));
                t3.setBooleanbg(true);
                break;
            }
        }
    }
}