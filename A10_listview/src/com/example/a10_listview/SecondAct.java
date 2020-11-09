package com.example.a10_listview;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SecondAct extends Activity {
	private ListView lv;
	private int images[] = { R.drawable.backup, R.drawable.browser,
			R.drawable.contacts, R.drawable.document, R.drawable.map };
	private String names[] = { "������", "�����", "��ϵ��", "�ļ���", "��ͼ" };
	private ArrayList<HashMap<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		list = new ArrayList<HashMap<String, Object>>();
		// ����hashmap
		HashMap<String, Object> map = null;
		// ����Դ����ʵ����
		for (int i = 0; i < images.length; i++) {
			map = new HashMap<String, Object>();
			map.put("name", names[i]);
			map.put("image", images[i]);
			map.put("info", "����һ����õ�" + names[i]);
			list.add(map);
		}
		String from[] = { "name", "image", "info" };
		int to[] = { R.id.name, R.id.iv, R.id.info };
		// �Ӽ�����Դ�� ���ؼ�ID��ȥ
		// ����2 ����Դ
		// ����3
		SimpleAdapter adapter = new SimpleAdapter(SecondAct.this, list,
				R.layout.listitem, from, to);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"�������" + list.get(position).get("name"), 1).show();

			}
		});

	}
}
