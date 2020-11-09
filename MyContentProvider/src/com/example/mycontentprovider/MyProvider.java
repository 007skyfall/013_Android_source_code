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
	 * Ӧ�ó���ʹ��ContentResolver�������ContentProvider�ṩ�����ݣ����ṩ
	 * 'CRUD'--Create����������retreieve(�ָ�)��update(����)��delete(ɾ��)����
	 * ��Щ���ܶ����������ڳ־û��洢֮�ϡ�
	 * �ͻ����̵�ContentResolver�����������ṩ�̳е�ContentProvider����֮����Զ�������̼�ͨ��,
	 * �������̼߳�ͬ�������uxuyao�����߹���ContentProvider�������������������֮�������һ�������Ľ�ɫ ��
	 * ע��㣺Ϊ�˷���ContentProvider
	 * ,Ӧ��ͨ����Ҫ��Androidmanifest.xml�ļ��м���һЩ��ContentProvider�йص�Ȩ��
	 * 
	 */
	private SQLiteOpenHelper dbopenhelpHelper;
	public static final String TABLE_NAME = "t_user";

	/**
	 * �÷�����contentprovider������ᱻ���ã�������Ӧ�ó����һ�η���Contentproviderʱ��
	 * ��contentprovider�ᱻ������������ �����ص���oncreate()������
	 * 
	 */

	@Override
	public boolean onCreate() {
		/** �������ݿ� */
		// ����ʱֻ����һ��
		Log.i("aa", "MyProvider-onCreate");
		dbopenhelpHelper = new DatabaseHelper(getContext());
		return true;
	}

	/**
	 * ����Uri��ѯ��select������ƥ���ȫ����¼������projection����һ�������б�����ֻѡ���ָ���������б�
	 * ����ֻѡ���ָ��ָ����������
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
		 * ���������÷��ǽ����ݿ�Ĳ���API������һ�����ID��Ȼ�����ID��װ��URi��
		 * 
		 */
		Log.i("aa", "MyProvider-insert");
		SQLiteDatabase db = dbopenhelpHelper.getWritableDatabase();
		long rowid = db.insert(TABLE_NAME, null, values);
		Log.v("TAG", "insert table t_user ok");
		if (rowid > 0) {
			/**
			 * ���¹���һ��URI���󣬼���uri��content rowID��45�������¹�����URI��content
			 * ://contacts/people/45
			 * 
			 */
			//content://org.crazyit.providers.firstprovider/1
			Uri insertedUri = ContentUris.withAppendedId(uri, rowid);// ��װID
			getContext().getContentResolver().notifyChange(insertedUri, null);// ����URI
			return insertedUri;// ����URI
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
			// �������ݿ⣬�����Լ����ݿ�İ汾
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE t_user("
					+ "_ID integer PRIMARY KEY autoincrement," + "NAME text"
					+ ");");
			Log.i("aa", "DatabaseHelper-onCreate");
			Log.i(TAG, "Create Table t_user ok");
			// ������t_user�����
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS t_user");
			Log.i("aa", "DatabaseHelper-onUpgrade");
			onCreate(db);
		}

	}

}
