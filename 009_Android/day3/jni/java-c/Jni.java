public class Jni{
	public native int print(int num);
	static{
		System.loadLibrary("hello");
	}
	
	public static void main(String argv[])
	{
		Jni jni = new Jni();
		System.out.println("java:"+jni.print(10));
	}

}
