#include <stdio.h>
#include <Jni.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <unistd.h>

int fd;

JNIEXPORT jint JNICALL Java_Jni_java_1open(JNIEnv *env, jobject obj)
{
	//diao yong qudong 
	fd = open("/dev/hello",O_RDWR);
	if(fd == -1){
		perror("open /dev/hello");
		return -1;
	}
	return 123;
}


JNIEXPORT jint JNICALL Java_Jni_java_1ioctl(JNIEnv *env, jobject obj, jint which, jint status)
{
	ioctl(fd,which,status);

	return 234;
}


JNIEXPORT jint JNICALL Java_Jni_java_1close(JNIEnv *env, jobject obj)
{
	close(fd);
	return 321;
}











