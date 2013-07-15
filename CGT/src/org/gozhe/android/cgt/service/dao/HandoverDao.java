package org.gozhe.android.cgt.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gozhe.android.cgt.entity.Handover;
import org.gozhe.android.cgt.service.DBHelper;
import org.gozhe.android.core.db.EntityDao;

import android.content.Context;
import android.database.Cursor;

public class HandoverDao implements EntityDao<Handover, Integer> {

	public static final String TABLE = "T_HANDOVER";
	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE + "(" + "_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
			+ "USERID VARCHAR(50) ," + "CNNAME VARCHAR(50) ,"
			+ "LOGINTIME VARCHAR(50) ," + "LOGOUTTIME VARCHAR(50) ,"
			+ "SIGNOUT INTEGER ," + "REMARKS VARCHAR(400))";

	DBHelper dbHelper;

	public HandoverDao(Context context) {
		dbHelper = new DBHelper(context);
		
		dbHelper.getDb().execSQL(DATABASE_CREATE);
	}

	@Override
	public void save(Handover entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb().execSQL(
						"insert into "
								+ TABLE
								+ "(USERID,CNNAME,LOGINTIME,LOGOUTTIME,SIGNOUT,REMARKS) values(?,?,?,?,?,?)",
						new Object[] { entity.getUserId(), entity.getCnName(),
								entity.getLoginTime(), entity.getLogoutTime(),
								entity.getSignout(), entity.getRemarks() });
	}

	@Override
	public void remove(Integer... ids) {
		// TODO Auto-generated method stub
		if (ids.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (Integer id : ids) {
				sb.append("?").append(",");
			}
			sb.deleteCharAt(sb.length() - 1);

			dbHelper.getDb().execSQL(
					"delete from " + TABLE + " where id in(" + sb + ")",
					(Object[]) ids);
		}
	}

	@Override
	public void upDate(Handover entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb()
				.execSQL(
						"update "
								+ TABLE
								+ " set USERID=?, CNNAME=?,LOGINTIME=?,LOGOUTTIME=?,SIGNOUT=?,REMARKS=? where id =?",
						new Object[] { entity.getUserId(), entity.getCnName(),
								entity.getLoginTime(), entity.getLogoutTime(),
								entity.getSignout(), entity.getRemarks(),
								entity.getId() });
	}

	@Override
	public Handover find(Integer id) {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " where id=?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new Handover(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4), cursor.getInt(5), cursor.getString(6));
		} else {
			return null;
		}
	}

	@Override
	public List<Handover> getPageData(Integer startResult, Integer maxResult) {
		// TODO Auto-generated method stub
		List<Handover> messages = new ArrayList<Handover>(0);
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " limit ?, ?",
				new String[] { String.valueOf(startResult),
						String.valueOf(maxResult) });
		// TODO Auto-generated method stub
		while (cursor.moveToNext()) {
			messages.add(new Handover(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getInt(5), cursor
							.getString(6)));
		}
		return messages;
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select count(*) from " + TABLE, null);
		if (cursor.moveToNext())
			return cursor.getLong(0);
		return 0l;
	}

}
