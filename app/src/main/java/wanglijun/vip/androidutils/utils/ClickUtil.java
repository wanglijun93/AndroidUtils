package wanglijun.vip.androidutils.utils;



/**
 * @author wlj
 * @date 2017/3/28
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description 点击处理方法，防止二次点击
 */
public class ClickUtil {

    // 上次点击时间
    private static long sLastTime;

    /**
     * 判断此次点击是否响应
     *
     * @return 响应则返回true，否则返回false
     */
    public static boolean isClick() {

        long time = TimeUtils.getCurTimeMills();
        if (sLastTime > time || time - sLastTime > 500) {
            synchronized (ClickUtil.class) {
                if (sLastTime > time || time - sLastTime > 500) {
                    sLastTime = time;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
