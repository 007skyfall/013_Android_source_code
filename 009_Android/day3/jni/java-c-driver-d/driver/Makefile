export ARCH=arm
export CROSS_COMPILE=arm-linux-gnueabi-
#KERNELDIR:= /lib/modules/$(shell uname -r)/build/
KERNELDIR:= /home/linux/fspad-733/lichee/linux-3.4/
PWD:=$(shell pwd)
modules:
	make -C $(KERNELDIR) M=$(PWD) modules
clean:
	make -C $(KERNELDIR) M=$(PWD) clean
obj-m:=myled.o
