package wanglijun.vip.androidutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * * @author wlj
 * @date 2017/3/28
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * ----------------------------------------------------------------------------
 * 类名：Android开发之常用必备工具类图片bitmap转成字符串string与String字符串转换为bitmap图片格式
 * ----------------------------------------------------------------------------
 * 功能描述：Android开发之常用必备工具类图片bitmap转成字符串string与String字符串转换为bitmap图片格式
 * ----------------------------------------------------------------------------
 */
public class BitmapAndStringUtils {
    /**
     * 图片转成string （byte）
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        LogUtil.d("---byte", appicon + "");
        int length = appicon.length;
        System.out.println("压缩前" + length);
//        return appicon;
        return Base64.encodeToString(appicon, Base64.DEFAULT);
    }
    /**
     * 图片转成（byte）
     *
     * @param bitmap
     * @return
     */
    public static byte[] convertIconToByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        LogUtil.d("---byte", appicon + "");
        int length = appicon.length;
        System.out.println("压缩前" + length);
        return appicon;
//        return Base64.encodeToString(appicon, Base64.DEFAULT);
    }
    /**
     * string转成bitmap
     *
     * @param st
     */
    public static Bitmap convertStringToIcon(String st) {
        // OutputStream out;
        Bitmap bitmap = null;
        try {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * (byte)转成bitmap
     *
     * @param st
     */
    public static Bitmap convertByteToIcon(byte[] st) {
        // OutputStream out;
        Bitmap bitmap = null;
        try {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            String s = Base64.encodeToString(st, Base64.DEFAULT);
            byte[] bitmapArray;
            bitmapArray = Base64.decode(s, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 转为byte数组
     *
     * @param bm
     * @return
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /***
     * 压缩GZip
     *
     * @param data
     * @return
     */
    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
            int length = b.length;
            System.out.println("压缩后" + length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 解压GZip
     *
     * @param data
     * @return
     */
    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

}
