package com.cheergotech.UI.activity.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Course.activity_course;
import com.cheergotech.UI.activity.Student.activity_student;
import com.cheergotech.UI.activity.Term.activity_Term;
import com.cheergotech.UI.activity.Today.activity_today;
import com.cheergotech.UI.activity.baijichu.activity_bjchu;
import com.cheergotech.UI.activity.buslocation.activity_all_bus_location;
import com.cheergotech.UI.activity.joblist.activity_Joblist;
import com.cheergotech.UI.activity.leave.activity_absentees;
import com.cheergotech.UI.activity.leave.activity_number;
import com.cheergotech.UI.activity.toschool.activity_toschool;

import butterknife.OnClick;

/**
 * AndroidStudio继承父类、实现接口无法提示重写方法，无法实时检测代码出错的解决方案
 * https://blog.csdn.net/qq_41904106/article/details/115071279
 */
public class MainActivity1 extends BaseActivity {

    private static final String TAG = MainActivity2.class.getName();

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_main;
    }

    @Override
    public void iniview() {
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4, R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.imgage, R.id.imgagemap})
    public void OnClick(View v) {
        switch (v.getId()) {

            case R.id.frame1: {
                //到校人数
                activity_toschool.setAction(activity);
                break;
            }

            case R.id.frame2: {
                //学生健康
                activity_student.setAction(activity);
                break;
            }

            case R.id.frame3: {
                //缺勤人数
                activity_absentees.setAction(activity);

                break;
            }

            case R.id.frame4: {
                //请假人数
                activity_number.setAction(activity);
                break;
            }

            case R.id.line1: {
                //课程信息
                activity_course.setAction(activity);
                //activity_timetable.setAction(context);
                break;
            }

            case R.id.line2: {
                //学期评语
                activity_Term.setAction(activity);
//                activity_pingyu.setAction(context);
                break;
            }

            case R.id.line3: {
                //作业
                activity_Joblist.setAction(activity);

                //acetivity_back.setAction(context);
                //activity_alljob.setAction(context);
                //activity_job_correct.setAction(context);
                break;
            }

            case R.id.line4: {
                //班级圈
                activity_bjchu.setAction(activity);
                break;
            }

            case R.id.imgage: {
                //今天评语
                activity_today.setAction(activity);
                break;
            }

            case R.id.imgagemap: {
                //校车位置
                activity_all_bus_location.setAction(activity);
                break;
            }
        }

    }

    @Override
    public void OnEorr() {

    }


}