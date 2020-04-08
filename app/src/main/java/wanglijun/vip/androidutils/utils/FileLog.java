package wanglijun.vip.androidutils.utils;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author wlj
 * @date 2017/3/28
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description 带日志文件输入的，又可控开关的日志调试
 */
public class FileLog {

    /**
     * 日志文件总开关
     */
    private static Boolean MYLOG_SWITCH = false;
    /**
     * 日志写入文件开关
     */
    private static Boolean MYLOG_WRITE_TO_FILE = false;
    /**
     * 输入日志类型，w代表只输出告警信息等，v代表输出所有信息
     */
    private static char MYLOG_TYPE = 'v';
    /**
     * sd卡中日志文件的最多保存天数
     */
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 0;
    /**
     * 目录
     */
    private static String sDirs;
    /**
     * 本类输出的日志文件名称
     */
    private static String MYLOGFILEName = "Log.txt";
    /**
     * 日志的输出格式
     */
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    /**
     * 日志文件格式
     */
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }

    /**
     * 配置文件log
     *
     * @param dirs      目录
     * @param isShowLog true表示写入
     */
    public static void setConfig(String dirs, boolean isShowLog) {
        sDirs = dirs;
        MYLOG_SWITCH = isShowLog;
        MYLOG_WRITE_TO_FILE = isShowLog;
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */
    private static void log(String tag, String msg, char level) {
        if (MYLOG_SWITCH) {
            if ('e' == level && ('e' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.e(tag, msg);
            } else if ('w' == level && ('w' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.w(tag, msg);
            } else if ('d' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.d(tag, msg);
            } else if ('i' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);

            }
            if (MYLOG_WRITE_TO_FILE) {
                writeLogToFile(String.valueOf(level), tag, msg);
            }
        }
    }

    /**
     * 打开日志文件并写入日志
     * 新建或打开日志文件
     * @return
     **/
    private static void writeLogToFile(String myLogType, String tag, String text) {
        Date newTime = new Date();
        String needWriteFile = logfile.format(newTime);
        String needWriteMessage = myLogSdf.format(newTime) + "    " + myLogType
                + "    " + tag + "    " + text;
        File dir = new File(sDirs);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(sDirs, needWriteFile
                + MYLOGFILEName);
        try {
            //后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            FileWriter filerWriter = new FileWriter(file, true);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除制定的日志文件
     */
    public static void delFile() {// 删除日志文件
        String needDelFile = logfile.format(getDateBefore());
        File file = new File(sDirs, needDelFile + MYLOGFILEName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     */
    private static Date getDateBefore() {
        Date newTime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(newTime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }

}