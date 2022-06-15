package com.cheergotech.UI.activity.main.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.navi.NaviSetting;
import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Course.activity_course;
import com.cheergotech.UI.activity.Student.activity_student;
import com.cheergotech.UI.activity.Term.activity_Term;
import com.cheergotech.UI.activity.Today.activity_today;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.baijichu.activity_bjchu;
import com.cheergotech.UI.activity.buslocation.activity_all_bus_location;
import com.cheergotech.UI.activity.buslocation.activity_bus_location;
import com.cheergotech.UI.activity.joblist.activity_Joblist;
import com.cheergotech.UI.activity.leave.activity_Leave_approval;
import com.cheergotech.UI.activity.leave.activity_absentees;
import com.cheergotech.UI.activity.toschool.activity_toschool;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.absinit;
import com.cheergotech.UI.model.classlistter;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.ActivityLocation;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.glide.ImageLoadHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1515:30
 * Description 首页
 */
public class page1 extends BaseFragment {
    private static final String TAG = page1.class.getName();
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.boot)
    ImageView boot;
    @BindView(R.id.xiexiao)
    TextView xiexiao;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.grade)
    TextView grade;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.absenceNumber)
    TextView absenceNumber;
    @BindView(R.id.normalNumber)
    TextView normalNumber;
    @BindView(R.id.leaveNumber)
    TextView leaveNumber;
    @BindView(R.id.r1)
    RecyclerView recyclerview;
    @BindView(R.id.timetitle)
    TextView timetitle;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.amapLayout)
    RelativeLayout amapLayout;
    @BindView(R.id.share)
    public TextView share;
    MapView mMapView;
    AMap aMap;

    @Override
    protected int getview() {
        return R.layout.activity_main;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (share != null) {
            share.animate().alpha(menuVisible ? 1f : 0f).setDuration(200).start();
        }
    }

    @Override
    public void iniview() {
        xiexiao.setText(userinfo.getSchoolName());
        name.setText(userinfo.getRealName());
        timetitle.setText(Config.stampToDatehmmdd(String.valueOf(System.currentTimeMillis())));
        ImageLoadHelper.glideShowImageWithUrl(context, userinfo.getHeadImg(), image);
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                recyclerview.setVisibility(View.GONE);
                return false;
            }
        });
        timetitle.setOnClickListener(view -> getinitData());
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.page_list, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                //切换班级保存本地
                classlistter classlistter = (com.cheergotech.UI.model.classlistter) list.get(position);
                grade.setText(classlistter.getNames());
                msgconfig.setClassId(classlistter.getId());
                msgconfig.setNames(classlistter.getNames());
                boot.setRight(180);
                recyclerview.setVisibility(View.GONE);
                absenceNumber.setVisibility(View.GONE);
                leaveNumber.setVisibility(View.GONE);
                normalNumber.setVisibility(View.GONE);
                initcount();
            }
        }));

        //获取相关数据
        getinitData();
        //设置权限名称
        if (!TextUtils.isEmpty(Msgconfig.getInstance().getRoleName())) {
            msg.setText(Msgconfig.getInstance().getRoleName());
        }

        //申请地址定位权限
        if (ActivityLocation.checkpermissions(context, this)) {
            initData();
        }
    }

    @Override
    public void initData() {
        NaviSetting.updatePrivacyShow(context, true, true);
        NaviSetting.updatePrivacyAgree(context, true);
        mMapView = info.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();

        //高德地图设置比例尺比例以及地图中心点
        //aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0,0),16));

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gps1)));
        myLocationStyle.strokeColor(Color.TRANSPARENT);
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);
        //连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);

        aMap.getUiSettings().setZoomControlsEnabled(false); //缩放按钮
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false); //设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //高德地图设置比例尺比例以及地图中心点
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));
            }
        });
    }

    @Override
    public void OnEorr() {

    }

    @Override
    @OnClick({R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4, R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.imgage, R.id.imgagemap, R.id.refresh, R.id.grade, R.id.boot})
    public void OnClick(View v) {
        if (msgconfig.getClassId() == 0) {
            Toasts.toastMessage(getString(R.string.toast5));
            return;
        }
        if (list.size() == 0) {
            Toasts.toastMessage(getString(R.string.toast5));
            return;
        }

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
                //activity_number.setAction(context);

                //请假列表(new)
                activity_Leave_approval.setAction(activity);
                break;
            }

            case R.id.line1: {
                //课程信息
                activity_course.setAction(activity);

                //课程表
                //activity_timetable.setAction(context);
                break;
            }

            case R.id.line2: {
                //学期评语
                activity_Term.setAction(activity);
//                activity_pingyu.setAction(context);
                break;
            }

            case R.id.imgage: {
                //今天评语
                activity_today.setAction(activity);
                break;
            }

            case R.id.line3: {
                //作业信息-列表
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

            case R.id.imgagemap: {
                //校车位置 地图放大
                activity_all_bus_location.setAction(activity);
                //activity_bus_location.setAction(context);
                break;
            }

            case R.id.refresh: {
                Toasts.toastMessage(getString(R.string.location));
                activity_bus_location.setAction(activity);
                break;
            }

            case R.id.grade:
            case R.id.boot: {
                //选择班级切换
                if (list.size() == 0) {
                    Toasts.toastMessage(getString(R.string.toast5));
                    getinitData();
                    return;
                }
                recyclerview.setVisibility(recyclerview.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ActivityLocation.OPEN_SET_REQUEST_CODE:
                for (String s : permissions) {
                    int checkSelfPermission = ContextCompat.checkSelfPermission(activity, s);
                    if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "定位授权失败");
                        break;
                    } else {
                        Log.d(TAG, "定位授权成功");
                        initData();
                    }

                }
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (mMapView != null)
            mMapView.onSaveInstanceState(outState);
    }

    /**
     * 教师-获取班级信息
     */
    public void getinitData() {
        Datamodule.getInstance().classlist(new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                List<classlistter> classlistters = (List<classlistter>) object;
                if (classlistters.size() > 0) {
                    classlistter classlistter = classlistters.get(0);
                    if (TextUtils.isEmpty(msgconfig.getNames())) {
                        grade.setText(classlistter.getNames());
                        msgconfig.setClassId(classlistter.getId());
                        msgconfig.setNames(classlistter.getNames());
                    } else {
                        grade.setText(msgconfig.getNames());
                    }
                    list.clear();
                    list.addAll(classlistters);
                    adapter.notifyDataSetChanged();
                    initcount();
                }

            }

            @Override
            public void Onfall() {

            }
        });
    }

    /**
     * 首页考勤初始化
     */
    public void initcount() {
        Datamodule.getInstance().init(Msgconfig.getInstance().getClassId(), new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void OnSuccess(Object object) {
                absinit absinit = (com.cheergotech.UI.model.absinit) object;
                if (absinit != null) {
                    //缺勤人数
                    if (absinit.getAbsenceNumber() > 0) {
                        absenceNumber.setText(String.valueOf(absinit.getAbsenceNumber()));
                        absenceNumber.setVisibility(View.VISIBLE);
                    } else {
                        absenceNumber.setVisibility(View.GONE);
                    }

                    //请假人数
                    if (absinit.getLeaveNumber() > 0) {
                        leaveNumber.setText(String.valueOf(absinit.getLeaveNumber()));
                        leaveNumber.setVisibility(View.VISIBLE);
                    } else {
                        leaveNumber.setVisibility(View.GONE);
                    }

                    //到校人数
                    if (absinit.getNormalNumber() > 0) {
                        normalNumber.setText(String.valueOf(absinit.getNormalNumber()));
                        normalNumber.setVisibility(View.VISIBLE);
                    } else {
                        normalNumber.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initcount();
    }

}
