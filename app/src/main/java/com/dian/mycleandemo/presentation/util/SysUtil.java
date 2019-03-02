package com.dian.mycleandemo.presentation.util;


import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dian.mycleandemo.presentation.base.BaseApp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by XTER on 2016/9/20.
 * 系统相关
 */
public class SysUtil {

	/**
	 * 得到当前日期
	 *
	 * @return time
	 */
	public static String getDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return sdf.format(d);
	}

	/**
	 * 得到转换日期
	 *
	 * @param time 数
	 * @return time
	 */
	public static String getDate(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return sdf.format(time);
	}

	/**
	 * 得到当前时间
	 *
	 * @return time
	 */
	public static String getNow() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return sdf.format(d);
	}

	/**
	 * 得到当前时间
	 *
	 * @return time
	 */
	public static String getNow2() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
		return sdf.format(d);
	}

	/**
	 * 网络可用？
	 *
	 * @return bool
	 */
	public static boolean isInternetConnection() {
		boolean isConnected;
		ConnectivityManager connectivityManager =
				(ConnectivityManager) BaseApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

		return isConnected;
	}

	/**
	 * 得到当前时间
	 *
	 * @return time
	 */
	public static String getNow3() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
		return sdf.format(d);
	}

	/**
	 * 得到转换时间
	 *
	 * @param time 数
	 * @return time
	 */
	public static String getTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return sdf.format(time);
	}

	/**
	 * 得到转换时间
	 *
	 * @param time 数
	 * @return time
	 */
	public static String getTime2(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
		return sdf.format(time);
	}

	/**
	 * 判断是否在规定时间内
	 * @param beginTime 起始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static Boolean timeIsBelong(String beginTime, String endTime){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
		Date now =null;
		Date bTime = null;
		Date eTime = null;
		try {
			now = df.parse(df.format(new Date()));
			bTime = df.parse(beginTime);
			eTime = df.parse(endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Boolean flag = belongCalendar(now, bTime, eTime);
		return flag;
	}

	private static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 获取系统几月几日星期几
	 * @return
	 */
	public static String getSystemData(){
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if("1".equals(mWay)){
			mWay ="天";
		}else if("2".equals(mWay)){
			mWay ="一";
		}else if("3".equals(mWay)){
			mWay ="二";
		}else if("4".equals(mWay)){
			mWay ="三";
		}else if("5".equals(mWay)){
			mWay ="四";
		}else if("6".equals(mWay)){
			mWay ="五";
		}else if("7".equals(mWay)){
			mWay ="六";
		}
		return mMonth + "月" + mDay+"日   "+"星期"+mWay;
	}

	/**
	 * 将String转换为utf-8
	 */
	public static String getUTF8XMLString(String xml) {
		// A StringBuffer Object
		StringBuffer sb = new StringBuffer();
		sb.append(xml);
		String xmString = "";
		String xmlUTF8 = "";
		try {
			xmString = new String(sb.toString().getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlUTF8;
	}

	/**
	 * 判断服务是否开启
	 * @param context
	 * @param ServiceName 服务的全名称全路径的.比如："com.example.demo.service.myservice".
	 * @return
	 */
	public static boolean isServiceRunning(Context context, String ServiceName) {
		if (("").equals(ServiceName) || ServiceName == null)
			return false;
		ActivityManager myManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
				.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString()
					.equals(ServiceName)) {
				return true;
			}
		}
		return false;
	}

	/* 获得状态栏高度 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/* 获得状态栏高度 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static int getStatusBarHeight2(Context context) {
		int result = 0;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			result = context.getResources().getDimensionPixelSize(height);
		} catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | InstantiationException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* 获取操作拦高度 */
	public static int getActionBarHeight(Context context) {
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

	/* 得到系统栏高度 */
	public static int getSystemBarHeight(Context context) {
		return getActionBarHeight(context) + getStatusBarHeight(context);
	}

	/* 得到系统栏（包括状态栏和操作栏）参数 */
	public static LinearLayout.LayoutParams getSystemBarParam(Context context) {
		int occupyHeight = getActionBarHeight(context) + getStatusBarHeight(context);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, occupyHeight);
		return layoutParams;
	}

	/**
	 * 判断系统中是否存在可以启动的相机应用
	 *
	 * @return 存在返回true，不存在返回false
	 */
	public static boolean hasCamera(Context context) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	public static int getLargerScreenSide(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return Math.max(dm.widthPixels, dm.heightPixels);
	}

	public static int getShorterScreenSide(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return Math.min(dm.widthPixels, dm.heightPixels);
	}

	public static int getShorterScreenSideToHight(Context context) {
		return Math.round(getShorterScreenSide(context) * getScreenRatio(context));
	}

	public static float getScreenRatio(Context context) {
		return (float) getShorterScreenSide(context) / getLargerScreenSide(context);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 *
	 * @param dpValue dp单位
	 * @return px单位
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 *
	 * @param pxValue pxValue px单位
	 * @return dp单位
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue sp单位
	 * @return px单位
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue px单位
	 * @return sp单位
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 退出
	 */
	public static void exit() {
		//获取PID
		android.os.Process.killProcess(android.os.Process.myPid());
		//常规java、c#的标准退出法，返回值为0代表正常退出
		System.exit(0);
	}

	//获取appSHA1值
	public static String getSHA1(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), PackageManager.GET_SIGNATURES);
			byte[] cert = info.signatures[0].toByteArray();
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] publicKey = md.digest(cert);
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < publicKey.length; i++) {
				String appendString = Integer.toHexString(0xFF & publicKey[i])
						.toUpperCase(Locale.US);
				if (appendString.length() == 1)
					hexString.append("0");
				hexString.append(appendString);
				hexString.append(":");
			}
			String result = hexString.toString();
			return result.substring(0, result.length()-1);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
