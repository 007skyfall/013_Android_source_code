package com.example.mysensor;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	/**
	 * 1.Android 手机 5.0以下 2.手机与电脑使用usb线 3.手机不要选择仅仅充电模式 4.usb线过细 优质线
	 * 5.设置--->开发者选项---->usb调试(如果没有usb调试,设置--->关于手机--->版本号(连点7下))
	 * 6.安装手机驱动--->驱动精灵,豌豆荚
	 * 
	 */

	private SensorManager sensorManager;
	
	private Sensor sensor, sensor1;
	
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);
		// 获取传感器的管理器
		
		sensorManager = (android.hardware.SensorManager) getSystemService(SENSOR_SERVICE);
		// 获取手持设备上所有传感器的列表
		
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

		for (int i = 0; i < sensors.size(); i++) {

			Log.i("Test", sensors.get(i).getName() + "");
		}

		// 获取加速度的传感器
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// 获取光照的传感器
		sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

	}

	/**
	 * 在onResume和onPause回调方法中分别注册和注销传感器事件监听器的重要的一步，这是一种推荐的做法， 即不需要的时候，不接受传感器数据
	 * 特别是Activity暂停时，否则 有些传感器会大量的消耗电量，影响电池的寿命，新版的系统不会自动关闭传感器。
	 * 
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_FASTEST);
		/**
		 * 注册传感器 1、保证节约电量 2、要注册的传感器
		 */

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(this);
		/**
		 * 当页面不在栈顶 我就讲传感器注销 为了节约电量
		 */
	}

	/**
	 * 这个参数可以是监听器事件对象那个，这个对象中的Values变量非常重要，该变量的类型是float[]
	 * 但该变量最多只有三个元素而且根据传感器的不同，vakues变量中的代表的含义也不同，对于光线传感器来说，只有Values数据的第一个值有意义
	 * 它代表了传感检测到的光线SIlux的值我们使用了StringBuilder记录光线变化的数据吗，
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

		float azimuth = Math.round(event.values[0]);
		float pitch = Math.round(event.values[1]);
		float roll = Math.round(event.values[2]);
		String out = String.format("Azimuth:%.2f\npitch:%.2f\nRoll:%.2f",
				azimuth, pitch, roll);
		// Log.d("TTT", out);
		tv.setText(out);
		// tv.setText(azimuth + "");

	}

	/**
	 * 当传感器的准确性更改时，将调用onAccuracyChanged，参数两个，一个是传感器对象；另一个标示该传感器的新的准确度
	 * 包括高/中/低/不可靠，其值为3 ，2 ,1 , 0 不可靠的的精确度不代表传感器已经坏了，有可能需要校准
	 */
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Log.i("Test", accuracy + "");

	}

}
