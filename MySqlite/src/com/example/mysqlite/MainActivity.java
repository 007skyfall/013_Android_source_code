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
	 * 创建listview不一定非要使用listActivity 当我们继承了ListActivity 1、不写setcontentview
	 * 2、在我们这个ListActivity里面有setcontentview这个方法参数是传递的一个listview的布局
	 * 
	 * 当我们程序运行之后会在我们的项目中生成一个数据库文件
	 * 
	 * @see data/data/应用程序包名/database下数据库名
	 * 
	 */
	/**
	 * sqlite数据库不分数据类型， android系统内嵌sqlite数据库，
	 * 
	 */
	private final static String TAG = "SQLiteTest";
	ArrayList<HashMap<String, Object>> data;
	SQLiterOpener dbOpener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);// listview的实现
		// 初始化数据库打开器
		dbOpener = new SQLiterOpener(MainActivity.this);

		// 先向数据库中插入几条数据
		insertuseSQL();// sql语句来完成增删改查execSQL（）方法
		insertUseAPI();
		// 查询，并使用simplecursorAdapter适配数据
		SQLiteDatabase db = dbOpener.getWritableDatabase();

		// (Context context, int layout, Cursor c, String[] from, int[] to)
		// 获取一个可写的数据库对象，用来对表中的内容增删改查
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.sqlite_list_item, db.rawQuery("select * from stuff",
						null), new String[] { "name", "age", "position" },
				new int[] { R.id.tv_name, R.id.tv_age, R.id.tv_posi });

		/**
		 * 参数1、传入的是本类 参数2、布局 参数3、查询的内容相当于数据源 参数4、from从哪来获取的是字段名
		 * 参数5、设定到哪个控件上通过ID的形式
		 * 
		 * 
		 * sql语句,new string[]={"name"} rawQuery();a 参数1、查询语句sql语句 参数2、选择标准
		 */
		setListAdapter(adapter); // ==lv.setadapter(adapter)

		/**
		 * 第二种listview设置方法： activity继承ListActivity 可以省略setcontentview
		 * 因为我们的ListActivity有自己的布局为listview
		 * 绑定adapter时我们使用的是setListAdapter(adapter)
		 * 
		 */

		db.close();// 关闭数据库
	}

	private void insertUseAPI() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();
		ContentValues cv = new ContentValues();// hashmap
		// 使用方式与我们的hashmap相似
		cv.put("name", "Xyz");
		cv.put("age", 30);
		cv.put("position", "Stuff");
		db.insert("stuff", null, cv);
		/**
		 * 创建容器对象 put方法添加键值 ：键就是我们的字段的name 值就是我们的数据 db.insert添加容器 参数1 数据库的表名、
		 * 参数2 nullColumnHack（这里传空值）代表强行插入null值得数据列的列名， 当values参数为null或不包含任何key-
		 * value对该参数有效
		 * 
		 * 参数3 容器 代表一行数据的记录
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
		 * 参数1 代表想删除数据的表名 string 参数2 满足该whereCaluse子句的记录将会被删除string 参数3
		 * 用于为whereCaluse子句传入参数string[] db.delete("stuff",
		 * "age<? or position = 'Manager'",new String[] ={"27"});
		 */
		db.close();
	}

	public void updateUseAPI() {
		SQLiteDatabase db = dbOpener.getWritableDatabase();

		/**
		 * 这个是更新的API 1、创建容器 2、存放数据 3、update
		 */
		ContentValues cv = new ContentValues();
		cv.put("psotion", "Manager");
		db.update("stuff", cv, null, null);

		/*
		 * 
		 * 参数1、代表想更新数据的表名 参数2、代表想更新的数据 参数 3、满足该wherecaluse子句的记录将会被更新
		 * 参数4、用于为whereclause子句的传入参数； db.update("stuff",cv,"Age>?",new
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

	// 使用SQL语句查询
	public ArrayList<HashMap<String, Object>> rawQuery() {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

		SQLiteDatabase db = dbOpener.getWritableDatabase();
		String sql = "select * from stuff ";
		Cursor cursor = db.rawQuery(sql, null);// 执行的sql语句
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
		 * 参数1 表名："stuff" 参数2 ，字段名 通过字符串形式来判断 参数 3，选择条件 选择语句 参数4，组名来自于哪个组
		 * 参数5，拿取得值
		 * 
		 * 
		 * 
		 */
		Log.i(TAG, "字段个数：" + cursor.getColumnCount()); // 得到表的字段个数
		Log.i(TAG, "共查询出" + cursor.getCount() + "行");
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
