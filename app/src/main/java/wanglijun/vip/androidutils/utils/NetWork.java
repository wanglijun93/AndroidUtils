package wanglijun.vip.androidutils.utils;

import wanglijun.vip.androidutils.Application;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 判断手机网络类型，是否连接
 */

public class NetWork {
    public static void internal() {
        int networkType = NetWorkUtils.getNetworkType(Application.context);
        String networkTypeName = NetWorkUtils.getNetworkTypeName(Application.context);
        LogUtil.d("-----网络名字-----", networkTypeName);
        LogUtil.d("----网络类型-----", networkType + "");
        if (networkTypeName.equals(NetWorkUtils.NETWORK_TYPE_WIFI)) {
            ToastUtil.showToast("你目前处于wifi网络");
        } else if (networkTypeName.equals(NetWorkUtils.NETWORK_TYPE_DISCONNECT)) {
            ToastUtil.showToast("你目前处于断网状态");
        } else if (networkTypeName.equals(NetWorkUtils.NETWORK_TYPE_3G)) {
            ToastUtil.showToast("你目前处于3G状态");
        } else if (networkTypeName.equals(NetWorkUtils.NETWORK_TYPE_2G)) {
            ToastUtil.showToast("你目前处于2G网络");
        } else if (networkTypeName.equals(NetWorkUtils.NETWORK_TYPE_WAP)) {
            ToastUtil.showToast("你目前处于企业网");
        } else if (networkTypeName.equals(NetWorkUtils.NETWORK_TYPE_UNKNOWN)) {
            ToastUtil.showToast("你目前网络类型不知道");
        }
    }
}
