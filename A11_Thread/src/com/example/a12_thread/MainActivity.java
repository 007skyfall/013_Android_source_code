package com.example.a12_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) { // ==/main
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {// ���ܵķ���
				super.handleMessage(msg);
				tv.setText((String) msg.obj);
			}
		};

		CountThread ct = new CountThread();
		ct.start();
	}

	// oncreate-onstart-onresume
	// ��UI�̲߳��ܸ���UI ���̲߳���������UI�Ĳ���
	// �̼߳�ͨ��
	class CountThread extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				try {
					Thread.sleep(1000);
					// tv.setText("����ֵ��"+i);
					Message msg = new Message();
					msg.obj = "����ֵ��" + i;
					handler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
