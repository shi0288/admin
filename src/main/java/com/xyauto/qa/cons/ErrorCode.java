package com.xyauto.qa.cons;

/**
 * Created by shiqm on 2017/3/23.
 */
public class ErrorCode {

    public static final String OK = "0";

    public static final String OVER = "9999";


    // ------------------ 登录失败 ------------------

    public static final class LOGIN_ERR {
        public static final String FAILED = "1000";
        public static final String NAME_EMPTY = "1";
    }

    // ------------------ 后台转换html ------------------

    public static final class HTML_ERR {
        public static final String FAILED = "1100";
    }

    // ------------------ 数据库操作 ------------------

    public static final class DB_ERR {
        public static final String UPDATE_FAILED = "2000";
    }



    // ------------------ 微服务操作 ------------------

    public static final class CLOUD_ERR {
        public static final String DATA_FAILED = "2000";
    }

}
