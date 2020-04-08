package wanglijun.vip.androidutils.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 服务开启与关闭
 */

public class ServiceUtils {
    /**
     * Judge whether a service is running
     *
     * @param context
     * @param className
     * @return
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfos = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : serviceInfos) {
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    /**
     * Stop running service
     *
     * @param context
     * @param className
     * @return
     */
    public static boolean stopRunningService(Context context, String className) {
        Intent intentService = null;
        boolean ret = false;
        try {
            intentService = new Intent(context, Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intentService != null) {
            ret = context.stopService(intentService);
        }
        return ret;
    }
}
