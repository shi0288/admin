package com.xyauto.qa.exceptions;

import com.xyauto.qa.cons.ErrorCode;

/**
 * Created by shiqm on 2017/3/23.
 */
public class LoginFailedException extends DataTransferException {

    public LoginFailedException(String errMsg) {
        super(ErrorCode.LOGIN_ERR.FAILED, errMsg);
    }
}
