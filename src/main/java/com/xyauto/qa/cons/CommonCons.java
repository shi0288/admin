package com.xyauto.qa.cons;

/**
 * Created by shiqm on 2017/3/22.
 */
public class CommonCons {


    /**
     * SESSION配置
     */
    public static final class Session_Flag {

        public static final String ACCOUNT = "account";
        public static final String USERINFO = "systemUserinfo";

        public static final String MENU_PARENT = "menu_parent";
        public static final String MENU_CHILDREN = "menu_children";
        public static final String CUR_PARENT = "cur_parent";
        public static final String CUR_CHILDREN = "cur_children";

    }

    /**
     * 分页配置
     */
    public static final class Pager_Flag {

        /**
         * 每页默认限制数
         */
        public static final Integer PAGE_LIMIT = 20;

        /**
         * 页数Key
         */
        public static final String KEY_PAGE = "getPageNum";

        /**
         * 每页实际限制数
         */
        public static final String KEY_LIMIT = "getPageSize";
    }


    /**
     * Freemarker
     */
    public static final class Freemarker_Flag {

        /**
         * suffix
         */
        public static final String FILE_SUFFIX = ".ftl";

        /**
         * prefix
         */
        public static final String FILE_PREFIX = "user/";

    }


    /**
     * 用户相关
     */
    public static final class User_Type_Flag {
        /**
         * 普通用户
         */
        public static final int USER_TYPE_PERSON = 0;
        /**
         * 标兵
         */
        public static final int USER_TYPE_EXAMPLE = 1;
        /**
         * 专家
         */
        public static final int USER_TYPE_EXPERT = 2;
        
        /**
         * 车顾问
         */
        public static final int USER_TYPE_ADVISER = 3;

    }


    /**
     * 块相关
     */
    public static final class Blocks_Flag {

        public static final String BANNER_PC = "index.bannerForPc";

        public static final String BANNER_MOBILE = "index.banner";

        public static final String NOTICE_PC = "system.announce";

        public static final String EXPERT_PC = "expert.recruit";

        public static final String USER_EXPERT_RECOMMEND = "user.recommendExpert";

        public static final String USER_EXPERT_BEST = "user.bestExpert";

        public static final String USER_MODEL_BEST = "user.bestPaceSetter";

        public static final String USER_OPERATE_BUSINESS = "user.OperateBusiness";
        
        public static final String TOPIC_TODAY = "topic.today";
        
        public static final String INFORMATION_EXPERT = "information_expert";
        public static final String ADVER1_EXPERT = "adver1_expert";
        public static final String ADVER2_EXPERT = "adver2_expert";
        public static final String USER_EXPERT_HOME = "user.expert.home";
        public static final String POPULAR_TOPIC = "popular.topic";
        public static final String ONE_INFORMATION= "one.information";
        public static final String recommend_Topic= "topic.recommend";
        public static final String Adver_Home= "adver.home";
        public static final String LABEL_QUESTION= "label.question";
        public static final String Advisor_Home="advisor.home";

    }

    /**
     * 块相关
     */
    public static final class Cloud_User_Flag {

        public static final Integer CODE_OK = 10000;

    }


    /**
     * Freemarker
     */
    public static final class Config_Flag {

        /**
         * 上传图片大小限制
         */
        public static final long PICTURE_LIMIT_SIZE = 8 * 1024 * 1024; //MB


        /**
         * 上传图片压缩长度
         */
        public static final int PICTURE_PC_HEIGHT = 1000; //px


        /**
         * 上传图片压缩宽度
         */
        public static final int PICTURE_PC_WIDTH = 300; //px


        /**
         * 上传图片压缩长度
         */
        public static final int PICTURE_MOBILE_HEIGHT = 400; //px


        /**
         * 上传图片压缩宽度
         */
        public static final int PICTURE_MOBILE_WIDTH = 200; //px


        /**
         * 上传图片类型
         */
        public static final String[] PICTURE_TYPE = {"jpg", "png", "jpeg", "bmp", "gif"}; //px

    }

    public static final class Quesiotn_Flag{
    	public static final Integer DELETE_QUESTION=0;
    	public static final Integer RUBBISH_QUESTION=-2;//
    	public static final Integer NORMAL_QUESTION=1;//正常状态数据，对应的问题表中的1,2
    	public static final Integer REVIEW_QUESTION=-1;
    	public static final Integer RELEASE_QUESTION=-98;//定时发布的问题，对应状态值-3
    	
    }
}
