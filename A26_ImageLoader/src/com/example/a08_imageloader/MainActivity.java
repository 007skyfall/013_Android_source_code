package com.example.a08_imageloader;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView rl = (ImageView) findViewById(R.id.iv);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_launcher) // ���ؿ�ʼĬ�ϵ�ͼƬ
				.showImageForEmptyUri(R.drawable.ic_launcher) // url���Օ���ʾ��ͼƬ���Լ�����drawable�����
				.showImageOnFail(R.drawable.ic_launcher) // ����ͼƬ�������⣬����ʾ��ͼƬ
				.cacheInMemory() // ������
				.cacheOnDisc() // ������
				.displayer(new RoundedBitmapDisplayer(5)) // ͼƬԲ����ʾ��ֵΪ����
				.build();
		ImageLoader.getInstance().displayImage(
				"http://192.168.7.121:8888/MyWeb/3.jpg", rl, options);
	}

}
