package com.cheergotech.UI.activity.leave;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.NaviSetting;
import com.bumptech.glide.Glide;
import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage2;
import com.cheergotech.UI.dialog.DialogMessage3;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.absence;
import com.cheergotech.UI.model.device;
import com.cheergotech.UI.model.messageSendDTO;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Logi;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 使用到导航 查看位置
 */
public class activity_location extends BaseActivity {
    String TAG = activity_location.class.getName();
    AMapNavi mAMapNavi;

    MapView mMapView;
    AMap aMap;
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.name2)
    TextView name2;
    @BindView(R.id.address2)
    TextView address2;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.mContainerLayout)
    LinearLayout mContainerLayout;
    device devices;
    absence absence;
    public final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_location.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_location.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_gps1;
    }

    @Override
    public void iniview() {

        String json = getIntent().getStringExtra(Constants.JSON);
        absence = gson.fromJson(json, absence.class);
        backtitle.setconter(absence.getStudentName());
        name.setText(absence.getStudentName());
        name2.setText(absence.getStudentName());
        try {
            NaviSetting.updatePrivacyShow(context, true, true);
            NaviSetting.updatePrivacyAgree(context, true);
            //获取地图控件引用
            mMapView = findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            aMap = mMapView.getMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(absence.getHeadImg())) {
            Glide.with(context).load(absence.getHeadImg()).into(avatar);
        } else {
            Glide.with(context).load(R.mipmap.avasss).into(avatar);
        }
        if (TextUtils.isEmpty(absence.getDeviceId())) {
            address.setText(R.string.ls1);
            address2.setText(R.string.ls1);
            Toasts.toastMessage(getString(R.string.toast10));
            //weizhishow();
        }

        initData();
    }

    @Override
    public void initData() {
        //APP-实时获取孩子最新位置
        Datamodule.getInstance().gpsappstu(absence.getDeviceId(), new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                devices = (device) object;
                address.setText(devices.getDetailsInfo());
                address2.setText(devices.getDetailsInfo());
                if (!TextUtils.isEmpty(devices.getDeviceId())) {
                    addmarkerOption(new LatLng(Double.parseDouble(devices.getLatitude()), Double.parseDouble(devices.getLongitude())), devices);
                }
            }

            @Override
            public void Onfall() {
                Logi.d(TAG, "Onfall: ");
            }
        });

    }


    @OnClick({R.id.send1, R.id.phone})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1: {
                //消息通知
                Log.d(TAG, "OnClick: " + absence.toString());

                DialogMessage2.show(context, new CallBcak() {
                    @Override
                    public void setOnClickListener(Object obj) {
                        messageSendDTO message = new messageSendDTO();
                        message.setStudentId(absence.getId());
                        message.setContent(obj.toString());
                        Datamodule.getInstance().sendmessage(message, this);

                    }

                    @Override
                    public void OnSuccess() {
                        Toasts.success();

                    }

                    @Override
                    public void Onfall() {
                        Toasts.showeorr();


                    }
                });
                break;
            }
            case R.id.phone: {
                //打电话给家长
                DialogMessage3.show(context, absence.getStudentName(), new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int type) {
                        switch (type) {
                            case 1:
                                break;
                            case 2:
                                startCallPhone(absence.getFamilyPhone());
                                break;
                        }

                    }


                });
                break;
            }

        }
    }

    @Override
    public void OnEorr() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出导航组件
        AmapNaviPage.getInstance().exitRouteActivity();
    }


    /**
     * 调用拨号功能
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        startActivity(intent);

    }

    /**
     * 打电话
     *
     * @param phone
     */
    protected void startCallPhone(String phone) {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                return;
            } else {
                callPhone(phone);
            }
        } else {
            callPhone(phone);

            // 检查是否获得了权限（Android6.0运行时权限）
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // 没有获得授权，申请授权
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) activity, Manifest.permission.CALL_PHONE)) {
                    // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                    // 弹窗需要解释为何需要该权限，再次请求授权
                    Toasts.toastMessage(getString(R.string.toast1));

                    // 帮跳转到该应用的设置界面，让用户手动授权
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                } else {
                    // 不需要解释为何需要该权限，直接请求授权
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }
            } else {
                // 已经获得授权，可以打电话
                callPhone(phone);
            }
        }

    }

    private void callPhone(String phone) {
        // 拨号：激活系统的拨号组件 -- 直接拨打电话
        //Intent intent = new Intent(); // 意图对象：动作 + 数据
        //intent.setAction(Intent.ACTION_CALL); // 设置动作
        //Uri data = Uri.parse("tel:" + phoneNumber); // 设置数据
        //intent.setData(data);
        //startActivity(intent); // 激活Activity组件
        //打开拨号界面，填充输入手机号码，让用户自主的选择
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    callPhone(absence.getFamilyPhone());
                } else {
                    // 授权失败！
                    Toasts.toastMessage(getString(R.string.toasteroot));
                }
                break;
            }
        }

    }


    /**
     * 学生位置
     */
    private void weizhishow() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.showMyLocation(false); //显示定位蓝点
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gps11)));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.strokeColor(Color.TRANSPARENT);
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);

        //高德地图设置比例尺比例以及地图中心点
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 16));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(false); // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {


            }
        });
        aMap.getUiSettings().setZoomControlsEnabled(true); //缩放按钮
        aMap.getUiSettings().setCompassEnabled(false);      //指南针
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setScaleControlsEnabled(true);   //显示默认的定位按钮
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Logi.d(TAG, "onMapClick: " + latLng.toString());


            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Logi.d(TAG, "onMarkerClick: " + marker.getPosition());
                Logi.d(TAG, "onMarkerClick: " + marker.getTitle());
                //隐藏和显示标题和描述
                //marker.showInfoWindow();
                //marker.hideInfoWindow();
                return true;
            }
        });
    }

    /**
     * 绘制点标记
     */
    private void addmarkerOption(LatLng latLng, device devices) {

        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title(absence.getStudentName()).snippet(devices.getDetailsInfo());
        markerOption.draggable(false);//设置Marker可拖动

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), absence.getSex() == 1 ? R.mipmap.gps11 : R.mipmap.gps2));
        markerOption.icon(bitmapDescriptor);

        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

        //绘制动画效果 Marker
        //Animation animation = new RotateAnimation(marker.getRotateAngle(), marker.getRotateAngle() + 360, 0, 0, 0);
        //animation.setDuration(10000L);
        //animation.setInterpolator(new LinearInterpolator());
        //marker.setAnimation(animation);
        //marker.startAnimation();

        // 绘制曲线
        //aMap.addPolyline((new PolylineOptions()).add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55)).geodesic(true).color(Color.RED));


    }


}