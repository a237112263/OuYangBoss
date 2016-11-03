package utils;

import android.content.Context;

import bean.SMSData;

/**
 * Created by Administrator on 2016/4/27.
 */
public class DatasUtils {
    public static final String yanzhengUrl="http://appweb.keyierp.com/sms.aspx?m=";
    public static final String mobUrl="http://appweb.keyierp.com/ERP/Login.aspx?mobile=";
    public static final String detailsUrl= "http://oyapp.keyierp.com/OYWL/GetLogisBatchByApp.aspx?mobile=";
    public static final String strToken="&Token=";
    public static final String LogisUrl="http://oyapp.keyierp.com/OYWL/LogisBatchFollow.aspx?mobile=";
    public static final String allLogis="http://oyapp.keyierp.com/OYWL/GetAllLogisBatchFollow.aspx?mobile=";
    public String SMSData(Context context){
        ACache aCache=ACache.get(context);
        SMSData smsData= (SMSData) aCache.getAsObject("SMSData");
        return smsData.getData().toString();
    }
    public String MobilNumber(Context context){
        ACache aCache=ACache.get(context);
        return aCache.getAsString("MobilNumber").toString();
    }
}
