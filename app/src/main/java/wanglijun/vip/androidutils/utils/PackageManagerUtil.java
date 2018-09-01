package wanglijun.vip.androidutils.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import java.util.List;



/**
 * @author wlj
 * @date 2018/09/01
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description PackageManager工具类
 */
public class PackageManagerUtil {


    public static List<ResolveInfo> queryIntentActivities(Context context, Intent intent) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().queryIntentActivities(intent, 0);
        }
    }

    public static PackageManager getPackageManager(Context context) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager();
        }
    }

    public static PackageInfo getPackageInfo(Context context, String packageName, int flags) throws PackageManager.NameNotFoundException {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getPackageInfo(packageName, flags);
        }
    }

    public static Resources getResourcesForApplication(Context context, String packageName) throws PackageManager.NameNotFoundException {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getResourcesForApplication(packageName);
        }
    }

    public static List<PackageInfo> getInstalledPackages(Context context, int flags) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getInstalledPackages(flags);
        }
    }

    public static Intent getLaunchIntentForPackage(Context context, String packageName) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getLaunchIntentForPackage(packageName);
        }
    }

    public static PackageInfo getPackageArchiveInfo(Context context, String archiveFilePath, int flags) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getPackageArchiveInfo(archiveFilePath, flags);
        }
    }

    public static ApplicationInfo getApplicationInfo(Context context, String packageName, int flags) throws PackageManager.NameNotFoundException {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getApplicationInfo(packageName, flags);
        }
    }

    public static ResolveInfo resolveActivity(Context context, Intent intent, int flags) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().resolveActivity(
                    intent, flags);
        }
    }

    public static boolean hasSystemFeature(Context context, String name) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().hasSystemFeature(name);
        }
    }

    public static void clearPackagePreferredActivities(Context context, String packageName) {
        synchronized (PackageManagerUtil.class) {
            context.getPackageManager().clearPackagePreferredActivities(packageName);
        }
    }

    public static void setComponentEnabledSetting(Context context, ComponentName componentName,
                                                  int newState, int flags) {
        synchronized (PackageManagerUtil.class) {
            context.getPackageManager().setComponentEnabledSetting(componentName,
                    newState,
                    flags
            );
        }
    }

    public static List<ResolveInfo> queryIntentServices(Context context, Intent intent, int flags) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().queryIntentServices(intent, flags);
        }
    }

    public static CharSequence getApplicationLabel(Context context, ApplicationInfo info) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getApplicationLabel(info);
        }
    }

    public static List<ProviderInfo> queryContentProviders(Context context, String processName, int uid, int flags) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().queryContentProviders(processName, uid, flags);
        }
    }

    public static
    @Nullable
    String[] getPackagesForUid(Context context, int uid) {
        synchronized (PackageManagerUtil.class) {
            return context.getPackageManager().getPackagesForUid(uid);
        }
    }
}
