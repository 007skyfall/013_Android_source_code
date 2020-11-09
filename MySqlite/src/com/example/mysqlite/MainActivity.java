package com.example.mysqlite;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	/**
	 * ����listview��һ����Ҫʹ��listActivity �����Ǽ̳���ListActivity 1����дsetcontentview
	 * 2�����������ListActivity������setcontentview������������Ǵ��ݵ�һ��listview�Ĳ���
	 * 
	 * �����ǳ�������֮��������ǵ���Ŀ������һ�����ݿ��ļ�
	 * 
	 * @see data/data/Ӧ�ó������/database�����ݿ���
	 * 
	 */
	/**
	 * sqlite���ݿⲻ���������ͣ� androidϵͳ��Ƕsqlite���ݿ⣬
	 * 
	 */
	private final static String TAG = "SQLiteTest";
	ArrayList<HashMap<String, Object>> data;
	SQLiterOpener dbOpener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);// listview��ʵ��
		// ��ʼ�����ݿ����
		dbOpener = new SQLiterOpener(MainActivity.this);

		// �������ݿ��в��뼸������
		insertuseSQL();// sql����������ɾ�Ĳ�execSQL��������
		insertUseAPI();
		// ��ѯ����ʹ��simplecursorAdapter��������
		SQLiteDatabase db = dbOpener.getWritableDatabase();

		// (Context context, int layout, Cursor c, String[] from, int[] to)
		// ��ȡһ����д�����ݿ���������Ա��е�������ɾ�Ĳ�
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.sqlite_list_item, db.rawQuery("select * from stuff",
						null), new String[] { "name", "age", "position" },
				new int[] { R.id.tv_name, R.id.tv_age, R.id.tv_posi });

		/**
		 * ����1��������Ǳ��� ����2������ ����3����ѯ�������൱������Դ ����4��from��������ȡ�����ֶ���
		 * ����5���趨���ĸ��ؼ���ͨ��ID����ʽ
		 * 
		 * 
		 * sql���,new string[]={"name"} rawQuery();a ����1����ѯ���sql��� ����2��ѡ���׼
		 */
		setListAdapter(adapter); // ==lv.setadapter(adapter)

		/**
		 * �ڶ���listview���÷����� activity�̳�ListActivity ����ʡ��setcontentview
		 * ��Ϊ���ǵ�ListActivity���Լ��Ĳ���Ϊlistview
		 * ��adapterʱ����ʹ�õ���setListAdapter(adapter)
		 * 
		 */

		db.close();// �ر����ݿ�
	}

	private void insertUseAPI() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();
		ContentValues cv = new ContentValues();// hashmap
		// ʹ�÷�ʽ�����ǵ�hashmap����
		cv.put("name", "Xyz");
		cv.put("age", 30);
		cv.put("position", "Stuff");
		db.insert("stuff", null, cv);
		/**
		 * ������������ put������Ӽ�ֵ �����������ǵ��ֶε�name ֵ�������ǵ����� db.insert������� ����1 ���ݿ�ı�����
		 * ����2 nullColumnHack�����ﴫ��ֵ������ǿ�в���nullֵ�������е������� ��values����Ϊnull�򲻰����κ�key-
		 * value�Ըò�����Ч
		 * 
		 * ����3 ���� ����һ�����ݵļ�¼
		 */
		cv = new ContentValues();
		cv.put("name", "Xsia");
		cv.put("age", 27);
		cv.put("position", "Manager");
		db.insert("stuff", null, cv);
		cv = new ContentValues();
		cv.put("name", "helloas");
		cv.put("age", 31);
		cv.put("position", "Manager");
		db.insert("stuff", null, cv);
		db.close();

	}

	public void deleteUseAPI() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();
		db.delete("stuff", "age<30 or position = 'Manager'", null);
		// db.delete("stuff", "age<? or position = ?", new Object[]
		// ={"29","manager"});
		/**
		 * ����1 ������ɾ�����ݵı��� string ����2 �����whereCaluse�Ӿ�ļ�¼���ᱻɾ��string ����3
		 * ����ΪwhereCaluse�Ӿ䴫�����string[] db.delete("stuff",
		 * "age<? or position = 'Manager'",new String[] ={"27"});
		 */
		db.close();
	}

	public void updateUseAPI() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();

		/**
		 * ����Ǹ��µ�API 1���������� 2��������� 3��update
		 */
		ContentValues cv = new ContentValues();
		cv.put("psotion", "Manager");
		db.update("stuff", cv, null, null);

		/*
		 * 
		 * ����1��������������ݵı��� ����2����������µ����� ���� 3�������wherecaluse�Ӿ�ļ�¼���ᱻ����
		 * ����4������Ϊwhereclause�Ӿ�Ĵ�������� db.update("stuff",cv,"Age>?",new
		 * Integer[]{30}); db.update("stuff",cv,"id>? and age =?",new
		 * Interger[]{20,20});
		 */

		db.close();
	}

	private void insertuseSQL() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();
		db.execSQL("insert into stuff values (null,'Sxzi',30,'stuff')");
		db.execSQL("insert into stuff values (null,'wang de',25,'stuff')");
		db.execSQL("insert into stuff values (null,'li',33,'Manager')");
		db.close();
	}

	public void deleteUseSQL() {

		SQLiteDatabase db = dbOpener.getWritableDatabase();
		db.execSQL("delete from stuff where age =30");
		db.close();

	}

	public void updateUseSQL() {

		SQLiteDatabase db = dbOpener.getWritableDatabase();
		db.execSQL("update stuff set position = 'manager' where position = 'stuff'");
		db.close();
	}

	// ʹ��SQL����ѯ
	public ArrayList<HashMap<String, Object>> rawQuery() {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

		SQLiteDatabase db = dbOpener.getWritableDatabase();
		String sql = "select * from stuff ";
		Cursor cursor = db.rawQuery(sql, null);// ִ�е�sql���
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String position = cursor.getString(cursor
					.getColumnIndex("position"));
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("age", age);
			map.put("position", position);
			result.add(map);
			cursor.moveToNext();
		}
		db.close();
		return result;

	}

	public void query() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();
		Cursor cursor = db.query("stuff", new String[] { "name", "age",
				"position" }, "age>10", null, "position", "", "age");
		/**
		 * ����1 ������"stuff" ����2 ���ֶ��� ͨ���ַ�����ʽ���ж� ���� 3��ѡ������ ѡ����� ����4�������������ĸ���
		 * ����5����ȡ��ֵ
		 * 
		 * 
		 * 
		 */
		Log.i(TAG, "�ֶθ�����" + cursor.getColumnCount()); // �õ�����ֶθ���
		Log.i(TAG, "����ѯ��" + cursor.getCount() + "��");
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String position = cursor
					.getString(cursor.getColumnIndex("postion"));
			Log.i("TAG", "Name:" + name + ",AGE:" + age + ",Position"
					+ position);

			cursor.moveToNext();

		}
		db.close();
	}

}
