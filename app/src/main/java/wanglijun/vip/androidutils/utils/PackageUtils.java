package wanglijun.vip.androidutils.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 包的相关操作
 */

public class PackageUtils {
    /**
     * Check whether a particular package has been granted a particular permission.
     * 检查包是否授予权限
     *
     * @param context
     * @param permName
     * @param pkgName
     * @return
     */
    public static boolean checkPermission(Context context, String permName, String pkgName) {
        PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permName, pkgName));
        return permission;
    }

    /**
     * Check whether a particular package has been granted a particular permission.
     * 检查包是否授予权限
     *
     * @param context
     * @param permName
     * @return
     */
    public static boolean checkPermission(Context context, String permName) {
        int perm = context.checkCallingOrSelfPermission(permName);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Install package
     * 安装apk
     *
     * @param context
     * @param filePath
     * @return
     */
    public static boolean install(Context context, String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file == null || !file.exists() || !file.isFile() || file.length() <= 0) {
            return false;
        }

        i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        return true;
    }

    /**
     * Uninstall package
     * 卸载apk
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean uninstall(Context context, String packageName) {
        if (packageName == null || packageName.length() == 0) {
            return false;
        }

        Intent i = new Intent(Intent.ACTION_DELETE, Uri.parse(new StringBuilder(32).append("package:")
                .append(packageName).toString()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        return true;
    }

    /**
     * Judge whether packageName is a system application
     * 判断是否是一个系统的应用软件
     *
     * @param context
     * @return
     */
    public static boolean isSystemApplication(Context context) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context, context.getPackageName());
    }

    /**
     * Judge whether packageName is a system application
     * 判断是否是一个系统的应用软件
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isSystemApplication(Context context, String packageName) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context.getPackageManager(), packageName);
    }

    /**
     * Judge whether packageName is a system application
     * 判断是否是一个系统的应用软件
     *
     * @param packageManager
     * @param packageName
     * @return
     */
    public static boolean isSystemApplication(PackageManager packageManager, String packageName) {
        if (packageManager == null || packageName == null || packageName.length() == 0) {
            return false;
        }
        try {
            ApplicationInfo app = packageManager.getApplicationInfo(packageName, 0);
            return (app != null && (app.flags & ApplicationInfo.FLAG_SYSTEM) > 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get installed package infos
     * 获取安装包信息
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getInsatalledPackageInfos(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }

    /**
     * Judge whether the packageName is installed
     * 判断是否安装了软件
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInsatalled(Context context, String packageName) {
        if (!StringUtils.isEmpty(packageName)) {
            List<PackageInfo> list = getInsatalledPackageInfos(context);
            if (!CollectionUtils.isEmpty(list)) {
                for (PackageInfo pi : list) {
                    if (packageName.equalsIgnoreCase(pi.packageName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Get all apps
     * 获取所有的APP
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getAllApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packageInfos.size(); i++) {
            PackageInfo pak = packageInfos.get(i);
            if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                apps.add(pak);
            }
        }
        return apps;
    }

    /**
     * Start app by packageName
     * 通过包名启动应用程序
     *
     * @param context
     * @param packageName
     */
    public static void startApp(Context context, String packageName) {
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            String pkgName = resolveinfo.activityInfo.packageName;
            String className = resolveinfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(pkgName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }
}
