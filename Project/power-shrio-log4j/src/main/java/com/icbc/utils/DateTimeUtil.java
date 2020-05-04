package com.icbc.utils;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTimeUtil {

    public static String farmat(Date date, String fromat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromat);
        return simpleDateFormat.format(date);
    }

    public static String farmat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String DateToStr2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String timeDifference(String a1, String b1) {
        DateTimeFormatter forPattern = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime a2 = forPattern.parseDateTime(a1);
        DateTime b2 = forPattern.parseDateTime(b1);
        int months = Months.monthsBetween((ReadableInstant) b2, (ReadableInstant) a2).getMonths();
        return months + "";
    }


    public static String randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = simpleDateFormat.parse(beginDate);
            Date end = simpleDateFormat.parse(endDate);
            if (start.getTime() >= end.getTime()) {
                return null;
            }

            long data = random(start.getTime(), end.getTime());
            return DateToStr2(new Date(data));
        } catch (Exception exception) {


            return null;
        }
    }

    public static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
