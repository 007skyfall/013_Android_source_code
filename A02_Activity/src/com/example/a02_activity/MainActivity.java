package com.example.a02_activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button btn;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn);
		et = (EditText) findViewById(R.id.et);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String msg = et.getText().toString();
				Intent intent = new Intent(MainActivity.this, SecondAct.class);// “‚Õº
				intent.putExtra("content", msg);// º¸÷µ∂‘
				startActivity(intent);

			}
		});
	}
}
