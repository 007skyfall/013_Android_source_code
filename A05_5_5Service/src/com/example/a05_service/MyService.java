package com.example.a05_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private boolean thread_loop, not_press;
	public static int i;
	public BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			not_press = intent.getBooleanExtra("NP", false);
		}
	};

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
		Log.i("Tag", "服务已创建");
		addregister();
	}

	private void addregister() {
		IntentFilter filter = new IntentFilter();
		filter.addAction("FM92.6");
		registerReceiver(receiver, filter);
	}

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("Tag", "服务已开启");
		MyThread mt = new MyThread();
		Thread t = new Thread(mt);
		t.start();
	}

	// 调用stopservice 就会调用ondestroy方法来关闭服务
	public void onDestroy() {
		super.onDestroy();
		thread_loop = true;
		Log.i("Tag", "服务已关闭");
		unregisterReceiver(receiver);

	}

	class MyThread implements Runnable {
		Intent intent = new Intent();

		public void run() {
			// 匹配频道号
			intent.setAction("FM1126.2");
			while (!thread_loop) {
				try {
					// Log.i("Tag", "计数值:" + i);
					// 发送具体的内容
					intent.putExtra("Count", ++i);
					sendBroadcast(intent);
					Thread.sleep(1000);
					// 如果不松开手，那么让其进入内循环
					while (not_press) {

					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
