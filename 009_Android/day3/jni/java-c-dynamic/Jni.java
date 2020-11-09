public class Jni{
	public native int java_open();
	static{
		System.loadLibrary("hello");
	}
	
	public static void main(String argv[])
	{
		Jni jni = new Jni();
		System.out.println("java:"+jni.java_open());
	}

}
