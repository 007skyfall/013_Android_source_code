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
		list.add("-----------ɽ��ʡ-----------");
		list.add("-----------����ʡ-----------");
		list.add("-----------����ʡ-----------");
		list.add("-----------����ʡ-----------");
		list.add("-----------�ӱ�ʡ-----------");
		list.add("-----------����ʡ-----------");
		list.add("-----------�㶫ʡ-----------");
		list.add("-----------����ʡ-----------");
		list.add("-----------�Ĵ�ʡ-----------");
		list.add("-----------�ຣʡ-----------");
		list.add("-----------����ʡ-----------");
		// ����2��listview��ÿһ��Ĳ���
		// ����3��listviewÿһ��仯�Ŀؼ���ID
		// ����4������ؼ�ID���������ڵļ���
		ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
				R.layout.textlayout, R.id.tv, list);
		// ���������� ��������
		lv.setAdapter(adapter);
	}
}
