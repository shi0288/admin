package com.xyauto.qa.controller;

import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.exceptions.DataTransferException;
import com.xyauto.qa.exceptions.QaRuntimeException;
import com.xyauto.qa.util.QaResult;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by shiqm on 2017/3/23.
 */
public class AjaxBaseController extends ABaseController{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<QaResult> handleException(Exception ex) {
        Throwable e = ExceptionUtils.getRootCause(ex);
        if (null != e) {
            ex = (Exception) e;
        }
        // 为了避免输出过多无用的异常堆栈，作如下处理：继承自DataTransferException的异常信息，仅输出错误信息，不输出堆栈信息
        // 这样真正的内部异常才会以堆栈信息展示出来，查日志的时候减少了无用信息的干扰
        if (DataTransferException.class.isAssignableFrom(ex.getClass())) {
            DataTransferException be = (DataTransferException) ex;
            //因为是警告，就正常输入了
            logger.warn(be.getErrMsg());
        } else {
            //对真正的错误输入堆栈
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        // 封装接口返回结果
        QaResult qaResult;
        if (QaRuntimeException.class.isAssignableFrom(ex.getClass())) {
            QaRuntimeException qaRuntimeException = (QaRuntimeException) ex;
            qaResult = new QaResult(qaRuntimeException.getErrCode(), qaRuntimeException.getErrMsg());
        }
        // 其他异常信息
        else {
            // 如果是MissingServletRequestParameterException异常，则解析缺少的参数并打印出来
            if (ex.getClass().isAssignableFrom(MissingServletRequestParameterException.class)) {
                MissingServletRequestParameterException msrpException = (MissingServletRequestParameterException) ex;
                logger.warn("有请求缺少参数：" + msrpException.getParameterName());
            }
            qaResult = new QaResult(ErrorCode.OVER, "未知错误");
        }
        return new ResponseEntity<>(qaResult, HttpStatus.OK);

    }


}
