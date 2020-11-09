package com.example.a12_thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondAct extends Activity {
	private Button btn;
	private Handler handler;
	private int a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.button1);
		myThread mt = new myThread();
		mt.start();
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 发送消息
				Message msg = new Message();
				msg.obj = a++;
				handler.sendMessage(msg);
			}
		});
	}

	class myThread extends Thread {
		@Override
		public void run() {
			// 接受
			Looper.prepare();
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					Log.i("Tag", "接受到了：" + msg.obj);
				}
			};
			Looper.loop();
		}
	}
}
