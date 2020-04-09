package wanglijun.vip.androidutils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间相关
 *
 * @author wlj
 */
public class TimeUtils {

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * <p>在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.</p>
     * 格式的意义如下： 日期和时间模式 <br>
     * <p>日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
     * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
     * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
     * </p>
     * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
     * <table border="1" cellspacing="1" cellpadding="1" summary="Chart shows pattern letters, date/time component,
     * presentation, and examples.">
     * <tr>
     * <th align="left">字母</th>
     * <th align="left">日期或时间元素</th>
     * <th align="left">表示</th>
     * <th align="left">示例</th>
     * </tr>
     * <tr>
     * <td><code>G</code></td>
     * <td>Era 标志符</td>
     * <td>Text</td>
     * <td><code>AD</code></td>
     * </tr>
     * <tr>
     * <td><code>y</code> </td>
     * <td>年 </td>
     * <td>Year </td>
     * <td><code>1996</code>; <code>96</code> </td>
     * </tr>
     * <tr>
     * <td><code>M</code> </td>
     * <td>年中的月份 </td>
     * <td>Month </td>
     * <td><code>July</code>; <code>Jul</code>; <code>07</code> </td>
     * </tr>
     * <tr>
     * <td><code>w</code> </td>
     * <td>年中的周数 </td>
     * <td>Number </td>
     * <td><code>27</code> </td>
     * </tr>
     * <tr>
     * <td><code>W</code> </td>
     * <td>月份中的周数 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>D</code> </td>
     * <td>年中的天数 </td>
     * <td>Number </td>
     * <td><code>189</code> </td>
     * </tr>
     * <tr>
     * <td><code>d</code> </td>
     * <td>月份中的天数 </td>
     * <td>Number </td>
     * <td><code>10</code> </td>
     * </tr>
     * <tr>
     * <td><code>F</code> </td>
     * <td>月份中的星期 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>E</code> </td>
     * <td>星期中的天数 </td>
     * <td>Text </td>
     * <td><code>Tuesday</code>; <code>Tue</code> </td>
     * </tr>
     * <tr>
     * <td><code>a</code> </td>
     * <td>Am/pm 标记 </td>
     * <td>Text </td>
     * <td><code>PM</code> </td>
     * </tr>
     * <tr>
     * <td><code>H</code> </td>
     * <td>一天中的小时数（0-23） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr>
     * <td><code>k</code> </td>
     * <td>一天中的小时数（1-24） </td>
     * <td>Number </td>
     * <td><code>24</code> </td>
     * </tr>
     * <tr>
     * <td><code>K</code> </td>
     * <td>am/pm 中的小时数（0-11） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr>
     * <td><code>h</code> </td>
     * <td>am/pm 中的小时数（1-12） </td>
     * <td>Number </td>
     * <td><code>12</code> </td>
     * </tr>
     * <tr>
     * <td><code>m</code> </td>
     * <td>小时中的分钟数 </td>
     * <td>Number </td>
     * <td><code>30</code> </td>
     * </tr>
     * <tr>
     * <td><code>s</code> </td>
     * <td>分钟中的秒数 </td>
     * <td>Number </td>
     * <td><code>55</code> </td>
     * </tr>
     * <tr>
     * <td><code>S</code> </td>
     * <td>毫秒数 </td>
     * <td>Number </td>
     * <td><code>978</code> </td>
     * </tr>
     * <tr>
     * <td><code>z</code> </td>
     * <td>时区 </td>
     * <td>General time zone </td>
     * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code> </td>
     * </tr>
     * <tr>
     * <td><code>Z</code> </td>
     * <td>时区 </td>
     * <td>RFC 822 time zone </td>
     * <td><code>-0800</code> </td>
     * </tr>
     * </table>
     * <pre>
     *                          HH:mm    15:44
     *                         h:mm a    3:44 下午
     *                        HH:mm z    15:44 CST
     *                        HH:mm Z    15:44 +0800
     *                     HH:mm zzzz    15:44 中国标准时间
     *                       HH:mm:ss    15:44:40
     *                     yyyy-MM-dd    2016-08-12
     *               yyyy-MM-dd HH:mm    2016-08-12 15:44
     *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     *                         K:mm a    3:44 下午
     *               EEE, MMM d, ''yy    星期五, 八月 12, '16
     *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     *                  yyMMddHHmmssZ    160812154440+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     * </pre>
     */
    public static ThreadLocal<SimpleDateFormat> DEFAULT_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_MM_DD_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM月dd日", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_M_D_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM月dd日", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_MM_DD_HH_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_MM_DD_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_MM_DD_HH_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_M_D_HH_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-M-d HH:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_YYYY_MM_DD_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_YYYY_MM_DD_HH_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日   HH:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_YYYY_M_D_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年M月d日", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_YYYY_MM_DD_HH_MM_SS_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_YYYY_MM_DD_HH_MM_2_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日HH时mm分", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> CN_YYYY_MM_DD_HH_MM_3_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年M月d日H时m分", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_M_D_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("M/d", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_MM_DD_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_MM_DD_HH_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_MM_DD_HH_SDFS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_BIAS_YYYY_MM_DD_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_BIAS_YYYY_MM_DD_HH_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_H_MM_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("H:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_HH_mm = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> EN_YYYY_M_D_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-M-d", Locale.getDefault());
        }
    };

    public static ThreadLocal<SimpleDateFormat> EN_MM_DD_HH_MM_SDF =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                }
            };

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param milliseconds 毫秒时间戳
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF.get());
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF.get());
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF.get());
    }


    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF.get());
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param milliseconds 毫秒时间戳
     * @return Date类型时间
     */
    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @param unit         <ul>
     *                     <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *                     <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *                     <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *                     <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *                     <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *                     </ul>
     * @return unit时间戳
     */
    private static long milliseconds2Unit(long milliseconds, ConstUtils.TimeUnit unit) {
        switch (unit) {
            case MSEC:
                return milliseconds / ConstUtils.MSEC;
            case SEC:
                return milliseconds / ConstUtils.SEC;
            case MIN:
                return milliseconds / ConstUtils.MIN;
            case HOUR:
                return milliseconds / ConstUtils.HOUR;
            case DAY:
                return milliseconds / ConstUtils.DAY;

        }
        return -1;
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @param unit  <ul>
     *              <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *              <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *              <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *              <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, ConstUtils.TimeUnit unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF.get());
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param unit   <ul>
     *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, ConstUtils.TimeUnit unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2都为Date类型</p>
     *
     * @param time0 Date类型时间1
     * @param time1 Date类型时间2
     * @param unit  <ul>
     *              <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *              <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *              <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *              <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(Date time0, Date time1, ConstUtils.TimeUnit unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
                - date2Milliseconds(time0), unit));
    }

    /**
     * 获取两个毫秒值之间的时间差(天数)
     *
     * @param time1
     * @param time2 一般time2写较大的值
     * @return
     */
    public static int getIntervalTime(long time1, long time2) {
        return (int) ((time2 - time1) / (1000 * 3600 * 24));
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public static Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @param unit <ul>
     *             <li>{@link ConstUtils.TimeUnit#MSEC}:毫秒</li>
     *             <li>{@link ConstUtils.TimeUnit#SEC }:秒</li>
     *             <li>{@link ConstUtils.TimeUnit#MIN }:分</li>
     *             <li>{@link ConstUtils.TimeUnit#HOUR}:小时</li>
     *             <li>{@link ConstUtils.TimeUnit#DAY }:天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, ConstUtils.TimeUnit unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF.get());
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param unit   <ul>
     *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, ConstUtils.TimeUnit unit, SimpleDateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time为Date类型</p>
     *
     * @param time Date类型时间
     * @param unit <ul>
     *             <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *             <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *             <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *             <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *             <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(Date time, ConstUtils.TimeUnit unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate() {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @return 当前日期是星期几
     */
    public static String getWeek() {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    /**
     * 获取当前日期的下一天是星期几<br>
     *
     * @return 当前日期是星期几
     */
    public static String getWeekOfNextDate() {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK);
        if (w == 0) {
            w = 1;
        }
        if (w > 6) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前是年内的第几周
     *
     * @return
     */
    public static int getWeekOfYear() {
        int weekofyear = 0;
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            c.setFirstDayOfWeek(Calendar.MONDAY);/*设置周一为一周的第一天*/
            weekofyear = c.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekofyear;
    }

    /**
     * 获取日历展示信息
     *
     * @return 返回日期+周几
     */
    public static String getCalendar() {
        return TimeUtils.getCurTimeString(TimeUtils.CN_MM_DD_SDF.get()) + " " + TimeUtils.getWeek();
    }

    /**
     * 获取桌面时钟的时间
     * 20180508 因该方法没被调用，且该方法需要 用到application的上下文，所以暂时注释掉
     */
//    public static String getDeskClockTime() {
//        ContentResolver cv = ActApplication.getAppContext().getContentResolver();
//        String strTimeFormat = android.provider.Settings.System.getString(cv,
//                android.provider.Settings.System.TIME_12_24);
//        int hour = "24".equals(strTimeFormat) ? Calendar.HOUR_OF_DAY : Calendar.HOUR;
//        Calendar c = Calendar.getInstance();
////        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        c.setTimeZone(TimeZone.getDefault());
//        String mHuor = String.valueOf(c.get(hour));
//        if (hour == Calendar.HOUR && "0".equals(mHuor)) {
//            mHuor = "12";
//        }
//        String imnute = String.valueOf(c.get(Calendar.MINUTE));
//        if (imnute.length() < 2) {
//            imnute = "0" + imnute;
//        }
//        if (mHuor.length() < 2) {
//            mHuor = "0" + mHuor;
//        }
//        return mHuor + ":" + imnute;
//    }

    /**
     * 获取当前月份是几月
     */
    public static String getCurMonthStr() {

        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        String monthString = null;

        if (month < 10) {

            monthString = 0 + "" + month;
        } else {

            monthString = "" + month;
        }

        /**
         * 月份从0开始计算
         */
        return year + "-" + monthString;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurMonth() {
        int month = 0;
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            month = c.get(Calendar.MONTH) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return month;
    }

    /**
     * 获取上个月月份
     *
     * @return
     */
    public static int getLastMonth() {

        int last = 1;
        int cur = getCurMonth();
        last = cur - 1;
        if (last == 0) {
            last = 12;
        }
        return last;
    }

    /**
     * 获取两个月份差值
     *
     * @param month1
     * @param month2
     * @return
     */
    public static int getDistanceOfTwoMonth(String month1, String month2) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(month1));
        aft.setTime(sdf.parse(month2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;

        return Math.abs(month + result);
    }

    /**
     * 判断旧日期和新日期【新旧根据获取先后而定】的先后关系，如果新日期大于旧日期则返回true
     */
    public static boolean compareDates(int oldYear, int oldMonth, int oldDay,
                                       int newYear, int newMonth, int newDay) {
        if (newYear != oldYear) {
            return !(newYear < oldYear);
        }

        if (newMonth != oldMonth) {
            return !(newMonth < oldMonth);
        }

        if (newDay != oldDay) {
            return !(newDay < oldDay);
        }

        return false;
    }

    /**
     * 获取当前时
     */
    public static int getHour() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间的小时
     */
    public static int getHour(long timeMills) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMills);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 获取当前月的天数
     *
     * @return
     */
    public static int getCurMonthLength() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurDayOFMonth() {
        Calendar c = Calendar.getInstance();
        return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static int getCurDayOFMonthInt() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static String getCurYear() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year + "";
    }

    /**
     * 获取当前是几号
     *
     * @return
     */
    public static int getDay() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static String getYear(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int year = c.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获取距离指定日期指定天数的日期
     *
     * @param current
     * @param diff
     * @return
     */
    public static String getDayByDiff(String current, int diff) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = null;
        try {
            calendar.setTime(format.parse(current));
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + diff);
            result = format.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取三天后的凌晨
     *
     * @return
     */
    public static long getThreeDaysLater() {
        return getTodayZero() + 3 * 24 * 60 * 60 * 1000L;
    }

    /**
     * 今天零点
     */
    public static long getTodayZero() {
        String timeStr = getCurTimeString(EN_YYYY_MM_DD_SDF.get());
        return string2Date(timeStr, EN_YYYY_MM_DD_SDF.get()).getTime();
    }

    /**
     * 明天零点
     *
     * @return
     */
    public static long getTomorrowZero() {
        return getTodayZero() + 24 * 60 * 60 * 1000L;
    }


    /**
     * 获取最近一个周末的日期
     *
     * @param style 返回结果样式 0 day/month   1 month.day
     * @return
     */
    private static String getLatestSunday(int style) {
        String result = "";
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            int mDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            int diff = 0 - (mDayOfWeek - 1);
            if (diff == 0) {
                diff = -7;
            }
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.DAY_OF_MONTH, diff);
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            int mMonth = c.get(Calendar.MONTH) + 1;// 获取mDay所在月份
            if (style == 0) {
                result = mDay + "/" + mMonth;
            } else if (style == 1) {
                result = mMonth + "." + mDay;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取最近一个周末的日期
     *
     * @param diffWeek -1：上一周的周一  -2：上两周的周一 。。。
     * @return
     */
    public static String getLatestSundayDiff(int diffWeek) {
        String result = "";
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            int mDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            int diff = 0 - (mDayOfWeek - 1);
            if (diff == 0) {
                diff = -7;
            }
            diff = diff + (diffWeek * 7 + 1);
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.DAY_OF_MONTH, diff);
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            int mMonth = c.get(Calendar.MONTH) + 1;// 获取mDay所在月份
            result = mMonth + "." + mDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取最近一个周末的日期
     *
     * @return [20170605, 20170610]
     */
    public static String[] getLatestWeekSpan(int diffWeek) {
        String[] result = new String[2];
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            int mDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            int diff = 0 - (mDayOfWeek - 1);
            if (diff == 0) {
                diff = -7;
            }

            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    diff = diff + (diffWeek * 7 + 1);
                } else {
                    diff = diff + 6;
                }
                c.setTime(new Date(System.currentTimeMillis()));
                c.add(Calendar.DAY_OF_MONTH, diff);
                int day = c.get(Calendar.DAY_OF_MONTH);
                String mDay = day + "";
                if (day < 10) {
                    mDay = "0" + day;
                }
                // 获取mDay所在月份
                int month = c.get(Calendar.MONTH) + 1;
                String mMonth = month + "";
                if (month < 10) {
                    mMonth = "0" + month;
                }
                int mYear = c.get(Calendar.YEAR);
                result[i] = mYear + "" + mMonth + "" + mDay;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取最近一周的日期---点分式-周报页面
     *
     * @return
     */
    public static String getLatestSundayDot() {

        String result = "";
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            int mDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//           0 1 2 3 4 5 6
            int diff = 0 - (mDayOfWeek - 1);
            if (diff == 0) {
                diff = -7;
            }
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.DAY_OF_MONTH, diff);
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            // 获取mDay所在月份
            String mMonth = c.get(Calendar.MONTH) + 1 + "";
            if (mMonth.length() == 1) {
                mMonth = 0 + "" + mMonth;
            }
            if (mDay.length() == 1) {
                mDay = 0 + mDay;
            }
            result = mMonth + "." + mDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取最近一月的日期---空格分式-月报页面
     *
     * @return
     */
    public static String getLastMonthDot() {
        String result = "";
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.MONTH, -1);
            // 获取mDay所在月份
            String mMonth = c.get(Calendar.MONTH) + 1 + "";
            String mYear = c.get(Calendar.YEAR) + "";
            if (mMonth.length() == 1) {
                mMonth = 0 + "" + mMonth;
            }
            result = mYear + "年" + mMonth + "月";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取上一月的日期---年、月[0-11]
     *
     * @param interval -1:之前1个月 -2之前两个月 -3：之前三个月。。。以此类推
     * @return
     */
    public static String[] getLastMonthYear(int interval) {
        String[] result = new String[]{"2017", "1"};
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.MONTH, interval);
            String mMonth = c.get(Calendar.MONTH) + "";
            String mYear = c.get(Calendar.YEAR) + "";
            result[0] = mYear;
            result[1] = mMonth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[] getLastMonthRecord(int interval) {
        String[] result = new String[]{"2017", "1"};
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.MONTH, interval);
            String mMonth = (c.get(Calendar.MONTH) + 1) + "";
            String mYear = c.get(Calendar.YEAR) + "";
            result[0] = mYear;
            result[1] = mMonth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据日期获取周几
     *
     * @param date：2017-06-04
     */
    public static String getDayOfWeekByDate(String date) {
        String[] dayOfWeek;
        Calendar calendar;
        String result;
        try {
            dayOfWeek = new String[]{"default", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(date);
            calendar = Calendar.getInstance();
            calendar.setTime(date1);
            result = dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)];
        } catch (ParseException e) {
            result = date;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据日期获取周几
     *
     * @param :geshi：2017-06-04
     * @param date              预报中第一天的日期
     * @param index             第几天
     */
    public static String getDayOfWeekByDate(long date, int index) {
        String[] dayOfWeek;
        Calendar calendar;
        String result;
        try {
            dayOfWeek = new String[]{"default", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
            Date date1 = new Date(date);
            calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(calendar.DATE, index);
            result = dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)];
        } catch (Exception e) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
            calendar = Calendar.getInstance();
            calendar.setTime(new Date(date));
            calendar.add(calendar.DATE, index);
            result = sdf.format(calendar.getTime());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取上周周几的日期,默认一周从周一开始
     *
     * @param dayOfWeek
     * @param weekOffset
     * @return
     */
    public static Date getDayOfWeek(int dayOfWeek, int weekOffset) {
        return getDayOfWeek(Calendar.MONDAY, dayOfWeek, weekOffset);
    }

    /**
     * 获取上(下)周周几的日期
     *
     * @param firstDayOfWeek {@link Calendar}
     *                       值范围 <code>SUNDAY</code>,
     *                       <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
     *                       <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code>
     * @param dayOfWeek      {@link Calendar}
     * @param weekOffset     周偏移，上周为-1，本周为0，下周为1，以此类推
     * @return
     */
    public static Date getDayOfWeek(int firstDayOfWeek, int dayOfWeek, int weekOffset) {
        if (dayOfWeek > Calendar.SATURDAY || dayOfWeek < Calendar.SUNDAY) {
            return null;
        }
        if (firstDayOfWeek > Calendar.SATURDAY || firstDayOfWeek < Calendar.SUNDAY) {
            return null;
        }
        Calendar date = Calendar.getInstance(Locale.CHINA);
        date.setFirstDayOfWeek(firstDayOfWeek);
        //周数减一，即上周
        date.add(Calendar.WEEK_OF_MONTH, weekOffset);
        //日子设为周几
        date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        //时分秒全部置0
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    /**
     * 将毫秒值转化为友好的时间表达格式
     * 今天 14:52
     * 明天 14:52
     * 昨天 14:52
     * 年一样，日期非今天昨天明天，返回08-12 14:52
     * 年不一样，返回2018-08-12 14:52
     */
    public static String milliseconds2FriendlyString(long milliseconds) {
        return date2FriendlyString(new Date(milliseconds));
    }

    /**
     * 将Date类型转化为友好的时间表达格式
     * 今天 14:52
     * 明天 14:52
     * 昨天 14:52
     * 年一样，日期非今天昨天明天，返回08-12 14:52
     * 年不一样，返回2018-08-12 14:52
     */
    public static String date2FriendlyString(Date date) {
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        Calendar currentCalendar = Calendar.getInstance();
        if (currentCalendar.get(Calendar.YEAR) != targetCalendar.get(Calendar.YEAR)) {
            return EN_YYYY_MM_DD_HH_MM_SDF.get().format(date);
        } else {
            int diffDay = currentCalendar.get(Calendar.DAY_OF_YEAR) - targetCalendar.get(
                    Calendar.DAY_OF_YEAR);
            if (diffDay == 1) {
                return "昨天 " + EN_HH_mm.get().format(date);
            } else if (diffDay == 0) {
                return "今天 " + EN_HH_mm.get().format(date);
            } else if (diffDay == -1) {
                return "明天 " + EN_HH_mm.get().format(date);
            } else {
                return EN_MM_DD_HH_MM_SDF.get().format(date);
            }
        }
    }

    /**
     * 将毫秒值转化为友好的时间表达格式
     * 今天 14:52
     * 明天 14:52
     * 昨天 14:52
     * 年一样，日期非今天昨天明天，返回08-12 14:52
     * 年不一样，返回2018-08-12 14:52
     */
    public static String milliseconds2FriendlyStringCN(long milliseconds) {
        return date2FriendlyStringCN(new Date(milliseconds));
    }

    /**
     * 将Date类型转化为友好的时间表达格式
     * 今天 14:52
     * 明天 14:52
     * 昨天 14:52
     * 年一样，日期非今天昨天明天，返回08-12 14:52
     * 年不一样，返回2018-08-12 14:52
     */
    public static String date2FriendlyStringCN(Date date) {
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        Calendar currentCalendar = Calendar.getInstance();
        if (currentCalendar.get(Calendar.YEAR) != targetCalendar.get(Calendar.YEAR)) {
            return EN_YYYY_MM_DD_HH_MM_SDF.get().format(date);
        } else {
            int diffDay = currentCalendar.get(Calendar.DAY_OF_YEAR) -
                    targetCalendar.get(Calendar.DAY_OF_YEAR);
            if (diffDay == 1) {
                return "昨天 " + EN_HH_mm.get().format(date);
            } else if (diffDay == 0) {
                return "今天 " + EN_HH_mm.get().format(date);
            } else if (diffDay == -1) {
                return "明天 " + EN_HH_mm.get().format(date);
            } else {
                return CN_MM_DD_HH_MM_SDF.get().format(date);
            }
        }
    }

}