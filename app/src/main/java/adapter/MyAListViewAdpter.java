package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.keyi.ouyangboss.CarMsgActivity;
import com.keyi.ouyangboss.R;

import bean.CarMessage;
import utils.ACache;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MyAListViewAdpter extends BaseAdapter implements View.OnClickListener {
    private  Button button;
    private Context context;
    private CarMessage carMessage;
    public MyAListViewAdpter(Context context) {
        this.context=context;
        ACache aCache=ACache.get(context);
        carMessage= (CarMessage) aCache.getAsObject("CarMessage");
    }
    @Override
    public int getCount() {
        return carMessage.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return carMessage.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder=null;
        if (convert==null) {
            convert = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.listview_contain, parent, false);
            holder=new ViewHolder();
            holder.tv1=(TextView) convert.findViewById(R.id.tv1);
            holder.tv2=(TextView) convert.findViewById(R.id.tv2);
            holder.tv3=(TextView) convert.findViewById(R.id.tv3);
            holder.tv4=(TextView) convert.findViewById(R.id.tv4);
            holder.tv5=(TextView) convert.findViewById(R.id.tv5);
            holder.tv6=(TextView) convert.findViewById(R.id.tv6);
            holder.tv7=(TextView) convert.findViewById(R.id.tv7);
            convert.setTag(holder);
        }else{
            holder=(ViewHolder) convert.getTag();
        }
        setDataToUi1(position, holder);
        button= (Button) convert.findViewById(R.id.bt_list);
        button.setTag(position);
        button.setOnClickListener(this);
        return convert;
    }

    private void setDataToUi1(int position, ViewHolder holder) {
        holder.tv1.setText("车次号："+carMessage.getData().get(position).getLogisBatchNo());
        holder.tv2.setText("司机:"+carMessage.getData().get(position).getDriver());
        holder.tv3.setText("司机电话:" +carMessage.getData().get(position).getDriverMobile());
        holder.tv4.setText("车牌号："+carMessage.getData().get(position).getCarNo());
        holder.tv5.setText("大车费用："+carMessage.getData().get(position).getCarcost());
        holder.tv6.setText("运单数量："+carMessage.getData().get(position).getConsignmentNum());
        holder.tv7.setText("发车时间："+carMessage.getData().get(position).getSendDateString());
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.bt_list:
               Intent intent=new Intent(context, CarMsgActivity.class);
               for (int i=0;i<carMessage.getData().size();i++){
                   if ((int)v.getTag()==i){
                        intent.putExtra("LogisBatchNo", carMessage.getData().get(i).getLogisBatchNo());
                       context.startActivity(intent);
                   }
               }
       }
    }

    class ViewHolder{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private TextView tv6;
        private TextView tv7;
    }
}
