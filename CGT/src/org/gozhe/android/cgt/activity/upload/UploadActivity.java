package org.gozhe.android.cgt.activity.upload;

import org.gozhe.android.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class UploadActivity extends TabActivity {
	
	private RadioGroup mainTab;
	private TabHost mTabHost;
	
	private TextView tv_title;
	
	private Intent mIntent1;
	private Intent mIntent2;
	private Intent mIntent3;
	private Intent mIntent4;

	private final static String TAB_TAG_1 = "tag_basicinfo";
	private final static String TAB_TAG_2 = "tag_photos";
	private final static String TAB_TAG_3 = "tag_records";
	private final static String TAB_TAG_4 = "tag_manager";
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(R.layout.upload_tab);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_b);
		
	    tv_title=(TextView)findViewById(R.id.tv_title_info);
		
		mainTab = (RadioGroup) findViewById(R.id.main_tab);
		
		mainTab.setOnCheckedChangeListener(this.tabCheckedChangedListener);
	
		prepareIntent();
		setupIntent();
	}
	
	/**
	 * 准备tab的内容Intent
	 */
	private void prepareIntent() {
		mIntent1 = new Intent(this, AddQuestionAActivity.class);
		mIntent2 = new Intent(this, AddQuestionBActivity.class);
		mIntent3 = new Intent(this, AddQuestionCActivity.class);
		mIntent4 = new Intent(this, ManagerActivity.class);
	}

	@SuppressWarnings("deprecation")
	private void setupIntent() {
		this.mTabHost = getTabHost();
		
		TabHost localTabHost = this.mTabHost;
		localTabHost.addTab(buildTabSpec(TAB_TAG_1, R.string.task_tb_basic,
				R.drawable.icon_1_n, mIntent1));
		localTabHost.addTab(buildTabSpec(TAB_TAG_2, R.string.task_tb_photo,
				R.drawable.icon_2_n, mIntent2));
		localTabHost.addTab(buildTabSpec(TAB_TAG_3, R.string.task_tb_record,
				R.drawable.icon_3_n, mIntent3));
		localTabHost.addTab(buildTabSpec(TAB_TAG_4, R.string.task_tb_manager,
				R.drawable.icon_4_n, mIntent4));
		
		RadioButton radiu0 =(RadioButton)findViewById(R.id.radio_button0);
		radiu0.setChecked(true);
		tv_title.setText("问题上报-基本信息-①");
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,final Intent content) {
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
				getResources().getDrawable(resIcon)).setContent(content);
	}

	private OnCheckedChangeListener tabCheckedChangedListener= new  OnCheckedChangeListener(){
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch(checkedId){
			case R.id.radio_button0:
				mTabHost.setCurrentTabByTag(TAB_TAG_1);
				tv_title.setText("问题上报-基本信息-①");
				break;
			case R.id.radio_button1:
				mTabHost.setCurrentTabByTag(TAB_TAG_2);
				tv_title.setText("问题上报-现场照片-②");
				break;
			case R.id.radio_button2:
				mTabHost.setCurrentTabByTag(TAB_TAG_3);
				tv_title.setText("问题上报-现场录音-③");
				break;
			case R.id.radio_button3:
				mTabHost.setCurrentTabByTag(TAB_TAG_4);
				tv_title.setText("问题上报-上传管理-④");
				break;
			}
		}
		
	};
}
