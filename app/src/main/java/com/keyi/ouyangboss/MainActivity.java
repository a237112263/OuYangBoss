package com.keyi.ouyangboss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import adapter.MyAListViewAdpter;
import bean.CarMessage;
import utils.ACache;
import utils.DatasUtils;
import utils.HttpUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button imageView;
    private ListView listView;
    private MyAListViewAdpter adpter;
    private long exitTime = 0;
    private ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aCache=ACache.get(this);
        listView = (ListView) findViewById(R.id.lv_main);
        imageView = (Button) findViewById(R.id.iv_car);
        imageView.setOnClickListener(this);
        dengluHttp();
    }

    private void dengluHttp() {
        DatasUtils datasUtils=new DatasUtils();
        final String url = DatasUtils.detailsUrl + aCache.getAsString("MobilNumber") + DatasUtils.strToken +datasUtils.SMSData(this) ;
        HttpUtils utils = new HttpUtils();
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Log.e("asdasd", data);
                Gson gson = new Gson();
                CarMessage carMessage = gson.fromJson(data, CarMessage.class);
                if (carMessage.isIsOK()) {
                    aCache.put("CarMessage", carMessage);
                    adpter = new MyAListViewAdpter(MainActivity.this);
                    listView.setAdapter(adpter);
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MapAllActivity.class);
        startActivity(intent);
    }
}
