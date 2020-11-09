#ifndef __HAL_H__
#define __HAL_H__


struct led_device_t{
	hw_device_t *common;
	int (*hal_open)();
	int (*hal_ioctl)(int which,int status);
	int (*hal_close)();
};

#endif

