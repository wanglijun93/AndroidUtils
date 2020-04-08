package wanglijun.vip.androidutils.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description 时间转换工具类
 */
public class DateToStringUtils {
    /**
     * 字符串转换到时间格式
     * @param dateStr 需要转换的字符串
     * @return dateFormatStr 需要转换的字符串的时间格式
     * @param formatStr 需要格式的目标字符串  举例 yyyyMMdd
     * @return String 返回转换后的时间字符串
     * @throws ParseException 转换异常
     */
    public static String StringToDate(String dateStr,String dateFormatStr,String formatStr){
        @SuppressLint("SimpleDateFormat") DateFormat sdf=new SimpleDateFormat(dateFormatStr);
        Date date=null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat s=new SimpleDateFormat(formatStr);

        return s.format(date);
    }
    public static String StringToDate2(long dateStr,String dateFormatStr,String formatStr){
        @SuppressLint("SimpleDateFormat") DateFormat sdf=new SimpleDateFormat(dateFormatStr);
        Date date=null;
        try {
            date = sdf.parse(String.valueOf(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat s=new SimpleDateFormat(formatStr);

        return s.format(date);
    }
}
