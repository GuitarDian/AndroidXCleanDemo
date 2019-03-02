package com.dian.mycleandemo.presentation.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by XTER on 2017/11/2.
 * 文件工具--带rx
 */
public class FileUtil {
	/**
	 * 删除文件或目录
	 *
	 * @param path 文件或目录。
	 * @return true 表示删除成功，否则为失败
	 */
	synchronized public static boolean delete(File path) {
		if (null == path) {
			return true;
		}

		if (path.isDirectory()) {
			File[] files = path.listFiles();
			if (null != files) {
				for (File file : files) {
					if (!delete(file)) {
						return false;
					}
				}
			}
		}
		return !path.exists() || path.delete();
	}

	public synchronized static void createFile(String path) throws IOException {
		if (TextUtils.isEmpty(path)) {
			return;
		}
		File file = new File(path);
		if (!file.exists())
			if (path.endsWith("/")) {
				file.mkdirs();
			} else {
				if (file.getParentFile().exists()) {
					file.createNewFile();
				} else {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
			}
	}

	/**
	 * 向文件中写入内容
	 *
	 * @param filepath 文件路径与名称
	 * @param newstr   写入的内容
	 */
	public static void writeFileContent(String filepath, String newstr) throws IOException {
		PrintWriter pw = null;
		try {
			FileWriter fw = new FileWriter(filepath, true);
			pw = new PrintWriter(fw);
			pw.println(newstr);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile(filepath);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public static void writeLogFile(String newstr) throws IOException {
		String logFileName = Environment.getExternalStorageDirectory() + "/jvxs/logs/log_" + SysUtil.getDate() + ".txt";
//		File logFile = new File(logFileName);
//		if(logFile.exists()&&logFile.length()>200*1024*1024){
//			logFileName =
//		}
		PrintWriter pw = null;
		try {
			FileWriter fw = new FileWriter(logFileName, true);
			pw = new PrintWriter(fw);
			pw.println(newstr);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile(logFileName);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public static void writeToFile(String content, String filePath) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath, true);
			fileWriter.write(content);
			fileWriter.flush();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
