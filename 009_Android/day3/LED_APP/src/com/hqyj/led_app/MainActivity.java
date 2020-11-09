package com.hqyj.led_app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.app.Activity;

public class MainActivity extends Activity {

    private ImageButton button;
    private boolean status = false;
	private LedService service;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //1.�ҵ�ͼƬ�İ�ť
        button = (ImageButton) findViewById(R.id.imageButton1);
        //3.newһ�����õײ�Ķ���
        service  = new LedService();
       //2.Ϊ��ť���õ���¼�
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//4.ȡ��
			status =! status;
				if(status){
					//����
					service.java_open();
					service.java_ioctl(1, 1);
			       //5.�л�����Ϊ����
					button.setImageDrawable(getResources().getDrawable(R.drawable.bulb_on));
				}else{
					//���
					service.java_ioctl(1, 0);
					service.java_close();
					//5.�л�����Ϊ���
					button.setImageDrawable(getResources().getDrawable(R.drawable.bulb_off));
				}
				
				
			}
		});
        
        
        
    }

    
}


