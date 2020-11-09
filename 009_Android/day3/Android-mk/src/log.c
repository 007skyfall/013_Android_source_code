#define LOG_TAG "hello"
#include <stdio.h>
#include <android/log.h>

int main(int argc, const char *argv[])
{
	__android_log_print(ANDROID_LOG_INFO, LOG_TAG,"this is andorid log print\n");
	return 0;
}
