package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class CarMessage implements Serializable{
    private static final long serialVersionUID = -3717349166478282625L;

    private boolean IsOK;
    private Object ErrMsg;

    private List<DataBean> Data;

    public boolean isIsOK() {
        return IsOK;
    }

    public void setIsOK(boolean IsOK) {
        this.IsOK = IsOK;
    }

    public Object getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(Object ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = -5285218081391954727L;
        private String LogisBatchNo;
        private String Driver;
        private String DriverMobile;
        private String CarNo;
        private double Carcost;
        private int ConsignmentNum;
        private String SendDate;
        private String SendDateString;

        public String getLogisBatchNo() {
            return LogisBatchNo;
        }

        public void setLogisBatchNo(String LogisBatchNo) {
            this.LogisBatchNo = LogisBatchNo;
        }

        public String getDriver() {
            return Driver;
        }

        public void setDriver(String Driver) {
            this.Driver = Driver;
        }

        public String getDriverMobile() {
            return DriverMobile;
        }

        public void setDriverMobile(String DriverMobile) {
            this.DriverMobile = DriverMobile;
        }

        public String getCarNo() {
            return CarNo;
        }

        public void setCarNo(String CarNo) {
            this.CarNo = CarNo;
        }

        public double getCarcost() {
            return Carcost;
        }

        public void setCarcost(double Carcost) {
            this.Carcost = Carcost;
        }

        public int getConsignmentNum() {
            return ConsignmentNum;
        }

        public void setConsignmentNum(int ConsignmentNum) {
            this.ConsignmentNum = ConsignmentNum;
        }

        public String getSendDate() {
            return SendDate;
        }

        public void setSendDate(String SendDate) {
            this.SendDate = SendDate;
        }

        public String getSendDateString() {
            return SendDateString;
        }

        public void setSendDateString(String SendDateString) {
            this.SendDateString = SendDateString;
        }
    }
}
