package com.xyauto.qa.exceptions;

import com.xyauto.qa.cons.Level;

/**
 * Created by shiqm on 2017/3/23.
 */
public class QaRuntimeException extends RuntimeException{

    protected String errCode;
    protected String errMsg;
    protected Level level = Level.ERROR;


    public QaRuntimeException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
