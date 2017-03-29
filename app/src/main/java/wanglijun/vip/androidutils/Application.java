package wanglijun.vip.androidutils;


import android.content.Context;
import android.os.Handler;

/**
 * @author wlj
 * @date 2017/3/28
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description 全局上下文
 */
public class Application extends android.app.Application {
    //这是一行没用代码
    public static Context context;
    public static Handler mainHandler;



    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mainHandler = new Handler();
    }


}
