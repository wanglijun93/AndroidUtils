package wanglijun.vip.androidutils.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 集合操作
 */

public class CollectionUtils {
    private static final String DELIMITER = ",";

    /**
     * 判断集合是否为空
     *
     * @param c
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(Collection<V> c) {
        return (c == null || c.size() == 0);
    }

    /**
     * Join collection to string, separator is {@link #DELIMITER}
     *
     * @param tokens
     * @return
     */
    public static String join(Iterable tokens) {
        return tokens == null ? "" : TextUtils.join(DELIMITER, tokens);
    }

    /**
     * 将数组转为list
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T... array) {
        return Arrays.asList(array);
    }

    /**
     * 将数组转为set集合
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> Set<T> arrayToSet(T... array) {
        return new HashSet<T>(arrayToList(array));
    }

    /**
     * 集合转为数组
     *
     * @param c
     * @return
     */
    public static Object[] listToArray(Collection<?> c) {
        if (!isEmpty(c)) {
            return c.toArray();
        }
        return null;
    }

    /**
     * list转为set
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Set<T> listToSet(List<T> list) {
        return new HashSet<T>(list);
    }

    /**
     * Convert set to list
     * set转为list
     *
     * @param set
     * @param <T>
     * @return
     */
    public static <T> List<T> setToList(Set<T> set) {
        return new ArrayList<T>(set);
    }

    /**
     * Traverse collection
     * 遍历集合
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String traverseCollection(Collection<T> c) {
        if (!isEmpty(c)) {
            int len = c.size();
            StringBuilder builder = new StringBuilder(len);
            int i = 0;
            for (T t : c) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString());
                i++;
                if (i < len) {
                    builder.append(DELIMITER);
                }
            }
            return builder.toString();
        }
        return null;
    }

}
