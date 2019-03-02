package com.dian.mycleandemo.data.source.local.db;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Dian on 2018/9/19.
 * 配置存储管理--SharedPreferenceManager
 */
public class SPM {

	public static void saveBoolean(Context context, String name, String key, boolean value) {
		try {
			SharedPreferences sp = context.getSharedPreferences(name, 0);
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean(key, value);
			editor.apply();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void saveInt(Context context, String name, String key, int value) {
		try {
			SharedPreferences sp = context.getSharedPreferences(name, 0);
			SharedPreferences.Editor editor = sp.edit();
			editor.putInt(key, value);
			editor.apply();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void saveStr(Context context, String name, String key, String value) {
		try {
			SharedPreferences sp = context.getSharedPreferences(name, 0);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString(key, value);
			editor.apply();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean getBoolean(Context context, String name, String key, boolean defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		return sp != null && sp.getBoolean(key, defaultValue);
	}

	public static int getInt(Context context, String name, String key, int defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		if (sp != null) {
			return sp.getInt(key, defaultValue);
		}
		return -1;
	}

	public static String getStr(Context context, String name, String key, String defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		if (sp != null) {
			return sp.getString(key, defaultValue);
		}
		return "";
	}

	/**
	 * 清除配置表
	 *
	 * @param name 配置文件名
	 */
	public static void removeSP(Context context, String name) {
		SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		sp.edit().clear().apply();
	}

}
