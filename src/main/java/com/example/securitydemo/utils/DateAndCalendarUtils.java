package com.example.securitydemo.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @date 2020/6/10 0010 18:05
 * @description
 **/
public class DateAndCalendarUtils {

    public final static String DATE_FORMAT_YMDHMS="yyyy-MM-dd HH:mm:ss";

    public static String CalendartoString(Date date){
        if(date==null) return null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    public static String CalendartoString(Calendar cal){
        if(cal==null) return null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=cal.getTime();
        return sdf.format(d);
    }
    public static String CalendartoStringYMD(Calendar cal){
        if(cal==null) return null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d=cal.getTime();
        return sdf.format(d);
    }
    public static Calendar StringtoCalendarYMD(String s) throws ParseException {
        if(s==null) return null;
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(sdf.parse(s));
        return cal;
    }
    public static Calendar StringtoCalendar(String s) throws ParseException{
        if(s==null) return null;
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(sdf.parse(s));
        return cal;
    }
    public static String FormatCalendar(Calendar cal){
        if(cal==null) return null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d=cal.getTime();
        return sdf.format(d);
    }
    public static String FormatHM(Calendar cal){
        if(cal==null) return null;
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
        Date d=cal.getTime();
        return sdf.format(d);
    }
    public static int StringtoInt(String temp){
        return Integer.valueOf(temp).intValue();
    }
    public static String compareCalendar4Format(Calendar cal1,Calendar cal2){
        long s=cal2.getTimeInMillis()-cal1.getTimeInMillis();
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Calendar cal3=Calendar.getInstance();
        cal3.setTimeInMillis(s);
        cal3.add(Calendar.HOUR_OF_DAY, -8);
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        Date d=cal3.getTime();
        return sdf.format(d);
    }

    /**
     * 时间戳转为相应格式时间字符串
     * @param timestamp
     * @return
     */
    public static String formatTime(long timestamp){
        return new SimpleDateFormat(DATE_FORMAT_YMDHMS).format(new Date(timestamp));
    }

    /**
     * 时间戳类型转为相应格式时间字符串
     * @param timestamp
     * @return
     */
    public static String formatTimestamp(Timestamp timestamp){
        if (timestamp==null){
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_YMDHMS).format((Date)timestamp);
    }

    /**
     * 时间戳类型转为相应格式时间字符串
     * 在同个方法中多次调用的情况，可传入同个SimpleDateFormat对象，避免重复new对象。
     * @param timestamp
     * @return
     */
    public static String formatTimestamp(Timestamp timestamp, SimpleDateFormat sdf){
        if (timestamp==null){
            return "";
        }
        return sdf.format((Date)timestamp);
    }

    /**
     * Date类型转相应格式字符串
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        if (date==null){
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_YMDHMS).format(date);
    }
}
