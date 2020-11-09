package com.example.a05_service;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button start_pause;
	private TextView tv;
	private BroadcastReceiver receiver;
	private boolean flag_switch;
	private SeekBar sb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start_pause = (Button) findViewById(R.id.start_pause);
		tv = (TextView) findViewById(R.id.tv);
		sb = (SeekBar) findViewById(R.id.sb);
		// ��ʼ��
		sb.setProgress(0);
		sb.setMax(100);// Ĭ��Ϊ100
		start_pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��������
				if (!flag_switch) {
					startService(new Intent(MainActivity.this, MyService.class));
					start_pause
							.setBackgroundResource(android.R.drawable.ic_media_pause);
					flag_switch = true;
				} else {
					stopService(new Intent(MainActivity.this, MyService.class));
					start_pause
							.setBackgroundResource(android.R.drawable.ic_media_play);
					flag_switch = false;
				}

			}
		});

		// =========================BroadcastReceiver������============================
		receiver = new BroadcastReceiver() {
			// ���ǽ���Ҫ���ܵ������ݣ��������� ʵʱ��
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				// ȡ�����͹���������
				int Count = intent.getIntExtra("Count", 0);
				// ���ü���ֵ
				tv.setText("��ǰ����ֵ:" + Count);
				sb.setProgress(Count);

			}
		};

		// ע��Ƶ����
		addregister();
		// seekbar�ļ����϶��ļ����¼�
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// ֹͣ׷�ٴ���
				Log.i("Tag", "����ִ��onStopTrackingTouch");
				MyService.i = sb.getProgress();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// ��ʼ׷�ٴ���
				Log.i("Tag", "����ִ��onStartTrackingTouch");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// ���ȸı� �ͻ�ִ�и÷���
				Log.i("Tag", "����ִ��onProgressChanged����" + progress);
				tv.setText("��ǰ����ֵ:" + progress);
				
			}
		});
	}

	private void addregister() {
		// ע�������
		IntentFilter filter = new IntentFilter();
		filter.addAction("FM1126.2");
		registerReceiver(receiver, filter);

	}

}
