package org.gozhe.android.cgt.utils;

import java.io.File;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

public class Utility {

	/**
	 * 获取手机IMEI码
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	/**
	 * 判断SD卡是否存在并可读写
	 * 
	 * @return
	 */
	public static boolean isSdcardAvailable() {
		boolean result = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			if (Environment.getExternalStorageDirectory().canWrite()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * SD卡总空间(MIB单位)
	 * 
	 * @return
	 */
	public static long getSdcardAllSize() {
		if (isSdcardAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs statfs = new StatFs(path.getPath());
			long blockSize = statfs.getBlockSize();// 获取block的SIZE
			long totalBlocks = statfs.getBlockCount();// 获取BLOCK数量
			long availableBlock = statfs.getAvailableBlocks();// 空闲的Block的数量

			return totalBlocks * blockSize / (1024 * 1024);// MiB
			// (availableBlocks * blockSize)/1024 KIB 单位
			// (availableBlocks * blockSize)/1024 /1024 MIB单位
		} else {
			return 0;
		}
	}

	/**
	 * SD卡可用空间(MIB单位)
	 * 
	 * @return
	 */
	public static long getSdcardAvaiableSize() {

		if (isSdcardAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs statfs = new StatFs(path.getPath());
			long blockSize = statfs.getBlockSize();// 获取block的SIZE
			long totalBlocks = statfs.getBlockCount();// 获取BLOCK数量
			long availableBlock = statfs.getAvailableBlocks();// 空闲的Block的数量

			return availableBlock * blockSize / (1024 * 1024);// MiB
			// (availableBlocks * blockSize)/1024 KIB 单位
			// (availableBlocks * blockSize)/1024 /1024 MIB单位
		} else {
			return 0;
		}
	}

	/**
	 * 获取应用程序路径
	 * 
	 * @param app
	 * @return
	 */
	public static String getAppPath() {

		if (isSdcardAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			String sdcardPath = path.getPath();
			return sdcardPath + "/CGT";
		} else {
			return "@";
		}
	}

	/**
	 * 获取应用程序相关照片录音文件路径
	 * 
	 * @param app
	 * @return
	 */
	public static String getUpDataPath() {

		File files = new File(getAppPath() + "/UpData");
		if (!files.exists()) {
			files.mkdirs();
		}
		return getAppPath() + "/UpData";
	}

	/**
	 * 判断网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvaiable(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		NetworkInfo.State state = info.getState();
		if (state == NetworkInfo.State.CONNECTED)
			return true;
		else
			return false;
	}
}
