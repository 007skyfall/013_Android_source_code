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
	MediaPlayer mPlayer; //���ֲ���ʵ������
	ImageView coverImage;
	int bufferprecent = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		coverImage = (ImageView) findViewById(R.id.coverImage);
		mController = new MediaController(this);//�����Ķ���
		mController.setAnchorView(findViewById(R.id.root));

	}

	@Override
	protected void onResume() {//activity ��������
		// TODO Auto-generated method stub
		super.onResume();
		mPlayer = new MediaPlayer();
		try {
			// mPlayer.setDataSource(this, Uri.parse("URI_TO_REMOTE_AUDIO"));
			mPlayer.setDataSource("/sdcard/bbbb.mp3");//���������ļ�
			mPlayer.prepare();//Ԥ������
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
		coverImage.setImageResource(R.drawable.ic_launcher);//����ͼƬ
		mController.setMediaPlayer(this);//�ܲ��ܲ�������
		mController.setEnabled(true);//����
	}

	@Override
	protected void onPause() {//Activity�������� ��ͣ
		// TODO Auto-generated method stub
		super.onPause();
		// �ͷ���ر�
		mPlayer.release();//�ͷ�
		mPlayer = null;//����

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {//�����¼�
		// TODO Auto-generated method stub
		mController.show();//��ʾ������
		return super.onTouchEvent(event);
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
		// TODO Auto-generated method stub
		bufferprecent = arg1;

	}

	// �ص�
	@Override
	public boolean canPause() {//�ܲ�����ͣ
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekBackward() {//�ܲ�����һ��ʱ��֡
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekForward() {//�ܲ�����һ��ʱ��֡
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return bufferprecent;
	}

	@Override
	public int getCurrentPosition() {//�������ķ���
		// TODO Auto-generated method stub
		return mPlayer.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return mPlayer.getDuration(); //����ʱ�䳤��
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return mPlayer.isPlaying();//����״̬
	}

	@Override
	public void pause() {//ͬ����ͣ
		// TODO Auto-generated method stub
		mPlayer.pause();//��ͣ
	}

	@Override
	public void seekTo(int arg0) {//��תλ��
		// TODO Auto-generated method stub
		mPlayer.seekTo(arg0);
	}

	@Override
	public void start() {//�������Ŀ�����ť����
		// TODO Auto-generated method stub
		mPlayer.start();
	}
}
