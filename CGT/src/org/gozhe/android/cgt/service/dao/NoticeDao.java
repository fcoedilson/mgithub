package org.gozhe.android.cgt.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gozhe.android.cgt.entity.Notice;
import org.gozhe.android.cgt.service.DBHelper;
import org.gozhe.android.core.db.EntityDao;


import android.content.Context;
import android.database.Cursor;

public class NoticeDao implements EntityDao<Notice, Integer> {

	public static final String TABLE = "T_NOTICE";
	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE + "(" + "_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
			+ "NOTICRDATE VARCHAR(50) ," + "CONTENT VARCHAR(400))";

	DBHelper dbHelper;

	public NoticeDao(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void save(Notice entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb().execSQL(
				"insert into " + TABLE + "(NOTICEDATE,CONTENT) values(?,?)",
				new Object[] { entity.getNoticeDate(), entity.getContent() });
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
	public void upDate(Notice entity) {
		// TODO Auto-generated method stub
		dbHelper.getDb().execSQL(
				"update " + TABLE + " set NOTICEDATE=?, CONTENT=? where id =?",
				new Object[] { entity.getNoticeDate(), entity.getContent(),
						entity.getId() });
	}

	@Override
	public Notice find(Integer id) {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " where id=?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new Notice(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2));
		} else {
			return null;
		}
	}

	@Override
	public List<Notice> getPageData(Integer startResult, Integer maxResult) {
		// TODO Auto-generated method stub
		List<Notice> messages = new ArrayList<Notice>(0);
		Cursor cursor = dbHelper.getDb().rawQuery(
				"select * from " + TABLE + " limit ?, ?",
				new String[] { String.valueOf(startResult),
						String.valueOf(maxResult) });
		// TODO Auto-generated method stub
		while (cursor.moveToNext()) {
			messages.add(new Notice(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2)));
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
