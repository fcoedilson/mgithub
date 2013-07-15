package org.gozhe.android.cgt.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gozhe.android.cgt.entity.User;
import org.gozhe.android.cgt.service.DBHelper;
import org.gozhe.android.core.db.EntityDao;


import android.content.Context;
import android.database.Cursor;

public class UserDao implements EntityDao<User, Integer> {

	public static final String TABLE = "T_USER";

	public static final String ID = "_ID";
	public static final String USERID = "USERID";
	public static final String USERNAME = "USERNAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String CNNAME = "CNNAME";
	public static final String EMPID = "EMPID";
	public static final String TEL = "TEL";
	public static final String DUTYGRID = "DUTYGRID";
	public static final String REMARKS = "REMARKS";
	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE + "(" + ID + "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ USERID + "VARCHAR(50) NOT NULL ," + USERNAME
			+ "VARCHAR(50) NOT NULL ," + PASSWORD + "VARCHAR(50) NOT NULL ,"
			+ CNNAME + "VARCHAR(50) NOT NULL ," + EMPID + "VARCHAR(50)," + TEL
			+ "VARCHAR(50)," + DUTYGRID + "VARCHAR(50) NOT NULL ," + REMARKS
			+ "VARCHAR(300))";

	DBHelper dbHelper;

	public UserDao(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void save(User entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb().execSQL(
				"insert into " + TABLE + "(" + USERID + "," + USERNAME + ","
						+ PASSWORD + "," + CNNAME + "," + EMPID + "," + TEL
						+ "," + DUTYGRID + "," + REMARKS + ")"
						+ "values(?,?,?,?,?,?,?,?)",
				new Object[] { entity.getUserId(), entity.getUsername(),
						entity.getPassword(), entity.getCnName(),
						entity.getEmpId(), entity.getTel(),
						entity.getDutyGrid(), entity.getRemarks() });
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
	public void upDate(User entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb()
				.execSQL(
						"update "
								+ TABLE
								+ " set USERID=?, USERNAME=?, PASSWORD=? ,CNNAME=?,EMPID=?,TEL=?,DUTYGRID=?,REMARKS=? where id =?",
						new Object[] { entity.getUserId(),
								entity.getUsername(), entity.getPassword(),
								entity.getCnName(), entity.getEmpId(),
								entity.getTel(), entity.getDutyGrid(),
								entity.getRemarks(), entity.getId() });
	}

	@Override
	public User find(Integer id) {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " where id=?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new User(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4), cursor.getString(5),
					cursor.getString(6), cursor.getString(7),
					cursor.getString(8));
		} else {
			return null;
		}
	}

	@Override
	public List<User> getPageData(Integer startResult, Integer maxResult) {
		// TODO Auto-generated method stub
		List<User> messages = new ArrayList<User>(0);
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " limit ?, ?",
				new String[] { String.valueOf(startResult),
						String.valueOf(maxResult) });
		// TODO Auto-generated method stub
		while (cursor.moveToNext()) {
			messages.add(new User(cursor.getInt(0), cursor.getString(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getString(6), cursor
							.getString(7), cursor.getString(8)));
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
