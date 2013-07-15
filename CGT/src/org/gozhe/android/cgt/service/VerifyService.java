package org.gozhe.android.cgt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gozhe.android.cgt.entity.Verify;
import org.gozhe.android.cgt.service.dao.VerifyDao;


import android.content.Context;

public class VerifyService {

	Context context;

	public VerifyService(Context context) {
		this.context = context;
	}

	public List<HashMap<String, Object>> getVerifyData() {
		
		List<HashMap<String, Object>> messageListData = new ArrayList<HashMap<String, Object>>();
		VerifyDao dao = new VerifyDao(this.context);
		List<Verify> messages = dao.getPageData(1, 20);
		HashMap<String, Object> message;
		for (Verify sm : messages) {
			message = new HashMap<String, Object>();
			message.put("id", String.valueOf(sm.getId()));
			message.put("title", sm.getVerifyId());
			message.put("describe", sm.getDescribe());
			message.put("time", sm.getAddress());
			messageListData.add(message);
		}
		return messageListData;
	}
}
