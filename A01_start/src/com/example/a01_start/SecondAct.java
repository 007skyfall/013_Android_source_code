package com.example.a01_start;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondAct extends Activity {
	private Button btn;

	// ���е�xml�ļ��Ľڵ㶼���Ա�java����Ͷ����ʾ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_act);
		btn = (Button) findViewById(R.id.button1);
		btn.setText("��ת");// Charsequenece cq = new String();
		btn.setOnClickListener(new MyClick());
	}

	class MyClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// ���֮������ִ�е�����
			btn.setTextColor(Color.RED);
			

		}

	}
}
