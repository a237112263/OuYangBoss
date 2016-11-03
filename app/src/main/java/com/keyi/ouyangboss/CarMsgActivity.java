package com.keyi.ouyangboss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import adapter.CarListViewAdapter;
import bean.LogisData;
import utils.ACache;
import utils.DatasUtils;
import utils.HttpUtils;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CarMsgActivity extends Activity implements View.OnClickListener {
    private ListView listView;
    private CarListViewAdapter adpter;
    private ACache aCache;
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmsg);
        aCache=ACache.get(this);
        listView = (ListView) findViewById(R.id.lv_car);
        button= (Button) findViewById(R.id.checi_car);
        button.setOnClickListener(this);
        Intent intent = getIntent();
        String string = intent.getStringExtra("LogisBatchNo");
        dengluHttp(string);
    }

    private void dengluHttp(String string) {
        DatasUtils datasUtils=new DatasUtils();
        final String url = DatasUtils.LogisUrl + aCache.getAsString("MobilNumber") + DatasUtils.strToken +datasUtils.SMSData(this)+ "&LogisBatchNo=" + string;
        HttpUtils utils = new HttpUtils();
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Log.e("123",data.toString());
                Gson gson=new Gson();
                LogisData logisData=gson.fromJson(data,LogisData.class);
                aCache.put("LogisData",logisData);
                adpter = new CarListViewAdapter(CarMsgActivity.this);
                listView.setAdapter(adpter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(CarMsgActivity.this,MapOneActivity.class);
        startActivity(intent);
    }
}
