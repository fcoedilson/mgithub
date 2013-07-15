package org.gozhe.android.cgt.activity.notice;


import org.gozhe.android.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class NoticeActivity extends TabActivity implements
		OnCheckedChangeListener {

	private RadioGroup mainTab;
	private TabHost mTabHost;

	// 内容Intent
	private Intent mIntent1;
	private Intent mIntent2;

	private final static String TAB_TAG_1 = "tag_notice";
	private final static String TAB_TAG_2 = "tag_handvoer";

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
				
		setContentView(R.layout.notice);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_b);
		
	    TextView tv_title=(TextView)findViewById(R.id.tv_title_info);
	    
	    tv_title.setText("今日提示");
	    


		mainTab = (RadioGroup) findViewById(R.id.notice_tab);
		mainTab.setOnCheckedChangeListener(this);

		mIntent1 = new Intent(this, TodayNoticeActivity.class);
		mIntent2 = new Intent(this, HandoverActivity.class);

		mTabHost = getTabHost();

		mTabHost.addTab(mTabHost
				.newTabSpec(TAB_TAG_1)
				.setIndicator("今日提示",
						getResources().getDrawable(R.drawable.icon_1_n))
				.setContent(mIntent1));

		mTabHost.addTab(mTabHost
				.newTabSpec(TAB_TAG_2)
				.setIndicator("交接记录",
						getResources().getDrawable(R.drawable.icon_2_n))
				.setContent(mIntent2));

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.notice_radio_button0:
			this.mTabHost.setCurrentTabByTag(TAB_TAG_1);
			break;
		case R.id.notice_radio_button1:
			this.mTabHost.setCurrentTabByTag(TAB_TAG_2);
			break;
		}
	}
}
