package com.example.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * 创建的数据库，数据库有三个方法 ，一个构造方法确定你的数据库名，游表工廠，数据库的版本号 第二个方法用来更新数据库的传入数据库老版本，和当亲版本
 * 第三个方法创建数据库的表
 * 
 */
public class SQLiterOpener extends SQLiteOpenHelper {
	private final static String DB_NAME = "people_db";// 数据库文件的文件名
	//数据库文件的位置在内部存储路径的database文件夹/   /data/data/应用程序包名/database

	public SQLiterOpener(Context context) {
		super(context, DB_NAME, null, 1);
		/**
		 * super 重写他的参数 参数1 上下文对象 参数2 数据库名 参数3 游标工厂 参数4 数据库的版本
		 */

	}

	// 数据库版本更新变化的时候进行执行这个方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * 参数1、数据库对象 参数2、数据库的老版本 参数3、新版本
		 */
		db.execSQL("drop table if exits stuff");
		/**
		 * 删表 stuff
		 */
		onCreate(db);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {// 数据库对象
		/**
		 * 创建数据库的方法通常用来创建表 我们执行了一个创建表的语句
		 */							//listview	
		db.execSQL("create table stuff (_id integer primary key autoincrement, name text not null, age integer, position text not null)");
		// create table 数据库表名 (字段)
		// _id 整形 主键 autoincrement自增
		// not null 不能为空
		// primary key
		// stuff 为表名
		// text
		// 文本大小无限制，utf-8
		// utf-16
//		编码格式
		/**
		 * GBK 
		 * ASC II
		 * ISO-8859-1(不支持中文)
		 * UTF-8(最多)
		 */

	}

}
