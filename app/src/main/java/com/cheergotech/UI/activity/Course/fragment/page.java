package com.cheergotech.UI.activity.Course.fragment;

import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.amCourse;
import com.cheergotech.UI.model.infoweek;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.widget.widgetimage9;

import java.util.List;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/209:48
 * Description
 */
public class page extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.recyclerview2)
    RecyclerView recyclerview2;
    @BindView(R.id.eorr)
    widgetimage9 eorr;
    @BindView(R.id.neverCompleteToEnd)
    NestedScrollView neverCompleteToEnd;

    public page(int type) {
        this.type = type;
    }

    @Override
    protected int getview() {
        return R.layout.main_page_mxl;
    }

    @Override
    public void iniview() {

        //上午课程
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_baiji_text, null));
        //下午课程
        recyclerview2.setLayoutManager(new LinearLayoutManager(context));
        recyclerview2.setAdapter(adapter2 = new Adapter(context, list2, Adapter.bus_baiji_text, null));

        initData();
    }

    @Override
    public void initData() {
        Datamodule.getInstance().courseinfoweek(msgconfig.getClassId(), type, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {

                infoweek infoweek = (com.cheergotech.UI.model.infoweek) object;
                //上午课程
                List<amCourse> amCourseList = infoweek.getAmCourse();
                if (amCourseList.size()>0){
                    list.clear();
                }
                list.addAll(amCourseList);
                adapter.notifyDataSetChanged();

                //下午课程
                List<amCourse> pmCourseList = infoweek.getPmCourse();
                if (pmCourseList.size()>0){
                    list2.clear();
                }
                list2.addAll(pmCourseList);
                adapter2.notifyDataSetChanged();

                OnEorr();


            }
        });

    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {
        if (list.size() == 0 && list2.size() == 0) {
            eorr.setVisibility(View.VISIBLE);
            neverCompleteToEnd.setVisibility(View.GONE);
        }
    }
}
