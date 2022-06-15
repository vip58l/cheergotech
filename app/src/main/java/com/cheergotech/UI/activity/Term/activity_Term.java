package com.cheergotech.UI.activity.Term;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Term.fragment.pageterm;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.PageInfo;
import com.cheergotech.UI.model.studentunReviewNumber;
import com.cheergotech.UI.model.studentunrcom;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.Backtitle;
import com.cheergotech.widget.widgetimage16;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学期评语
 */
public class activity_Term extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.t3)
    widgetimage16 t3;

    @BindView(R.id.checkbox)
    CheckBox checkbox;
    private pageterm p1;
    private pageterm p2;
    private pageterm p3;
    private pageterm p4;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Term.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_term;
    }

    @Override
    public void iniview() {

        if (fragments.size() == 0) {
            //类型 0：全部 1：已评 2:未评
            fragments.add(p1 = new pageterm(0, t2, t3));
            fragments.add(p2 = new pageterm(1, t2, t3));
            fragments.add(p3 = new pageterm(2, t2, t3));
            p4 = p1;
        }
        viewpager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int OffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                p4 = (pageterm) fragments.get(position);
                checkbox.setChecked(p4.isIstrue());

                t1.setBooleanbg(false);
                t2.setBooleanbg(false);
                t3.setBooleanbg(false);
                switch (position) {
                    case 0:
                        t1.setBooleanbg(true);
                        break;
                    case 1:
                        t2.setBooleanbg(true);

                            t2.show(p4.stu.getReviewNumber(), 1, 0);


                        break;
                    case 2:
                        t3.setBooleanbg(true);
                        t3.show(p4.stu.getUnReviewNumber(), 0, 0);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setOffscreenPageLimit(3);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (p4 != null) {
                    p4.setIstrue(b);
                }

            }
        });
    }

    public void OnLoad(int type) {
        if (type == 1) {
            totalPage = 0;
            list.clear();
            adapter.notifyDataSetChanged();
        } else {
            totalPage++;
        }

        initData();
    }

    @Override
    public void initData() {
        totalPage++;
        // 0：全部 1：已评 2:未评
        Datamodule.getInstance().semester(msgconfig.getClassId(), totalPage, 20, 0, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                studentunReviewNumber st = (studentunReviewNumber) object;
                PageInfo stPageInfo = (PageInfo) st.getPageInfo();
                List<studentunrcom> studentunrcom = (List<com.cheergotech.UI.model.studentunrcom>) stPageInfo.getList();
                list.addAll(studentunrcom);
                adapter.notifyDataSetChanged();
                if (studentunrcom.size() == 0 && totalPage > 1) {
                    totalPage--;
                    Toasts.toastMessage(getString(R.string.err));
                }
                OnEorr();


            }

            @Override
            public void Onfall() {

            }
        });
    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.t3, R.id.send1})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                List<Object> objects = new ArrayList<>();
                for (Object o : p4.list) {
                    studentunrcom studentunrcom = (com.cheergotech.UI.model.studentunrcom) o;
                    if (studentunrcom.isIsboolean()) {
                        if (studentunrcom.getStatus() == 2) {
                            objects.add(studentunrcom);
                        }
                    }
                }
                if (objects.size() == 0) {
                    Toasts.toastMessage(getString(R.string.toast4));
                    return;
                }
                activity_termo.setAction(activity, gson.toJson(objects));
                break;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.sussess && resultCode == Config.setResult) {
            p1.OnLoad(1);
            p2.OnLoad(1);
            p3.OnLoad(1);
        }


    }
}