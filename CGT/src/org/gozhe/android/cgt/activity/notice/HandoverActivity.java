package org.gozhe.android.cgt.activity.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gozhe.android.R;
import org.gozhe.android.cgt.entity.Handover;
import org.gozhe.android.cgt.service.dao.HandoverDao;
import org.gozhe.android.cgt.view.TableAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class HandoverActivity extends Activity {

	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.workhandover);

		listview = (ListView) findViewById(R.id.thlistview);

		LoadDate();

	}

	private void LoadDate() {

		List<HashMap<String, Object>> messageListData = new ArrayList<HashMap<String, Object>>();
		// 查询数据
		HandoverDao dao = new HandoverDao(this);
		
//		for(int i=0;i<20;i++){
//			Handover handover = new Handover();
//			handover.setCnName("尹新东");
//			handover.setLoginTime("2013-07-15 09:02");
//			handover.setLogoutTime("2013-07-15 18:02");
//			handover.setSignout(1);
//			handover.setUserId("root");
//			handover.setRemarks("测试登录注销及交接管理");
//			
//			dao.save(handover);
//		}
		
		List<Handover> messages = dao.getPageData(1, 5);
		// 添加数据
		HashMap<String, Object> message;
		for (Handover sm : messages) {
			message = new HashMap<String, Object>();
			message.put("userid", sm.getUserId());
			message.put("name", sm.getCnName());
			message.put("time", sm.getLogoutTime());
			message.put("remark", sm.getRemarks());
			messageListData.add(message);
		}

		TableAdapter adapter = new TableAdapter(this, messageListData,
				R.layout.workhandover_item, new String[] { "name", "time",
						"remark" }, new int[] { R.id.handover_name,
						R.id.handover_time, R.id.handover_remarks });
		
		listview.setAdapter(adapter);
	}

}
