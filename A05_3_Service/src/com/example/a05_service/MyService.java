package com.example.a05_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private boolean thread_loop;
	public static int i;

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
		Log.i("Tag", "服务已创建");
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

	}

	class MyThread implements Runnable {
		Intent intent = new Intent();

		public void run() {
			// 匹配频道号
			intent.setAction("FM1126.2");
			while (!thread_loop) {
				try {
					i++;
					Log.i("Tag", "计数值:" + i);
					Thread.sleep(1000);
					//发送具体的内容
					intent.putExtra("Count", i);
					sendBroadcast(intent);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
