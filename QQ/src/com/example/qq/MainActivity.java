package com.example.qq;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;
	private Handler handler;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
		new MyThread().start();
		tv = (TextView) findViewById(R.id.tv);
		handler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				tv.setText("跳过该页面：" + msg.what + "秒");
				return false;
			}
		});
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	// 页面显示出来的时候，等待3秒跳转
	@Override
	protected void onResume() {// Activity生命周期 执行完onresume显示页面
		super.onResume();
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			for (int i = 3; i >= 0; i--) {
				try {
					Thread.sleep(1000);
					handler.sendEmptyMessage(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (sp.getBoolean("flag", true)) {
				startActivity(new Intent(MainActivity.this, LoginAct.class));
			} else {
				startActivity(new Intent(MainActivity.this, mainpage.class));
			}

			MainActivity.this.finish();
			super.run();
		}

	}

}
