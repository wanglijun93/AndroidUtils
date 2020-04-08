package wanglijun.vip.androidutils.utils;

import java.util.Objects;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 对象操作
 */

public class ObjectUtils {
    /**
     * Returns true if a and b are equal.
     * 如果a和b相等，返回TRUE
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Object a, Object b) {
        return a == b || (Objects.equals(a, b));
    }
}
