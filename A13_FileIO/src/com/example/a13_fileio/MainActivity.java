package com.example.a13_fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button save, take;
	private TextView tv;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		save = (Button) findViewById(R.id.save);
		take = (Button) findViewById(R.id.take);
		tv = (TextView) findViewById(R.id.tv);
		et = (EditText) findViewById(R.id.et);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 写
				File file = new File("/sdcard/a.txt");
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					FileOutputStream fos = new FileOutputStream(file);
					String str = et.getText().toString();
					byte[] bytes = str.getBytes();
					fos.write(bytes);
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		take.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 权限问题
				File file = new File("/sdcard/a.txt");
				if (file.exists()) {
					try {
						FileInputStream fis = new FileInputStream(file);
						byte buffer[] = new byte[(int) file.length()];
						fis.read(buffer);
						String str = new String(buffer);
						tv.setText(str);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
	}
}
