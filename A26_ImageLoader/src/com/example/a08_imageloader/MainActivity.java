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
				.showStubImage(R.drawable.ic_launcher) // 加载开始默认的图片
				.showImageForEmptyUri(R.drawable.ic_launcher) // url爲空會显示该图片，自己放在drawable里面的
				.showImageOnFail(R.drawable.ic_launcher) // 加载图片出现问题，会显示该图片
				.cacheInMemory() // 缓存用
				.cacheOnDisc() // 缓存用
				.displayer(new RoundedBitmapDisplayer(5)) // 图片圆角显示，值为整数
				.build();
		ImageLoader.getInstance().displayImage(
				"http://192.168.7.121:8888/MyWeb/3.jpg", rl, options);
	}

}
