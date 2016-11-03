package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.keyi.ouyangboss.R;

import bean.LogisData;
import utils.ACache;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CarListViewAdapter extends BaseAdapter {
         private LogisData logisData;
        public CarListViewAdapter(Context context) {
            ACache aCache=ACache.get(context);
            logisData= (LogisData) aCache.getAsObject("LogisData");
        }
        @Override
        public int getCount() {
            return logisData.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return logisData.getData().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convert, ViewGroup parent) {
                convert = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.listview_carmsg, parent, false);
                TextView tv1=(TextView) convert.findViewById(R.id.tv1);
                TextView tv2=(TextView) convert.findViewById(R.id.tv2);
                TextView tv4=(TextView) convert.findViewById(R.id.tv4);
                Button button= (Button) convert.findViewById(R.id.bt_details);
                Button button1= (Button) convert.findViewById(R.id.bt_details1);
            if (position==0){
                tv1.setText("车次号："+logisData.getData().get(position).getTradeNo());
            }else {
                tv1.setVisibility(View.GONE);
            }
            tv2.setText("货物状态:"+logisData.getData().get(position).getTrackMessage());
            tv4.setText("时间：" + logisData.getData().get(position).getCreateDateString());
            if ( position==(logisData.getData().size()-1)){
                button.setVisibility(View.GONE);
                button1.setVisibility(View.VISIBLE);
            }
            Log.e("asdasd", convert.getTag() + "");
            return convert;
        }
    }


