package org.gozhe.android.core.tools;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	@SuppressLint("SimpleDateFormat")
	public String getNowDate(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		return sdf.format(new Date());		
	}
}
