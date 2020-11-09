package com.example.a01_start;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

//Activityy通常展现为一个可视化的用户界面
//（1）新页面的创建
/* 1.创建一个类 并让她继承 Activity
 * 2.创建一个xml文件，用来设置setcontentview的参数
 * 3.AndroidManiFest.xml用来注册页面
 */
//（2） 按钮如何控制
//  1.创建一个按钮在xml文件中
//	2.找到这个按钮在java代码中，通过id来找到
//  3.设置他的监听事件，setonclicklistener


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {// 创建
		super.onCreate(savedInstanceState);// 继承父类
		setContentView(R.layout.activity_main);// 设置内容视图
	}

}
