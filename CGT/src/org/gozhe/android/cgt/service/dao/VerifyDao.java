package org.gozhe.android.cgt.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gozhe.android.cgt.entity.Verify;
import org.gozhe.android.cgt.service.DBHelper;
import org.gozhe.android.core.db.EntityDao;

import android.content.Context;
import android.database.Cursor;

public class VerifyDao implements EntityDao<Verify, Integer> {

	public static final String TABLE = "T_VERIFY";

	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE + "(" + "_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
			+ "VERIFYID VARCHAR(50) ," + "DESCRIBE VARCHAR(200) ,"
			+ "ADDRESS VARCHAR(200) ," + "USERID VARCHAR(50) ,"
			+ "IFVERIFY INTEGER ," + "IFPROBLEM INTEGER )";

	DBHelper dbHelper;

	public VerifyDao(Context context) {
		dbHelper = new DBHelper(context);
		
		dbHelper.getDb().execSQL(DATABASE_CREATE);
	}

	@Override
	public void save(Verify entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb()
				.execSQL(
						"insert into "
								+ TABLE
								+ "(VERIFYID,DESCRIBE,ADDRESS,USERID,IFVERIFY,IFPROBLEM) values(?,?,?,?,?,?)",
						new Object[] {
								entity.getVerifyId(), entity.getDescribe(),
								entity.getAddress(), entity.getUserId(),
								entity.getIfVerify(), entity.getIfProblem() });
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
	public void upDate(Verify entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb()
				.execSQL(
						"update "
								+ TABLE
								+ " set VERIFYID=?, DESCRIBE=?,ADDRESS=?,USERID=?,IFVERIFY=?,IFPROBLEM=? where id =?",
						new Object[] { entity.getVerifyId(),
								entity.getDescribe(), entity.getAddress(),
								entity.getUserId(), entity.getIfVerify(),
								entity.getIfProblem(), entity.getId() });
	}

	@Override
	public Verify find(Integer id) {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " where id=?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new Verify(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
		} else {
			return null;
		}
	}

	@Override
	public List<Verify> getPageData(Integer startResult, Integer maxResult) {
		// TODO Auto-generated method stub
		List<Verify> messages = new ArrayList<Verify>(0);
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " limit ?, ?",
				new String[] { String.valueOf(startResult),
						String.valueOf(maxResult) });
		// TODO Auto-generated method stub
		while (cursor.moveToNext()) {
			messages.add(new Verify(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getInt(5), cursor.getInt(6)));
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
