package org.gozhe.android.cgt.activity;


import org.gozhe.android.R;
import org.gozhe.android.cgt.utils.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

public class StartingActivity  extends Activity{

	//延迟3秒 
    private static final long SPLASH_DELAY_MILLIS = 5000;
	// 使用Handler的postDelayed方法，3秒后执行跳转到	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
		
		setContentView(R.layout.starting);
				
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				checkState();
				
				goLoginPage();
			}
		},SPLASH_DELAY_MILLIS);
	}
	
	private void checkState(){
		boolean network = Utility.isNetworkAvaiable(StartingActivity.this);
		boolean sdcard = Utility.isSdcardAvailable();
		if(!network){
			showInfo("网络好像不可用哦!");
		}
		if(!sdcard){
			showInfo("SD卡监测异常!为保证软件正常使用,请插入sdcard!");
		}
	}
	
	private void showInfo(String infos){
		Toast.makeText(this, infos, Toast.LENGTH_SHORT).show();
	}
	
	private void goLoginPage() {
        Intent intent = new Intent(StartingActivity.this, LoginActivity.class);
        StartingActivity.this.startActivity(intent);
        StartingActivity.this.finish();
    }
}
