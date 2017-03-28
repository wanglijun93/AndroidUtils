package wanglijun.vip.androidutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wlj
 * @date 2017/3/28
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description：把一个文件夹中的所有文件拷贝到一个文件夹
 * 
 * 
 */
public class FileHelper {
	private File to;// 目标文件夹
	private static File src;// 源文件夹
	private static Context context;
	private static FileHelper helper;

	private FileHelper() {
	}

	public static FileHelper getInstance(Context c) {
		if (helper == null) {
			helper = new FileHelper();
			FileHelper.context = c;
		}
		return helper;
	}

	public void copyAToB(String a , String b){
		System.out.println("From:" + a);
		System.out.println("From:" + b);


	}
	public void copyAssetToSD() throws IOException{
		//		InputStream open1 = context.getResources().getAssets().open("html"+File.separator+"table.html");
		//		copyAToB(open1, createFile("table.html").toString());
		InputStream open2 = context.getAssets().open("html"+File.separator+"image002.png");
		copyAToB(open2, createFile("image002.png").toString());

	}
	public File createFile(String fileName) throws IOException{
		File fileTo = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {// sd可以读写
			fileTo = new File(Environment.getExternalStorageDirectory()
					+ File.separator +context.getApplicationInfo().packageName+File.separator+ "html");

		} else {
			fileTo = new File(context.getExternalCacheDir() + File.separator
					+context.getApplicationInfo().packageName+File.separator+"html");
		}

		if(fileTo.exists()){
			fileTo.delete();
		}
		fileTo.mkdirs();
		File f = new File(fileTo.getAbsolutePath()+File.separator+fileName);
		if(f.exists()){
			f.delete();
		}
		f.createNewFile();
		System.out.println(f.toString());
		return f;
	}
	public static boolean appIsInstalled(Context context, String pageName) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(pageName, 0);
			System.out.println("已经安装成功" + packageInfo.packageName);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	/**
	 * 把Asset下的apk拷贝到sdcard下 /Android/data/你的包名/cache 目录下
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static File getAssetFileToCacheDir(Context context, String fileName) {
		try {
			File cacheDir = getCacheDir(context);
			final String cachePath = cacheDir.getAbsolutePath()
					+ File.separator + fileName;
			InputStream is = context.getAssets().open(fileName);
			File file = new File(cachePath);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] temp = new byte[1024];

			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			fos.close();
			is.close();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 复制文件
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (!oldfile.exists()) { //文件不存在时
				InputStream inStream = new FileInputStream(oldPath); //读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ( (byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		}
		catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * .获取sdcard中的缓存目录
	 * 
	 * @param context
	 * @return
	 */
	public static File getCacheDir(Context context) {
		String APP_DIR_NAME = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Android/data/";
		File dir = new File(APP_DIR_NAME + context.getPackageName() + "/cache/");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	public void copyAToB(InputStream is , String b) throws IOException{
		System.out.println("From: 文件流");
		System.out.println("to:"+b);

		FileOutputStream out = new FileOutputStream(b, false);
		byte[] buff = new byte[2048];
		int len = 0;
		while ((len = is.read(buff)) != -1) {
			out.write(buff, 0, len);
			out.flush();
		}
		is.close();
		out.close();
	}

	/**
	 * Save Bitmap to a file.保存图片到SD卡。
	 * 
	 * @param bitmap
	 * @return error message if the saving is failed. null if the saving is
	 *         successful.
	 * @throws IOException
	 */
	public static File saveBitmapToFile(Bitmap bitmap, String _file)
			throws IOException {
		BufferedOutputStream os = null;
		try {
			File file = new File(_file);
			int end = _file.lastIndexOf(File.separator);
			String _filePath = _file.substring(0, end);
			File filePath = new File(_filePath);
			if(!filePath.exists()){
				filePath.mkdirs();
			}
			File[] files = filePath.listFiles();
			for(File listFile : files){
				if(listFile != null && listFile.exists()){
					listFile.delete();
				}
			}
			file.createNewFile();
			os = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
			return file;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public void saveFile2SD(String filePath,String content){
		String sdStatus = Environment.getExternalStorageState();  
		if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  
			return;  
		}  
		try {  
			File file = new File(filePath);
			int end = filePath.lastIndexOf(File.separator);
			String _filePath = filePath.substring(0, end);
			File path = new File(_filePath);
			if(!path.exists()){
				path.mkdirs();
			}
			if(!file.exists()) {
				file.createNewFile();  
			}  
			FileOutputStream stream = new FileOutputStream(file);  
			byte[] buf = content.getBytes();  
			stream.write(buf);            
			stream.close();  

		} catch(Exception e) {  
			e.printStackTrace();  
		}  
	}

	public String buildHtmlString(String jsonBuilder) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><meta name=\"viewport\" content=\"width=650, target-densitydpi=medium-dpi\"><meta charset=\"UTF-8\"></head><body>");
		sb.append("<table border=1 cellpadding=0 cellspacing=0 width=650  style='border-collapse:collapse;table-layout:fixed;width:529pt'>");
		sb.append("<tr height=19 style='height:14.25pt'> ");
		sb.append("<td colspan=8 rowspan=4 height=76 width=650 style='height:57.0pt;width:529pt'align=center valign=center vertical=\"middle\"><span><img width=157 height=38 src='file://"+Environment.getExternalStorageDirectory()+ File.separator +context.getApplicationInfo().packageName+File.separator+ "html"+File.separator+"image002.png"+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;湖南空港实业股份有限公司地面服务清单（）</span></td>");
		sb.append("</tr>");

		sb.append("<tr height=19 style='height:14.25pt'></tr>");
		sb.append("<tr height=19 style='height:14.25pt'></tr>");
		sb.append("<tr height=19 style='height:14.25pt'></tr>");
		sb.append("<tr height=38 style='height:28.50pt'></tr>");

		sb.append("<tr height=19 style='height:14.25pt'>");
		sb.append("<td colspan=2 height=19 class=xl67 style='height:14.25pt' >单据编号：<span class =printdocment >1223</span></td>");
		sb.append("<td colspan=2 class=xl67 >日期：<span id=\"printData\" class =printdocment><font class=\"font5\">2015</font><font class=\"font7\"></font><font class=\"font5\">年 3 月 5 日l;</font></span></td>");
		sb.append("<td class=xl67 colspan=2 style='mso-ignore:colspan'>航空公司：<span id=\"printComputer\" class =printdocment><font class=\"font5\" >南方航空</font></span></td>");
		sb.append("<td colspan=2 class=xl88>停机位：<span id=\"printPosition\" class =printdocment>120</span></td>");
		sb.append("</tr>");

		sb.append("<tr height=19 style='height:14.25pt'></tr>");

		sb.append("<tr height=19 style='height:14.25pt'>");
		sb.append("<td colspan=2 height=19 class=xl67 style='height:14.25pt'>机型：<span id=\"printModel\" class =printdocment><font class=\"font5\">B767（宽体）</font></span></td>");
		sb.append("<td colspan=2 class=xl67>航班号：<span id=\"printFlightNumber\" class =printdocment><font class=\"font5\"  >CZ1234</font></td>");
		sb.append("<td class=xl67 colspan=2 style='mso-ignore:colspan'>任务类型：<span id =\"printTaskType\" class =printdocment></span></td>");
		sb.append("<td colspan=2 class=xl67>机号:<span id = \"printMachineNo\" class =printdocment></span></td>");
		sb.append("</tr>");

		sb.append("<tr height=38 style='height:28.50pt'></tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=2 rowspan=3 height=63 class=xl81 style='height:47.85pt'>飞机服务</td>");
		sb.append("<td colspan=3 class=xl85 style='border-left:none'>1、基本收费（过站清洁服务）</td>");
		sb.append("<td colspan=2  style='border-left:none' align=\"center\" id = \"printBasiCharge\" class =printdocment>　</td>");
		sb.append("<td class=xl73 style='border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl85 style='height:15.95pt;border-left:none'>2、航前服务（清洁服务）</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\"  id = \"printPreService\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl87 style='height:15.95pt;border-left:none'>3、航后服务（清洁服务）</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id=\"printNavService\">　</td>");
		sb.append("<td class=xl74 style='border-top:none;border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=2 rowspan=6 height=126 class=xl81 style='height:95.7pt'>飞机勤务</td>");
		sb.append("<td colspan=3 class=xl85 style='border-left:none'>1、一般勤务</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id=\"printGeneralService\">　</td>");
		sb.append("<td class=xl73 style='border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl85 style='height:15.95pt;border-left:none'>2、例行检查（过站）</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id = \"printRoutineInspection\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl85 style='height:15.95pt;border-left:none'>3、飞机放行（过站）</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id=\"printAircraftRelease\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl85 style='height:15.95pt;border-left:none'>4、例行检查放行航前服务</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id = \"printPreRoutineInspection\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl85 style='height:15.95pt;border-left:none'>5、例行检查放行航后服务</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id = \"printNavRoutineInspection\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>架次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl85 style='height:15.95pt;border-left:none'>6、非例行检查</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id=\"printRoutineInspection\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>工时</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=2 rowspan=18 height=378 class=xl83 width=192 style='height:287.1pt;width:144pt'>站坪服务和飞机勤务</br>使用的设备情况</td>");
		sb.append("<td colspan=3 class=xl86 style='border-left:none'>1、引导车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id= \"printCarGuide\">　</td>");
		sb.append("<td class=xl75 style='border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>2、牵引车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\"id= \"printCarTow\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>3、客梯车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align=\"center\" id= \"printCarAirstairs\">　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>小时</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>4、旅客摆渡车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarPassenger'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>5、机组摆渡车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarCrew'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>6、升降平台车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarLift'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>小时</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>7、残疾人专用车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarDisabled'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>8、电源车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarPower'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>小时</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>9、气源车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarGasource'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>10、空调车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarAir'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>小时</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>11、清水车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarClear'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>12、污水车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarDirt'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>13、垃圾车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCarRubbish'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>14、除冰车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printCar'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>小时</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>15、除冰液</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printFluid'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>升</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>16、VIP用车</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printVip'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>台次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>17、充氮气</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printFilling'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>次</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=3 height=21 class=xl86 style='height:15.95pt;border-left:none'>18、其他</td>");
		sb.append("<td colspan=2 class =printdocment style='border-left:none' align='center' id= 'printOther'>　</td>");
		sb.append("<td class=xl73 style='border-top:none;border-left:none'>　</td>");
		sb.append("</tr>");


		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'>");
		sb.append("<td colspan=2 rowspan=3 height=147 class=xl84 width=192 style='height:111.65pt;width:144pt'>其他需要说明的事项</td>");
		sb.append("<td colspan=6 rowspan=3 class =printdocment id= 'printNotice'>　</td>");
		sb.append("</tr>");

		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'></tr>");
		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'></tr>");
		sb.append("<tr height=21 style='mso-height-source:userset;height:15.95pt'></tr>");

		sb.append("<tr height=38 style='height:28.50pt'>");

		sb.append("<td height=19 class=xl78 colspan=2 style='height:14.25pt;mso-ignore:colspan'>服务方签字：</td>");
		sb.append("<td class =printdocment colspan=2 align='center' id= 'printServiceSignature'>　</td>");
		sb.append("<td class=xl78 colspan=2 style='mso-ignore:colspan'>航空公司代表签字：</td>");
		sb.append("<td class =printdocment colspan=2 align='center' id= 'printAirSignature'>　</td>");
		sb.append("</tr>");
		sb.append("</table>");

		//TODO 
		sb.append("<p> <b>电子签名：</b></p>");
		sb.append("<img width=\"108\" src=\"@image_assets\"/>");
		sb.append("<p></p><p></p>");
		sb.append("<p> <b> 签字人头像：</b></p>");
		sb.append("<img width=\"108\" src=\"@image_file\"/>");

		sb.append(" <script type='text/javascript' > ");
		sb.append("window.onload = function(){var data = "+jsonBuilder+"; var element = document.getElementsByClassName(\"printdocment\");for(var i =0;i<element.length;i++){if (typeof(data[i]) == \"undefined\")element[i].innerHTML= \"    \";else element[i].innerHTML= data[i];};}");
		sb.append("</script> </body></html>");
		return sb.toString();
	}

	public void writeFileToSD(String s) {  
		String sdStatus = Environment.getExternalStorageState();  
		if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  
			return;  
		}  
		try {  
			String pathName="/sdcard/test/";  
			String fileName="file.txt";  
			File path = new File(pathName);  
			File file = new File(pathName + fileName);  
			if( !path.exists()) {  
				path.mkdir();  
			}  
			if( !file.exists()) {  
				file.createNewFile();  
			}  
			FileOutputStream stream = new FileOutputStream(file);  
			byte[] buf = s.getBytes();  
			stream.write(buf);            
			stream.close();  

		} catch(Exception e) {  
			e.printStackTrace();  
		}  
	}


	public static String GetResponseMysign(Map<String, String> inputPara, String privateKey){
		String fullstring = GetPostStrings(inputPara, "_sign") + privateKey;
		return ToMD5(fullstring);
	}
	private static String GetPostStrings(Map<String, String> inputPara, String excepted){
		Map<String, String> sPara = new HashMap<>();

		//过滤空值、sign与sign_type参数
		if(inputPara != null) {
		    sPara = inputPara;
		}

		//获得签名结果
		StringBuilder prestr = new StringBuilder();
		for (String s : sPara.values()) {
			prestr.append(s.toLowerCase());
		}


		//去掉最後一個&字符
		/*int nLen = prestr.length();
		if (nLen > 1)
			prestr.delete(nLen - 1, 1);*/
		return prestr.toString();
	}

	public static String ToMD5(String string) {
		byte[] hash = new byte[0];
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) {
				hex.append("0");
			}
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}



}
