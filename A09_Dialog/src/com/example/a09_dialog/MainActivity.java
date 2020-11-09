package com.example.a09_dialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int a = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == event.KEYCODE_BACK) {

			if (a == 1) {
				finish();
			}
			if (a == 0) {
				Toast.makeText(getApplicationContext(), "�ٴε�����ؼ�", 1).show();
				a++;
			}

		}
		return true;
	}

	public AlertDialog createdialog() {
		// dialog������ ����
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		// ���������ñ���
		builder.setTitle("����һ���Ի���");
		// ������ʾ����
		builder.setMessage("ȷ���˳���ҳ����");
		// ���ð�ť
		builder.setNegativeButton("ȡ��", null);
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
			}
		});
		// ����AlertDialog����
		AlertDialog create = builder.create();
		return create;
	}
}
