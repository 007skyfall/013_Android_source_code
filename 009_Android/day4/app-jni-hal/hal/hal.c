#define LOG_TAG "hello"
#include <cutils/log.h>
#include <hardware/hardware.h>
#include "hal.h"

int led_hal_open()
{
	ALOGI("this is led_hal_open\n");
	return 0;
}

int led_hal_ioctl(int which,int status)
{
	ALOGI("this is led_hal_ioctl\n");
	ALOGI("which = %d,status = %d\n",which,status);
	return 0;
}

int led_hal_close()
{
	ALOGI("this is led_hal_ioctl\n");
	return 0;
}

struct led_device_t led = {
	.hal_open = led_hal_open,
	.hal_ioctl = led_hal_ioctl,
	.hal_close = led_hal_close,

};

int return_hal_api(const struct hw_module_t* module, const char* id,
				struct hw_device_t** device)
{
	*device = (struct hw_device_t*)&led;
	return 0;
}

hw_module_methods_t methods = {
	.open = return_hal_api,
};

hw_module_t HMI = {
	.id = "myled",
	.methods = &methods,
};








