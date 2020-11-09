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
       //1.找到图片的按钮
        button = (ImageButton) findViewById(R.id.imageButton1);
        //3.new一个调用底层的对象
        service  = new LedService();
       //2.为按钮设置点击事件
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//4.取反
			status =! status;
				if(status){
					//亮灯
					service.java_open();
					service.java_ioctl(1, 1);
			       //5.切换画布为亮灯
					button.setImageDrawable(getResources().getDrawable(R.drawable.bulb_on));
				}else{
					//灭灯
					service.java_ioctl(1, 0);
					service.java_close();
					//5.切换画布为灭灯
					button.setImageDrawable(getResources().getDrawable(R.drawable.bulb_off));
				}
				
				
			}
		});
        
        
        
    }

    
}


