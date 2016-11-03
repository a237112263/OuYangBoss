package com.keyi.ouyangboss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bean.AllCarData;
import bean.LogisData;
import utils.ACache;
import utils.DatasUtils;
import utils.HttpUtils;
import view.FlowLayout;

public class MapAllActivity extends AppCompatActivity {
    private ACache aCache;
    private LogisData logisData;
    private MapView mMapView;
    private BitmapDescriptor bitmap;
    private BaiduMap mBaiduMap;
    private LatLng latLng4;
    private TextView location;
    private LatLng[] latLngs;
    private int[] bitmapId={R.drawable.icon1,R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,R.drawable.icon7,R.drawable.icon8,R.drawable.icon9,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10,R.drawable.icon10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myoverlay_demo);
        aCache=ACache.get(this);
        initView();
        DatasUtils datasUtils=new DatasUtils();
        HttpUtils httpUtils=new HttpUtils();
        String url=DatasUtils.allLogis+datasUtils.MobilNumber(this)+DatasUtils.strToken+datasUtils.SMSData(this);
        Log.e("saddsa", url.toString());
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                AllCarData allCarData = gson.fromJson(data, AllCarData.class);
                Log.e("saddsa", allCarData.isIsOK() + "");
                int count = 0;
                for (int j = 0; j < allCarData.getData().size(); j++) {
                    latLngs = new LatLng[100];
                    List<LatLng> pts = new ArrayList<LatLng>();
                    for (int t = 0; t < allCarData.getData().get(j).getList().size(); t++) {
                        try {
                            String spStr[] = allCarData.getData().get(j).getList().get(t).getNextUserName().toString().split(",");
                            bitmap = BitmapDescriptorFactory
                                    .fromResource(bitmapId[t]);
                            latLng4 = new LatLng(Float.parseFloat(spStr[1]), Float.parseFloat(spStr[0]));
                            latLngs[count] = latLng4;
                            count++;
                            pts.add(latLng4);
                            OverlayOptions option1 = new MarkerOptions()
                                    .position(latLng4)
                                    .icon(bitmap);
                            //在地图上添加Marker，并显示
                            mBaiduMap.addOverlay(option1);
                        } catch (Exception e) {

                        }
                    }
                    int[] colors1 = {Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.rgb(255,128,0), Color.CYAN, Color.rgb(255,128,192),Color.rgb(64,128,128),Color.rgb(192,192,192),Color.rgb(128,128,64),Color.rgb(64,0,128),Color.rgb(128,0,0),Color.rgb(128,255,0),Color.rgb(128,98,36),Color.rgb(255,115,0),Color.rgb(0,188,235),Color.rgb(100,0,125),Color.rgb(50,100,231)};
                    canvinLine(pts, j, colors1[j],allCarData);
                    MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLngZoom(
                            latLng4, 10f);
                    mBaiduMap.animateMapStatus(statusUpdate);
                    markWindow(j, allCarData);
                }
            }
        });

    }
    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        // 获取地图的控制对象
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);
    }

    private void canvinLine(List<LatLng> pts,int j,int colors1,AllCarData allCarData) {
        FlowLayout flowLayout= (FlowLayout) findViewById(R.id.fl);
        LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams. MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(lp1);
        TextView textView=new TextView(this);
        textView.setPadding(10, 0, 0, 0);
        textView.setTextSize(12);
        textView.setTextColor(Color.rgb(0, 128, 255));
        textView.setText(allCarData.getData().get(j).getList().get(0).getTradeNo());
        TextView textView1=new TextView(this);
        textView1.setBackgroundColor(colors1);
        textView1.setWidth(100);
        textView1.setHeight(5);
        linearLayout.addView(textView);
        linearLayout.addView(textView1);
        flowLayout.addView(linearLayout, new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
        //构建分段颜色索引数组
            List<Integer> colors = new ArrayList<Integer>();
            colors.add(Integer.valueOf(colors1));
            try{
                OverlayOptions ooPolyline = new PolylineOptions().width(10)
                        .colorsValues(colors).points(pts);
                Polyline mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
            }catch (Exception e){

            }
        }


    private void markWindow(final int j, final AllCarData allCarData) {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                location = new TextView(getApplicationContext());
                location.setBackgroundResource(R.drawable.popup);
                location.setTextColor(Color.RED);
                location.setPadding(10, 10, 10, 10);
                LatLng ll = marker.getPosition();
                for (int i = 0; i < allCarData.getData().get(j).getList().size(); i++) {
                    try {
                        String spStr[] = allCarData.getData().get(j).getList().get(i).getNextUserName().toString().split(",");
                        LatLng lng=new LatLng(Float.parseFloat(spStr[1]), Float.parseFloat(spStr[0]));
                        if (ll.toString().equals(lng.toString())) {
                            Log.e("ll", allCarData.getData().get(j).getList().get(i).getTrackMessage().toString());
                            location.setText(allCarData.getData().get(j).getList().get(i).getTrackMessage().toString());
                            InfoWindow mInfoWindow ;
                            mInfoWindow = new InfoWindow(location, ll, -47);
                            mBaiduMap.showInfoWindow(mInfoWindow);
                            return true;
                            //显示InfoWindow
                        }else {

                        }
                    }catch (Exception e){

                    }
                }
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaiduMap.clear();
    }
}

