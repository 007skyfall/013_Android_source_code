package com.example.a14_sharedpreference;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建对象 同时创建了一个文件 mysp.xml
		// xml所在位置/data/data/<package name>/shared_pfs/mysp.xml
		SharedPreferences sp = getSharedPreferences("mysp",
				Context.MODE_PRIVATE);
		/*
		 * Editor edit = sp.edit(); edit.putString("name", "admin");
		 * edit.putString("password", "12345"); edit.commit();// 提交
		 */
		String name = sp.getString("name", "");
		String password = sp.getString("password", "");
		Toast.makeText(getApplicationContext(), name + "::" + password, 1)
				.show();

	}

}
