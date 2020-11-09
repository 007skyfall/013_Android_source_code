public class Jni{
	public native int java_open();
	public native int java_ioctl(int which,int status);
	public native int java_close();
	
	static{
		System.loadLibrary("hello");
	}

	public static void main(String argv[])
	{
		Jni jni  = new Jni();
		System.out.println(jni.java_open());
		System.out.println(jni.java_ioctl(1,1));
		System.out.println(jni.java_ioctl(1,0));
		System.out.println(jni.java_close());
	}

	
}
