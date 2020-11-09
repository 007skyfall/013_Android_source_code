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
		Log.i("Tag", "�����Ѵ���");
		addregister();
	}

	private void addregister() {
		IntentFilter filter = new IntentFilter();
		filter.addAction("FM92.6");
		registerReceiver(receiver, filter);
	}

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("Tag", "�����ѿ���");
		MyThread mt = new MyThread();
		Thread t = new Thread(mt);
		t.start();
	}

	// ����stopservice �ͻ����ondestroy�������رշ���
	public void onDestroy() {
		super.onDestroy();
		thread_loop = true;
		Log.i("Tag", "�����ѹر�");
		unregisterReceiver(receiver);

	}

	class MyThread implements Runnable {
		Intent intent = new Intent();

		public void run() {
			// ƥ��Ƶ����
			intent.setAction("FM1126.2");
			while (!thread_loop) {
				try {
					// Log.i("Tag", "����ֵ:" + i);
					// ���;��������
					intent.putExtra("Count", ++i);
					sendBroadcast(intent);
					Thread.sleep(1000);
					// ������ɿ��֣���ô���������ѭ��
					while (not_press) {

					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
