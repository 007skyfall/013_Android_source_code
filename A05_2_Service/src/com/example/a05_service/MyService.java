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
		Log.i("Tag", "�����Ѵ���");
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

	}

	class MyThread implements Runnable {
		Intent intent = new Intent();

		public void run() {
			// ƥ��Ƶ����
			intent.setAction("FM1126.2");
			while (!thread_loop) {
				try {
					i++;
					Log.i("Tag", "����ֵ:" + i);
					Thread.sleep(1000);
					//���;��������
					intent.putExtra("Count", i);
					sendBroadcast(intent);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
