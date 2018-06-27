package com.xyauto.qa.cons;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shiqm on 2016-04-23.
 */
public class ConvertCons {

    public static final class ResourceCover {
        public static final Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            init();
        }

        public static void init() {
            map.put(1, "PC");
            map.put(2, "IOS");
            map.put(3, "Android");
            map.put(4, "M站");
            map.put(5, "H5");
            map.put(6, "H51");
            map.put(7, "小程序");
            map.put(8, "活动");
            map.put(106, "平安好车主");
            map.put(101, "慧商机");
            map.put(102, "百度");
            map.put(103, "360");
            map.put(998, "后台创建");
            map.put(999, "批量导入");
        }

        public static String get(Integer key) {
            return map.get(key);
        }

        public static Map<Integer, String> getMap() {
            return map;
        }
    }


    public static final class UserTypeCover {
        public static final Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            init();
        }

        public static void init() {
            map.put(0, "普通用户");
            map.put(1, "标兵");
            map.put(2, "专家");
            map.put(3, "车顾问");
            map.put(9, "官方");
        }

        public static String get(Integer key) {
            return map.get(key);
        }

        public static Map<Integer, String> getMap() {
            return map;
        }
    }


    public static final class LogModuleCover {
        public static final Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            init();
        }

        public static void init() {
            LogModule[] temps = LogModule.values();
            for (LogModule logModule : temps) {
                map.put(logModule.getValue(), logModule.getName());
            }
        }

        public static String get(Integer key) {
            return map.get(key);
        }

        public static Map<Integer, String> getMap() {
            return map;
        }
    }

    public static final class LogActionCover {
        public static final Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            init();
        }

        public static void init() {
            LogAction[] temps = LogAction.values();
            for (LogAction logAction : temps) {
                map.put(logAction.getValue(), logAction.getName());
            }
        }

        public static String get(Integer key) {
            return map.get(key);
        }

        public static Map<Integer, String> getMap() {
            return map;
        }
    }

    public static final class QuestionType{
    	public static final Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            init();
        }

        public static void init() {
            map.put(1, "提问贴");
//            map.put(2, "投票");
//            map.put(3, "PK");
            map.put(4, "活动贴");
            map.put(5, "分享贴");
        }

        public static String get(Integer key) {
            return map.get(key);
        }

        public static Map<Integer, String> getMap() {
            return map;
        }
    }
}
