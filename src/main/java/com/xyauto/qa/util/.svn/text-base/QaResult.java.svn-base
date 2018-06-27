package com.xyauto.qa.util;

import com.xyauto.qa.cons.ErrorCode;


/**
 * Created by shiqm on 2017/3/23.
 */
public class QaResult {

    private String code;
    private String message;
    private Object data;


    public QaResult() {
        this(ErrorCode.OK, "");
    }

    @Override
    public String toString() {
        return "QaResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public QaResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public QaResult(Object data) {
        this(ErrorCode.OK, data);
    }

    public QaResult(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public QaResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
