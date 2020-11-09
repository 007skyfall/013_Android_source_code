package com.example.a05_service;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button start, destroy;
	private TextView tv;
	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.start);
		destroy = (Button) findViewById(R.id.destroy);
		tv = (TextView) findViewById(R.id.tv);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 开启服务
				startService(new Intent(MainActivity.this, MyService.class));

			}
		});
		destroy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(new Intent(MainActivity.this, MyService.class));

			}
		});

		// =========================BroadcastReceiver接收器============================
		receiver = new BroadcastReceiver() {
			// 就是将来要接受到的内容，接受内容 实时的
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				// 取出发送过来的内容 
				int Count = intent.getIntExtra("Count", 0);
				//设置计数值
				tv.setText("当前计数值:" + Count);

			}
		};

		// 注册频道号
		addregister();
	}

	private void addregister() {
		// 注册接收器
		IntentFilter filter = new IntentFilter();
		filter.addAction("FM1126.2");
		registerReceiver(receiver, filter);

	}

}
