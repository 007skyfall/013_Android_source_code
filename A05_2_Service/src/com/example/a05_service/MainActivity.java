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
				// ��������
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

		// =========================BroadcastReceiver������============================
		receiver = new BroadcastReceiver() {
			// ���ǽ���Ҫ���ܵ������ݣ��������� ʵʱ��
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				// ȡ�����͹��������� 
				int Count = intent.getIntExtra("Count", 0);
				//���ü���ֵ
				tv.setText("��ǰ����ֵ:" + Count);

			}
		};

		// ע��Ƶ����
		addregister();
	}

	private void addregister() {
		// ע�������
		IntentFilter filter = new IntentFilter();
		filter.addAction("FM1126.2");
		registerReceiver(receiver, filter);

	}

}
