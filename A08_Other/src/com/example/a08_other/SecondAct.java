package com.example.a08_other;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class SecondAct extends Activity {
	private CheckBox cb;
	private RadioButton rb2;
	private RadioButton rb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		cb = (CheckBox) findViewById(R.id.checkBox1);
		rb = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(),
							"��ѡ����" + cb.getText(), 1).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"��ȡ��ѡ����" + cb.getText(), 1).show();
				}

			}
		});
		rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(),
							"��ѡ����" + rb.getText(), 1).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"��ȡ��ѡ����" + rb.getText(), 1).show();
				}

			}
		});
		rb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(),
							"��ѡ����" + rb2.getText(), 1).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"��ȡ��ѡ����" + rb2.getText(), 1).show();
				}

			}
		});
	}
}
