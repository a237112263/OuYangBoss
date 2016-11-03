package com.keyi.ouyangboss;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import bean.LogisData;
import utils.ACache;

public class MapOneActivity extends AppCompatActivity {
    private MapView mMapView;
    private BitmapDescriptor bitmap,bitmap1;
    private BaiduMap mBaiduMap;
    private LatLng latLng4;
    private TextView location;
    private LatLng[] latLngs;
    private SharedPreferences sharedPreferences;
    private LogisData logisData;
    private ACache aCache;
    private int[] bitmapId={R.drawable.icon1,R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,R.drawable.icon7,R.drawable.icon8,R.drawable.icon9,R.drawable.icon10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapone_demo);
        initView();
        logisData= (LogisData) aCache.getAsObject("LogisData");
            latLngs = new LatLng[logisData.getData().size()];
            List<LatLng> pts = new ArrayList<LatLng>();
        if (logisData.getData().size()==1){
            Toast.makeText(MapOneActivity.this,"暂时没有线路！",Toast.LENGTH_SHORT).show();
        }
            for (int i = 0; i < logisData.getData().size(); i++) {
                try {
                    Log.e("sadds", logisData.getData().get(i).getNextUserName().toString());
                    String spStr[] = logisData.getData().get(i).getNextUserName().toString().split(",");
                    bitmap = BitmapDescriptorFactory
                            .fromResource(bitmapId[i]);
                    Log.e("NextUserName1", Float.parseFloat(spStr[0]) + "");
                    Log.e("NextUserName2", Float.parseFloat(spStr[1]) + "");
                    latLng4 = new LatLng(Float.parseFloat(spStr[1]), Float.parseFloat(spStr[0]));
                    latLngs[i]=latLng4;
                    pts.add(latLng4);
                    OverlayOptions option1 = new MarkerOptions()
                            .position(latLng4)
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                    mBaiduMap.addOverlay(option1);
                }catch (Exception e){

                }

            }
        try {
            canvinLine(pts);

        }catch (Exception e){

        }
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLngZoom(
                latLng4, 10f);
        mBaiduMap.animateMapStatus(statusUpdate);
        markWindow();
    }

    private void canvinLine(List<LatLng> pts) {
        //构建分段颜色索引数组
        List<Integer> colors = new ArrayList<Integer>();
        colors.add(Integer.valueOf(Color.BLUE));
        OverlayOptions ooPolyline = new PolylineOptions().width(10)
                .colorsValues(colors).points(pts);
        Polyline mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
    }

    private void markWindow() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                location = new TextView(getApplicationContext());
                location.setBackgroundResource(R.drawable.popup);
                location.setTextColor(Color.RED);
                location.setPadding(10, 10, 10, 10);
                LatLng ll = marker.getPosition();
                for (int i = 0; i < logisData.getData().size(); i++) {
                    if (ll.equals(latLngs[i])) {
                        location.setText(logisData.getData().get(i).getTrackMessage().toString());
                        Log.e("pts", logisData.getData().get(i).getTrackMessage().toString()+ "");
                    }
                }
                InfoWindow mInfoWindow;
                mInfoWindow = new InfoWindow(location, ll, -47);
                //显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);
                return true;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                return true;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                mBaiduMap.hideInfoWindow();
            }
        });
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView1);
        // 获取地图的控制对象
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);
        aCache=ACache.get(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaiduMap.clear();
    }
}

