package org.gozhe.android.cgt.activity;

import org.gozhe.android.R;
import org.gozhe.android.cgt.activity.download.MyHistoryActivity;
import org.gozhe.android.cgt.activity.download.MyTaskActivity;
import org.gozhe.android.cgt.activity.gps.MapActivity1;
import org.gozhe.android.cgt.activity.notice.NoticeActivity;
import org.gozhe.android.cgt.activity.upload.UploadActivity;
import org.gozhe.android.cgt.view.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	private GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);   
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(R.layout.main);
		
		//titlebar为自己标题栏的布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_b); 

		gridView = (GridView) findViewById(R.id.main_gridview);

		Integer[] images = { R.drawable.image_main_m1, R.drawable.image_main_m2, R.drawable.image_main_m3,
				R.drawable.image_main_m4, R.drawable.image_main_m5, R.drawable.image_main_m6, R.drawable.image_main_m7,
				R.drawable.image_main_m8, R.drawable.image_main_m9, R.drawable.image_main_m10, R.drawable.m11,
				R.drawable.image_main_m12 };

		String[] texts = { "问题上报", "我的任务", "历史记录", "今日提示", "GPS地图", "部件采集",
				"单健拨号", "短信群呼", "数据同步", "系统设置", "使用帮助", "系统签退" };

		gridView.setAdapter(new ImageAdapter(this, images, texts));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					gotoIntent(UploadActivity.class);
					break;
				case 1:
					gotoIntent(MyTaskActivity.class);
					break;
				case 2:
					gotoIntent(MyHistoryActivity.class);
					break;
				case 3:
					gotoIntent(NoticeActivity.class);
					break;
				case 4:
					gotoIntent(MapActivity1.class);
					break;
				case 5:

					break;
				case 6:
					callPhone("18616342456");
					break;
				case 7:

					break;
				case 8:

					break;
				}
			}
		});
	}

	private void gotoIntent(Class<?> cs) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, cs);
		startActivity(intent);
	}
	
	private void callPhone(String mobilenum){
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+mobilenum));
		startActivity(intent);	
	}
}
