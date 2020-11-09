package com.example.mycontentprovider;

import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	/**
	 * Android�ǻ���Linuxϵͳ�ģ�ÿ���û��ж����Ľ��̣���Щ����֮���ǲ��ܻ�����ʵģ������Ҫ�ڸ������̼乲�����ݣ�������Ҫ
	 * ʹ��Contentprovider��ʵ�֣�Android�����ݴ洢�������ļ������ݿ⣬
	 */
	private Button btnAdd, btnDelete, btnViewAll;
	private TextView ViewAll;
	private EditText inputId;
	private Uri CONTENT_URI = Uri.parse("content://org.crazyit.providers.firstprovider");

	// =
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnViewAll = (Button) findViewById(R.id.btnViewAll);
		ViewAll = (TextView) findViewById(R.id.ViewAll);
		inputId = (EditText) findViewById(R.id.inputId);

		CONTENT_URI = Uri.parse("content://org.crazyit.providers.firstprovider");
		btnAdd.setOnClickListener(this);
		btnViewAll.setOnClickListener(this);

		btnDelete.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdd:
			Log.i("aa", "������");
			ContentValues values = new ContentValues();//hashmap
			values.put("NAME", inputId.getText().toString());
			getContentResolver().insert(CONTENT_URI, values);
			// ���һ���ֶεķ�ʽ

			break;
		case R.id.btnViewAll://��ʾ
			Cursor cur = getContentResolver().query(CONTENT_URI, new String[] { "_ID", "NAME" }, null, null, null);
			StringBuffer sf = new StringBuffer();
			cur.moveToFirst();
			Log.i("aa", "�����ʾ");
			// Log.i("aa", "�����ʾ" + cur.getColumnName(0) + cur.getInt(0));
			while (!cur.isAfterLast()) {
				sf.append(cur.getInt(0)).append(":").append(cur.getString(1)).append("\n");
				Log.i("aa", sf.toString() + "");
				cur.moveToNext();

			}
			ViewAll.setText(sf.toString());

			break;
		case R.id.btnDelete:
			Log.i("aa", "���ɾ��");
			long selectedID = new Long(inputId.getText().toString());
//			"content://org.crazyit.providers.firstprovider/1
			Uri deleteUri = ContentUris.withAppendedId(CONTENT_URI, selectedID);

			Log.i("Test", deleteUri.toString());
			getContentResolver().delete(deleteUri, null, null);
			break;

		}

	}
}
