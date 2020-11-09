#include <stdio.h>
//#include <jni.h>
#include "Jni.h"

jint Java_Jni_print(JNIEnv *env, jobject obj, jint num)
{
	printf("c:num = %d\n",num);
	return 123;
}
