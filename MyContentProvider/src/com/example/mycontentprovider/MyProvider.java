package com.example.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MyProvider extends ContentProvider {
	/**
	 * 应用程序使用ContentResolver对象访问ContentProvider提供的数据，他提供
	 * 'CRUD'--Create（创建），retreieve(恢复)，update(更新)和delete(删除)功能
	 * 这些功能都可以作用于持久化存储之上。
	 * 客户进程的ContentResolver对象与内容提供继承的ContentProvider对象之间会自动处理进程间通信,
	 * 比如像线程间同步问题就uxuyao开发者管理，ContentProvider在数据外观与数据容器之间扮演着一个抽象层的角色 。
	 * 注意点：为了访问ContentProvider
	 * ,应用通常需要在Androidmanifest.xml文件中加入一些与ContentProvider有关的权限
	 * 
	 */
	private SQLiteOpenHelper dbopenhelpHelper;
	public static final String TABLE_NAME = "t_user";

	/**
	 * 该方法在contentprovider创建后会被调用，当其他应用程序第一次访问Contentprovider时，
	 * 该contentprovider会被创建出来，并 立即回调该oncreate()方法。
	 * 
	 */

	@Override
	public boolean onCreate() {
		/** 创建数据库 */
		// 调用时只创建一次
		Log.i("aa", "MyProvider-onCreate");
		dbopenhelpHelper = new DatabaseHelper(getContext());
		return true;
	}

	/**
	 * 根据Uri查询出select条件所匹配的全部记录，其中projection就是一个列名列表，表明只选择出指定的数据列表，
	 * 表明只选择出指定指定的数据列
	 * 
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.i("aa", "MyProvider-query");
		Log.i("aa", "" + projection[0]);
		SQLiteDatabase db = dbopenhelpHelper.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, projection, null, null, null, null,
				null);
		// db.close();
		// Log.i("aa", c.getColumnName(1) + "---------" + c.getInt(0));

		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub

		return "17073";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		/**
		 * 插入语句的用法是将数据库的插入API语句插入一条获得ID，然后将这个ID封装到URi中
		 * 
		 */
		Log.i("aa", "MyProvider-insert");
		SQLiteDatabase db = dbopenhelpHelper.getWritableDatabase();
		long rowid = db.insert(TABLE_NAME, null, values);
		Log.v("TAG", "insert table t_user ok");
		if (rowid > 0) {
			/**
			 * 重新构建一个URI对象，加入uri是content rowID是45，则重新构建的URI是content
			 * ://contacts/people/45
			 * 
			 */
			//content://org.crazyit.providers.firstprovider/1
			Uri insertedUri = ContentUris.withAppendedId(uri, rowid);// 封装ID
			getContext().getContentResolver().notifyChange(insertedUri, null);// 更新URI
			return insertedUri;// 返回URI
		}
		db.close();
		throw new SQLException("Failed to insert row into " + uri);

	}


	
//"_ID = 4 (!TextUtils.isEmpty(selection) ? "AND(" + selection
//		+ ')' : "")
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.i("aa", "MyProvider-delete");
		//music/zhangxinzhe/ 1
		String rowId = uri.getPathSegments().get(0);
		SQLiteDatabase db = dbopenhelpHelper.getReadableDatabase();
		int count = db.delete(TABLE_NAME,
				"_ID ="
						+ rowId
						+ (!TextUtils.isEmpty(selection) ? "AND(" + selection
								+ ')' : ""), selectionArgs);
		
//		"_ID = 1 AND "" "
		// db.close();
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] selectionArgs) {
		Log.i("aa", "MyProvider-update");
		String rowId = uri.getPathSegments().get(0);
		SQLiteDatabase db = dbopenhelpHelper.getReadableDatabase();
		int count = db.update(TABLE_NAME, values,
				"_ID="
						+ rowId
						+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')'
								: ""), selectionArgs);
		db.close();
		return count;
	}

	private class DatabaseHelper extends SQLiteOpenHelper {

		private static final String TAG = "TAG";
		private static final String DATABASE_NAME = "SQLDB";
		private static final int DATABASE_VERSION = 1;

		public DatabaseHelper(Context context) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.i("aa", "DatabaseHelper-DatabaseHelper");
			// 创建数据库，库名以及数据库的版本
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE t_user("
					+ "_ID integer PRIMARY KEY autoincrement," + "NAME text"
					+ ");");
			Log.i("aa", "DatabaseHelper-onCreate");
			Log.i(TAG, "Create Table t_user ok");
			// 创建表t_user这个表
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS t_user");
			Log.i("aa", "DatabaseHelper-onUpgrade");
			onCreate(db);
		}

	}

}
