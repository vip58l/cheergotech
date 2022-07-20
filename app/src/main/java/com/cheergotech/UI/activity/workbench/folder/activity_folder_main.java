package com.cheergotech.UI.activity.workbench.folder;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.activity.workbench.folder.fragment.fragment_folder;
import com.cheergotech.UI.dialog.DialogMessage16;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.widget.Backtitle;
import com.cheergotech.widget.widgetimage16;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件夹
 */
public class activity_folder_main extends BaseActivity {
    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_folder_main.class);
        context.startActivity(intent);
    }

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.backtitle)
    Backtitle backtitle;

    @Override
    protected int getview() {
        return R.layout.activity_folder_main;
    }

    @Override
    public void iniview() {
        backtitle.findViewById(R.id.line1).setVisibility(View.VISIBLE);
        backtitle.findViewById(R.id.imgrhig).setVisibility(View.GONE);

        if (fragments.size() == 0) {
            fragments.add(new fragment_folder(1));
            fragments.add(new fragment_folder(2));
        }
        viewpager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                t1.setBooleanbg(false);
                t2.setBooleanbg(false);
                switch (position) {
                    case 0:
                        t1.setBooleanbg(true);
                        break;
                    case 1:
                        t2.setBooleanbg(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.myonclick})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.t1:
                viewpager.setCurrentItem(0);
                break;
            case R.id.t2:
                viewpager.setCurrentItem(1);
                break;
            case R.id.myonclick:
                //排序
                DialogMessage16.show(context, new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int position) {

                        switch (position) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void OnEorr() {

    }


}