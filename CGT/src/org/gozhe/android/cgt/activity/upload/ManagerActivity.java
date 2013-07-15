package org.gozhe.android.cgt.activity.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gozhe.android.R;
import org.gozhe.android.cgt.view.ProgressAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ManagerActivity extends Activity {

	private List<Map<String, Object>> mData;

	ProgressAdapter adapter;
	ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.up_manager);

		listview = (ListView) findViewById(R.id.up_listview);

		mData = getData();

		adapter = new ProgressAdapter(this);
		adapter.mData = mData;

		listview.setAdapter(adapter);

	}

	private List<Map<String, Object>> getData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", "部件问题上传");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map.put("title", "部件问题上传");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map.put("title", "部件问题上传");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map.put("title", "部件问题上传");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map.put("title", "部件问题上传");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map.put("title", "部件问题上传");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		
		return list;
	}

}
