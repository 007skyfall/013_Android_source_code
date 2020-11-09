#define LOG_TAG "hello"
#include <stdio.h>
#include <jni.h>

jint c_open(JNIEnv *env, jclass clazz)
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG,"this is c_open\n");
	return 123;
}

jint c_ioctl(JNIEnv *env, jclass clazz,jint which,jint status)
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG,"this is c_ioctl\n");
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG,"which = %d,status = %d\n",which,status);
	return 123;
}

jint c_close(JNIEnv *env, jclass clazz)
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG,"this is c_close\n");
	return 123;
}

JNINativeMethod methods[] = {
	[0] = {
		.name      = "java_open",
		.signature = "()I",
		.fnPtr     = (void *)c_open,  
	},
	[1] = {
		.name      = "java_ioctl",
		.signature = "(II)I",
		.fnPtr     = (void *)c_ioctl,  
	},
	[2] = {
		.name      = "java_close",
		.signature = "()I",
		.fnPtr     = (void *)c_close,  
	},
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
	JNIEnv *env;
	jclass cls;
	
	//1.获取env
	if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_4)) {
		return JNI_ERR; /* jni.h JNI version not supported */
	}
	
	//2.找类
	cls = (*env)->FindClass(env, "com/hqyj/led_app/LedService");
	if (cls == NULL) {
		return JNI_ERR;
	}
	
	//3.方法的映射
	(*env)->RegisterNatives(env, cls, methods, sizeof(methods)/sizeof(methods[0]));
	
	return JNI_VERSION_1_4;
}
	
