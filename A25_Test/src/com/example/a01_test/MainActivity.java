package com.example.a01_test;

import java.io.FileDescriptor;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

public class MainActivity extends Activity implements MediaPlayerControl,
		OnBufferingUpdateListener {

	MediaController mController;
	MediaPlayer mPlayer; //音乐播放实质内容
	ImageView coverImage;
	int bufferprecent = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		coverImage = (ImageView) findViewById(R.id.coverImage);
		mController = new MediaController(this);//上下文对象
		mController.setAnchorView(findViewById(R.id.root));

	}

	@Override
	protected void onResume() {//activity 生命周期
		// TODO Auto-generated method stub
		super.onResume();
		mPlayer = new MediaPlayer();
		try {
			// mPlayer.setDataSource(this, Uri.parse("URI_TO_REMOTE_AUDIO"));
			mPlayer.setDataSource("/sdcard/bbbb.mp3");//设置音乐文件
			mPlayer.prepare();//预备工作
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coverImage.setImageResource(R.drawable.ic_launcher);//设置图片
		mController.setMediaPlayer(this);//能不能播放音乐
		mController.setEnabled(true);//可用
	}

	@Override
	protected void onPause() {//Activity生命周期 暂停
		// TODO Auto-generated method stub
		super.onPause();
		// 释放与关闭
		mPlayer.release();//释放
		mPlayer = null;//消亡

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {//触摸事件
		// TODO Auto-generated method stub
		mController.show();//显示控制条
		return super.onTouchEvent(event);
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
		// TODO Auto-generated method stub
		bufferprecent = arg1;

	}

	// 回调
	@Override
	public boolean canPause() {//能不能暂停
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekBackward() {//能不能上一个时间帧
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekForward() {//能不能下一个时间帧
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return bufferprecent;
	}

	@Override
	public int getCurrentPosition() {//控制条的方法
		// TODO Auto-generated method stub
		return mPlayer.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return mPlayer.getDuration(); //音乐时间长度
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return mPlayer.isPlaying();//播放状态
	}

	@Override
	public void pause() {//同步暂停
		// TODO Auto-generated method stub
		mPlayer.pause();//暂停
	}

	@Override
	public void seekTo(int arg0) {//跳转位置
		// TODO Auto-generated method stub
		mPlayer.seekTo(arg0);
	}

	@Override
	public void start() {//控制条的开启按钮监听
		// TODO Auto-generated method stub
		mPlayer.start();
	}
}
