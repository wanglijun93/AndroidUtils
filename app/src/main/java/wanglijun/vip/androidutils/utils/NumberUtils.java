package wanglijun.vip.androidutils.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 数字格式化操作类
 */

public class NumberUtils {
    /**
     * 保留一位小数
     *
     * @param number
     * @return
     */
    public static String formatOneDecimal(float number) {
        DecimalFormat oneDec = new DecimalFormat("##0.0");
        return oneDec.format(number);
    }

    /**
     * 保留两位小数
     *
     * @param number
     * @return
     */
    public static String formatTwoDecimal(float number) {
        DecimalFormat twoDec = new DecimalFormat("##0.00");
        return twoDec.format(number);
    }

    /**
     * 保留两位小数百分比
     *
     * @param number
     * @return
     */
    public static String formatTwoDecimalPercent(float number) {
        return formatTwoDecimal(number) + "%";
    }

    /**
     * 四舍五入
     *
     * @param number
     * @param scale  scale of the result returned.
     * @return
     */
    public static double roundingNumber(float number, int scale) {
        return roundingNumber(number, scale, RoundingMode.HALF_UP);
    }

    /**
     * 四舍五入
     *
     * @param number
     * @param scale        scale of the result returned.
     * @param roundingMode rounding mode to be used to round the result.
     * @return
     */
    public static double roundingNumber(float number, int scale, RoundingMode roundingMode) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(scale, roundingMode).doubleValue();
    }

}
