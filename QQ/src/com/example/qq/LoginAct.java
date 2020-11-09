package com.example.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAct extends Activity {
	private EditText et_user, et_password;
	private SharedPreferences sp;
	private Editor edit;
	private boolean first = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		et_user = (EditText) findViewById(R.id.et_user);
		et_password = (EditText) findViewById(R.id.et_password);
		sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
		edit = sp.edit();
	}

	public void Login(View v) {
		String str_user = et_user.getText().toString();
		String str_password = et_password.getText().toString();
		if (str_user.equals("admin") && str_password.equals("123456")) {// 成功
			// 页面跳转
			startActivity(new Intent(LoginAct.this, mainpage.class));
			edit.putBoolean("flag", first);
			edit.commit();
			finish();

		} else {
			Toast.makeText(getApplicationContext(), "用户名或密码错误", 1).show();
			et_user.setText("");
			et_password.setText("");
		}

	}
}
