package com.example.a01_start;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

//Activityyͨ��չ��Ϊһ�����ӻ����û�����
//��1����ҳ��Ĵ���
/* 1.����һ���� �������̳� Activity
 * 2.����һ��xml�ļ�����������setcontentview�Ĳ���
 * 3.AndroidManiFest.xml����ע��ҳ��
 */
//��2�� ��ť��ο���
//  1.����һ����ť��xml�ļ���
//	2.�ҵ������ť��java�����У�ͨ��id���ҵ�
//  3.�������ļ����¼���setonclicklistener


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {// ����
		super.onCreate(savedInstanceState);// �̳и���
		setContentView(R.layout.activity_main);// ����������ͼ
	}

}
