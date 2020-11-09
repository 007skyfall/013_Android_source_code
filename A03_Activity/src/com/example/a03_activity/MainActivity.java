package com.example.a03_activity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button call, send, browser, other;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		call = (Button) findViewById(R.id.call);
		send = (Button) findViewById(R.id.send);
		browser = (Button) findViewById(R.id.browser);
		other = (Button) findViewById(R.id.other);
		// ====================================
		call.setOnClickListener(this);// this 对象
		send.setOnClickListener(this);
		browser.setOnClickListener(this);
		other.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {// 用户点击call按钮，那么这个时候 v =call
		switch (v.getId()) {
		case R.id.call:
			// 打电话功能
			Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:110"));
			startActivity(i);
			// 权限问题
			break;
		case R.id.send:
			// 发短信
			Intent i1 = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:110"));
			i1.putExtra("sms_body", "hello world");
			startActivity(i1);
			break;
		case R.id.browser:
			Intent i2 = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.baidu.com"));
			startActivity(i2);
			break;
		case R.id.other:
			// com.example.a02_activity.SecondAct
			Intent i3 = new Intent();
			//			  android.intent.action.second_other
			i3.setAction("android.intent.action.second_other");
			i3.putExtra("content", "123456789");
			startActivity(i3);

			break;

		}
	}
}
