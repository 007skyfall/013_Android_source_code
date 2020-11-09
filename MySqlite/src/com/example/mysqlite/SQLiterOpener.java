package com.example.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * ���������ݿ⣬���ݿ����������� ��һ�����췽��ȷ��������ݿ������α��S�����ݿ�İ汾�� �ڶ������������������ݿ�Ĵ������ݿ��ϰ汾���͵��װ汾
 * �����������������ݿ�ı�
 * 
 */
public class SQLiterOpener extends SQLiteOpenHelper {
	private final static String DB_NAME = "people_db";// ���ݿ��ļ����ļ���
	//���ݿ��ļ���λ�����ڲ��洢·����database�ļ���/   /data/data/Ӧ�ó������/database

	public SQLiterOpener(Context context) {
		super(context, DB_NAME, null, 1);
		/**
		 * super ��д���Ĳ��� ����1 �����Ķ��� ����2 ���ݿ��� ����3 �α깤�� ����4 ���ݿ�İ汾
		 */

	}

	// ���ݿ�汾���±仯��ʱ�����ִ���������
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * ����1�����ݿ���� ����2�����ݿ���ϰ汾 ����3���°汾
		 */
		db.execSQL("drop table if exits stuff");
		/**
		 * ɾ�� stuff
		 */
		onCreate(db);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {// ���ݿ����
		/**
		 * �������ݿ�ķ���ͨ������������ ����ִ����һ������������
		 */							//listview	
		db.execSQL("create table stuff (_id integer primary key autoincrement, name text not null, age integer, position text not null)");
		// create table ���ݿ���� (�ֶ�)
		// _id ���� ���� autoincrement����
		// not null ����Ϊ��
		// primary key
		// stuff Ϊ����
		// text
		// �ı���С�����ƣ�utf-8
		// utf-16
//		�����ʽ
		/**
		 * GBK 
		 * ASC II
		 * ISO-8859-1(��֧������)
		 * UTF-8(���)
		 */

	}

}
