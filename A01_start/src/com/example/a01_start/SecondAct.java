package com.example.a01_start;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondAct extends Activity {
	private Button btn;

	// 所有的xml文件的节点都可以被java用类和对象表示

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_act);
		btn = (Button) findViewById(R.id.button1);
		btn.setText("跳转");// Charsequenece cq = new String();
		btn.setOnClickListener(new MyClick());
	}

	class MyClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 点击之后你想执行的内容
			btn.setTextColor(Color.RED);
			

		}

	}
}
