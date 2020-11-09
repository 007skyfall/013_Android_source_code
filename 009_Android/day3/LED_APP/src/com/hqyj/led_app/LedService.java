package com.hqyj.led_app;

public class LedService {
	public native int java_open();
	public native int java_ioctl(int which,int staus);
	public native int java_close();
	static{
		System.loadLibrary("hello");		
	}
}
