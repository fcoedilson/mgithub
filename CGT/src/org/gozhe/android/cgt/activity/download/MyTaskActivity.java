package org.gozhe.android.cgt.activity.download;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import org.gozhe.android.R;
import org.gozhe.android.cgt.entity.Handover;
import org.gozhe.android.cgt.entity.Verify;
import org.gozhe.android.cgt.service.VerifyService;
import org.gozhe.android.cgt.service.dao.HandoverDao;
import org.gozhe.android.cgt.service.dao.VerifyDao;
import org.gozhe.android.cgt.view.WeiboListView;
import org.gozhe.android.cgt.view.WeiboListView.OnRefreshListener;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyTaskActivity extends Activity {

	private WeiboListView listView;
	private SimpleAdapter adpater;
	
	private ProgressDialog progressDialog;  
	
	VerifyService verifyserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
				
		setContentView(R.layout.mytask);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_b);
		
	    TextView tv_title=(TextView)findViewById(R.id.tv_title_info);
	    
	    tv_title.setText("任务指令");

		listView = (WeiboListView) findViewById(R.id.task_listView);

		
		//downloadData();
		
		loadData();
		
		listView.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// data.addFirst("刷新后的内容");
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adpater.notifyDataSetChanged();
						listView.onRefreshComplete();
					}
				}.execute();
			}

		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				// Toast.makeText(TaskLoad.this, "OK",
				// Toast.LENGTH_SHORT).show();
				showTaskDialog();
				// showTaskInfos();

//			    Intent intent = new Intent();
//				Bundle bundle = new Bundle();
//				bundle.putString("name", "This is from MainActivity!");
//				intent.putExtras(bundle); // it.putExtra(“test”, "shuju”);
//
//				//intent.setClass(TaskLoadActivity.this, TaskInfoActivity.class);
//				startActivityForResult(intent, 0);

			}

		});
	}

	private void downloadData(){
		
		progressDialog = ProgressDialog.show(getApplicationContext(), "加载……", "正在加载数据，请稍后……");
		
		loadData();
		
		progressDialog.dismiss();
				
		
	}
	
	private void loadData() {
		
		List<HashMap<String, Object>> messageListData = new ArrayList<HashMap<String, Object>>();
		// 查询数据
		VerifyDao dao = new VerifyDao(this);
		
//		for(int i=0;i<30;i++){
//			Verify entity = new Verify();
//			entity.setUserId("001");
//			entity.setVerifyId("201307081200");
//			entity.setDescribe("朝阳区建国门北大街和XX街交叉口处东北角有一下水井盖丢失！联系人XXX，电话XXXXXXX，请前往核查问题。");
//			entity.setAddress("adsdsadsadasasd");
//			entity.setIfProblem(0);
//			entity.setIfVerify(0);
//			dao.save(entity);
//		}
		
		List<Verify> messages = dao.getPageData(1, 20);
		
		// 添加数据
		HashMap<String, Object> message;
		for (Verify sm : messages) {
			message = new HashMap<String, Object>();
			message.put("title", sm.getVerifyId());
			message.put("infos", sm.getDescribe());
			message.put("time", "2013-07-02 14:05:30   指挥中心");
			messageListData.add(message);
		}
		
		adpater = new SimpleAdapter(this, messageListData, R.layout.weibo_item, 
				new String[]{"title","infos","time"}, 
				new int[]{R.id.wb_item_title,R.id.wb_item_infos,R.id.wb_item_time});
		
		listView.setAdapter(adpater);
	}
	


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 0) {
			if (resultCode == Activity.RESULT_OK) {
				SharedPreferences preferences = getSharedPreferences("TaskID",
						0);
				Toast.makeText(MyTaskActivity.this,
						preferences.getString("TaskID", null),
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	private void showTaskDialog() {
		new AlertDialog.Builder(this).setIcon(android.R.drawable.btn_dropdown)
				.setTitle("任务确认").setMessage("a")
				.setPositiveButton("确认", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MyTaskActivity.this, "已完成确认",
								Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				}).setNegativeButton("取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}

				}).show();

	}

}
