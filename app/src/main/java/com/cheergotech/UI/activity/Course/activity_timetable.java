package com.cheergotech.UI.activity.Course;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.amCourse;
import com.cheergotech.UI.model.courseinfo;
import com.cheergotech.UI.model.infoweek;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Logi;

import java.util.List;

/**
 * 课程信息-总
 */
public class activity_timetable extends BaseActivity {
    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_timetable.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_timetable;
    }

    @Override
    public void iniview() {

        Datamodule.getInstance().courseinfo(msgconfig.getClassId(), new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                List<courseinfo> courseinfos = (List<courseinfo>) object;
                for (courseinfo courseinfo : courseinfos) {
                    int week = courseinfo.getWeek();
                    infoweek infoweek = (com.cheergotech.UI.model.infoweek) courseinfo.getCourseInfo();
                    List<amCourse> amCourseList1 = infoweek.getAmCourse();
                    List<amCourse> amCourseList2 = infoweek.getPmCourse();

                    Logi.d("TAG", "week: "+week);
                    Logi.d("TAG", "amCourseList1: "+amCourseList1.size());
                    Logi.d("TAG", "amCourseList2: "+amCourseList2.size());
                }

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