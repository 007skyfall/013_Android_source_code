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
	 * 1.Android �ֻ� 5.0���� 2.�ֻ������ʹ��usb�� 3.�ֻ���Ҫѡ��������ģʽ 4.usb�߹�ϸ ������
	 * 5.����--->������ѡ��---->usb����(���û��usb����,����--->�����ֻ�--->�汾��(����7��))
	 * 6.��װ�ֻ�����--->��������,�㶹��
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
		// ��ȡ�������Ĺ�����
		
		sensorManager = (android.hardware.SensorManager) getSystemService(SENSOR_SERVICE);
		// ��ȡ�ֳ��豸�����д��������б�
		
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

		for (int i = 0; i < sensors.size(); i++) {

			Log.i("Test", sensors.get(i).getName() + "");
		}

		// ��ȡ���ٶȵĴ�����
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// ��ȡ���յĴ�����
		sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

	}

	/**
	 * ��onResume��onPause�ص������зֱ�ע���ע���������¼�����������Ҫ��һ��������һ���Ƽ��������� ������Ҫ��ʱ�򣬲����ܴ���������
	 * �ر���Activity��ͣʱ������ ��Щ����������������ĵ�����Ӱ���ص��������°��ϵͳ�����Զ��رմ�������
	 * 
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_FASTEST);
		/**
		 * ע�ᴫ���� 1����֤��Լ���� 2��Ҫע��Ĵ�����
		 */

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(this);
		/**
		 * ��ҳ�治��ջ�� �Ҿͽ�������ע�� Ϊ�˽�Լ����
		 */
	}

	/**
	 * ������������Ǽ������¼������Ǹ�����������е�Values�����ǳ���Ҫ���ñ�����������float[]
	 * ���ñ������ֻ������Ԫ�ض��Ҹ��ݴ������Ĳ�ͬ��vakues�����еĴ���ĺ���Ҳ��ͬ�����ڹ��ߴ�������˵��ֻ��Values���ݵĵ�һ��ֵ������
	 * �������˴��м�⵽�Ĺ���SIlux��ֵ����ʹ����StringBuilder��¼���߱仯��������
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
	 * ����������׼ȷ�Ը���ʱ��������onAccuracyChanged������������һ���Ǵ�����������һ����ʾ�ô��������µ�׼ȷ��
	 * ������/��/��/���ɿ�����ֵΪ3 ��2 ,1 , 0 ���ɿ��ĵľ�ȷ�Ȳ����������Ѿ����ˣ��п�����ҪУ׼
	 */
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Log.i("Test", accuracy + "");

	}

}
