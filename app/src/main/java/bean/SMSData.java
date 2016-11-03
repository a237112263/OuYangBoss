package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/16.
 */
public class SMSData implements Serializable{
    private static final long serialVersionUID = -1L;
    private boolean IsOK;
    private Object ErrMsg;
    private String Data;

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

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
