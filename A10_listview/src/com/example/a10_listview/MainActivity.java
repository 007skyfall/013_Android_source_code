package com.example.a10_listview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private ArrayList list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		list = new ArrayList();
		list.add("-----------山西省-----------");
		list.add("-----------湖北省-----------");
		list.add("-----------湖南省-----------");
		list.add("-----------河南省-----------");
		list.add("-----------河北省-----------");
		list.add("-----------福建省-----------");
		list.add("-----------广东省-----------");
		list.add("-----------广西省-----------");
		list.add("-----------四川省-----------");
		list.add("-----------青海省-----------");
		list.add("-----------云南省-----------");
		// 参数2：listview的每一项的布局
		// 参数3：listview每一项变化的控件的ID
		// 参数4：这个控件ID的内容所在的集合
		ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
				R.layout.textlayout, R.id.tv, list);
		// 设置适配器 设置内容
		lv.setAdapter(adapter);
	}
}
