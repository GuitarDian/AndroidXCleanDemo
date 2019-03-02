package com.dian.mycleandemo.presentation.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by XTER on 2016/9/20.
 * Toast统一管理类
 */
public class ToastUtil {
	private static Toast toast;
	private static Handler mHandler = new Handler(Looper.getMainLooper());

	public static Handler getHandler(){
		return mHandler;
	}

	/**
	 * 短时间显示Toast
	 *
	 * @param context 上下文
	 * @param message 消息内容
	 */
	public static void showShort(final Context context, final CharSequence message) {
		mHandler.post(new Runnable() {
			@Override
			public synchronized void run() {
				if (null == toast) {
					toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
				} else {
					toast.setText(message);
				}
				toast.show();
			}
		});
	}

	/**
	 * 短时间显示Toast
	 *
	 * @param context 上下文
	 * @param message 消息内容ID
	 */
	public static void showShort(final Context context, final int message) {
		mHandler.post(new Runnable() {
			@Override
			public synchronized void run() {
				if (null == toast) {
					toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
				} else {
					toast.setText(message);
				}
				toast.show();
			}
		});

	}

	/**
	 * 长时间显示Toast
	 *
	 * @param context 上下文
	 * @param message 消息内容
	 */
	public static void showLong(final Context context, final CharSequence message) {
		mHandler.post(new Runnable() {
			@Override
			public synchronized void run() {
				if (null == toast) {
					toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
				} else {
					toast.setText(message);
				}
				toast.show();
			}
		});

	}

	/**
	 * 长时间显示Toast
	 *
	 * @param context 上下文
	 * @param message 消息内容ID
	 */
	public static void showLong(final Context context, final int message) {
		mHandler.post(new Runnable() {
			@Override
			public synchronized void run() {
				if (null == toast) {
					toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
				} else {
					toast.setText(message);
				}
				toast.show();
			}
		});
	}
}
