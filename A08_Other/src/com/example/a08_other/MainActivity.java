package com.example.a08_other;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Button pre, next;
	private ImageView iv;
	private int a;
	private int imageid[] = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
			R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
			R.drawable.a8 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pre = (Button) findViewById(R.id.pre);
		next = (Button) findViewById(R.id.next);
		iv = (ImageView) findViewById(R.id.iv);

	}

	public void MyClick(View v) {
		switch (v.getId()) {
		case R.id.pre:

			a = (a - 1 + imageid.length) % imageid.length;
			iv.setImageResource(imageid[a]);
			break;
		case R.id.next:
			a = (a + 1) % imageid.length;
			iv.setImageResource(imageid[a]);
			break;

		}

	}
}
