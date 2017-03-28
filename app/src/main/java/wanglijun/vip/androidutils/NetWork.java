package wanglijun.vip.androidutils;

/**
 * @author wlj
 * @date 2017/3/28
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description 判断手机网络类型 是否连接
 */
public   class NetWork {

    public static void internal() {
        int networkType = NetworkUtils.getNetworkType(AirportApplication.context);
        String networkTypeName = NetworkUtils.getNetworkTypeName(AirportApplication.context);
        LogUtil.d("-----网络名字-----", networkTypeName);
        LogUtil.d("----网络类型-----", networkType + "");
        if (networkTypeName.equals(NetworkUtils.NETWORK_TYPE_WIFI)) {
            ToastUtil.showToast("你目前处于wifi网络");
        } else if (networkTypeName.equals(NetworkUtils.NETWORK_TYPE_DISCONNECT)) {
            ToastUtil.showToast("你目前处于断网状态");
        } else if (networkTypeName.equals(NetworkUtils.NETWORK_TYPE_3G)) {
            ToastUtil.showToast("你目前处于3G状态");
        } else if (networkTypeName.equals(NetworkUtils.NETWORK_TYPE_2G)) {
            ToastUtil.showToast("你目前处于2G网络");
        } else if (networkTypeName.equals(NetworkUtils.NETWORK_TYPE_WAP)) {
            ToastUtil.showToast("你目前处于企业网");
        } else if (networkTypeName.equals(NetworkUtils.NETWORK_TYPE_UNKNOWN)) {
            ToastUtil.showToast("你目前网络类型不知道");
        }
    }
}
