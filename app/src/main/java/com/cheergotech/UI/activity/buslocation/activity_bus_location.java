package com.cheergotech.UI.activity.buslocation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.LinesInfobus;
import com.cheergotech.UI.model.schoolbust;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.JsonUitl;
import com.cheergotech.ulist.Logi;

import java.util.List;

/**
 * 刷新校车位置
 */
public class activity_bus_location extends BaseActivity {
    private static final String TAG = activity_bus_location.class.getName();

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_bus_location.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    MapView mMapView;
    AMap aMap;
    Polyline polyline;

    @Override
    protected int getview() {
        return R.layout.activity_bus_location;
    }

    @Override
    public void iniview() {
        MapsInitializer.updatePrivacyShow(context, true, true);
        MapsInitializer.updatePrivacyAgree(context, true);

        //获取地图控件引用
        mMapView = findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();

        //显示当前定位
        if (true) {
            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(10000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            myLocationStyle.showMyLocation(true); //显示定位蓝点
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gps1)));
            //连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
            myLocationStyle.strokeColor(Color.TRANSPARENT);
            myLocationStyle.radiusFillColor(Color.TRANSPARENT);
            //高德地图设置比例尺比例以及地图中心点
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 16));

            //地图view中心点和缩放级别设置
            //aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
            //aMap.moveCamera(CameraUpdateFactory.zoomTo(aMap.getMaxZoomLevel()-3));

            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
            aMap.setMyLocationEnabled(true); // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
//                    LinesInfobus linesInfobus = new LinesInfobus();
//                    linesInfobus.setLatitude(location.getLatitude());
//                    linesInfobus.setLongitude(location.getLongitude());
//                    addmarkerOption(linesInfobus);

                    if (list.size() == 0) {
                        getschoolbus(location.getLatitude(), location.getLongitude());
                    }
                }
            });
            aMap.getUiSettings().setZoomControlsEnabled(true); //缩放按钮
            aMap.getUiSettings().setCompassEnabled(false);      //指南针
            aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
            aMap.getUiSettings().setScaleControlsEnabled(false);   //显示默认的定位按钮
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
                    //隐藏和显示标题和描述
                    //marker.showInfoWindow();
                    //marker.hideInfoWindow();
                    return true;
                }
            });
            aMap.getUiSettings().setRotateGesturesEnabled(false);//禁止地图旋转手势.
            aMap.getUiSettings().setTiltGesturesEnabled(false);//禁止倾斜手势.


        }

        // setMarker();

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (mMapView != null)
            mMapView.onSaveInstanceState(outState);
    }


    /**
     * 绘制点标记
     */
    private void addmarkerOption(LinesInfobus linesInfobus) {

        LatLng latLng = new LatLng(linesInfobus.getLatitude(), linesInfobus.getLongitude());
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title(linesInfobus.getSiteName()).snippet(linesInfobus.getAddress());
        markerOption.draggable(false);//设置Marker可拖动
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gps1));
        markerOption.icon(bitmapDescriptor);

        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        Marker marker = aMap.addMarker(markerOption);

        //绘制动画效果 Marker
        //Animation animation = new RotateAnimation(marker.getRotateAngle(), marker.getRotateAngle() + 360, 0, 0, 0);
        //animation.setDuration(10000L);
        //animation.setInterpolator(new LinearInterpolator());
        //marker.setAnimation(animation);
        //marker.startAnimation();

        // 绘制曲线
        aMap.addPolyline((new PolylineOptions()).add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55)).geodesic(true).color(Color.RED));


    }

    private void setMarker() {
        //绘制marker
        Marker marker = aMap.addMarker(new MarkerOptions().position(new LatLng(39.986919, 116.353369)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gps1))).draggable(true));

        // 绘制曲线
        aMap.addPolyline((new PolylineOptions()).add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55)).geodesic(true).color(Color.RED));
    }

    /**
     * 拿到校车位置
     *
     * @param latitude
     * @param longitude
     */
    private void getschoolbus(double latitude, double longitude) {
        Datamodule.getInstance().schoolbus(String.valueOf(latitude), String.valueOf(longitude), userinfo.getSchoolId(), new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void OnSuccess(Object object) {
                List<schoolbust> mlist = (List<schoolbust>) object;
                list.addAll(mlist);
                adapter.notifyDataSetChanged();

                for (schoolbust schoolbust : mlist) {
                    List<LinesInfobus> linesInfobuses = JsonUitl.stringToList(schoolbust.getLinesInfo(), LinesInfobus.class);
                    LinesInfobus linesInfobus = linesInfobuses.get(linesInfobuses.size() - 1);
                    // 校车标识点
                    addmarkerOption(linesInfobus);
                }


            }
        });
    }
}