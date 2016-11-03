package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class LogisData implements Serializable{
    private static final long serialVersionUID = -2L;


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
        private static final long serialVersionUID = -3L;
        private String CreateDate;
        private Object NextUserName;
        private String TrackMessage;
        private String TradeNo;
        private String UserName;
        private String CreateDateString;

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public Object getNextUserName() {
            return NextUserName;
        }

        public void setNextUserName(Object NextUserName) {
            this.NextUserName = NextUserName;
        }

        public String getTrackMessage() {
            return TrackMessage;
        }

        public void setTrackMessage(String TrackMessage) {
            this.TrackMessage = TrackMessage;
        }

        public String getTradeNo() {
            return TradeNo;
        }

        public void setTradeNo(String TradeNo) {
            this.TradeNo = TradeNo;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getCreateDateString() {
            return CreateDateString;
        }

        public void setCreateDateString(String CreateDateString) {
            this.CreateDateString = CreateDateString;
        }
    }
}
