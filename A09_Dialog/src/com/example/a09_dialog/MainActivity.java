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
				Toast.makeText(getApplicationContext(), "再次点击返回键", 1).show();
				a++;
			}

		}
		return true;
	}

	public AlertDialog createdialog() {
		// dialog创建者 对象
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		// 创建者设置标题
		builder.setTitle("这是一个对话框");
		// 设置显示内容
		builder.setMessage("确定退出该页面吗？");
		// 设置按钮
		builder.setNegativeButton("取消", null);
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
			}
		});
		// 生成AlertDialog对象
		AlertDialog create = builder.create();
		return create;
	}
}
