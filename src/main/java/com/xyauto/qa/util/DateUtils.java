package com.xyauto.qa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String formatTime = "yyyy-MM-dd HH:mm:ss";
    public static String formatDate = "yyyyMMdd";
    public static String formatDateDay = "yyyy-MM-dd";
    public static Date strToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date strToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //日期转字符串
    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        return sdf.format(date);
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    //获取指定日期的前N天
    public static Date beforDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -num);
        date = calendar.getTime();
        return date;
    }
    /**
     * 获取指定日期的前N天
     * @param time
     * @param num：小于0表示后N天
     * @return
     */
    public static Date beforDate(String time, int num) {
    	Date date=strToDate(time,formatDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -num);
        date = calendar.getTime();
        return date;
    }
    //
    public static String intToDateString(int time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
        return sdf.format(date);
    }

    public static String intToDateString(int time, String format) {
        Date date = null;
        if (String.valueOf(time).length() == 10) {
            date = new Date(time * 1000L);
        }else{
            date = new Date(time);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Integer getTimes() {
        long temp = System.currentTimeMillis() / 1000;
        return (int) temp;
    }
    
    public static Date beforMonth(String time, int num,String formatDate){
    	Date date=strToDate(time,formatDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +num);
        date = calendar.getTime();
        return date;
    }
}
