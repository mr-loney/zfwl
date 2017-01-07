package com.zfwl.util;

import android.os.Looper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZZB on 2016/12/17.
 */
public class Utils {

    public static boolean isMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }
    public static String longToStringFriendly(long currentTime)
            throws ParseException {
        String formatType = "yyyy-MM-dd HH:mm";
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String

        Date nowDate = new Date(System.currentTimeMillis());
        if (date.getYear() == nowDate.getYear() &&
                date.getMonth() == nowDate.getMonth() &&
                date.getDay() == nowDate.getDay())
        {
            strTime = strTime.substring(11,16);
        } else {
            strTime = strTime.substring(5,10);
        }
        return strTime;
    }

    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
}
