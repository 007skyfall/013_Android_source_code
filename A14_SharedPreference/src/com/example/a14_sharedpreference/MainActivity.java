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
		// �������� ͬʱ������һ���ļ� mysp.xml
		// xml����λ��/data/data/<package name>/shared_pfs/mysp.xml
		SharedPreferences sp = getSharedPreferences("mysp",
				Context.MODE_PRIVATE);
		/*
		 * Editor edit = sp.edit(); edit.putString("name", "admin");
		 * edit.putString("password", "12345"); edit.commit();// �ύ
		 */
		String name = sp.getString("name", "");
		String password = sp.getString("password", "");
		Toast.makeText(getApplicationContext(), name + "::" + password, 1)
				.show();

	}

}
