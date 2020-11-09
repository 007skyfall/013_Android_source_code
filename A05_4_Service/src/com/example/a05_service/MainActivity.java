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
		// 初始化
		sb.setProgress(0);
		sb.setMax(100);// 默认为100
		start_pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 开启服务
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

		// =========================BroadcastReceiver接收器============================
		receiver = new BroadcastReceiver() {
			// 就是将来要接受到的内容，接受内容 实时的
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				// 取出发送过来的内容
				int Count = intent.getIntExtra("Count", 0);
				// 设置计数值
				tv.setText("当前计数值:" + Count);
				sb.setProgress(Count);

			}
		};

		// 注册频道号
		addregister();
		// seekbar的监听拖动的监听事件
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// 停止追踪触摸
				Log.i("Tag", "正在执行onStopTrackingTouch");
				MyService.i = sb.getProgress();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// 开始追踪触摸
				Log.i("Tag", "正在执行onStartTrackingTouch");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// 进度改变 就会执行该方法
				Log.i("Tag", "正在执行onProgressChanged：：" + progress);
				tv.setText("当前计数值:" + progress);
				
			}
		});
	}

	private void addregister() {
		// 注册接收器
		IntentFilter filter = new IntentFilter();
		filter.addAction("FM1126.2");
		registerReceiver(receiver, filter);

	}

}
