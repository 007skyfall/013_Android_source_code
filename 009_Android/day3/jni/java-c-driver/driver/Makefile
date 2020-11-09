KERNELDIR:= /lib/modules/$(shell uname -r)/build/
PWD:=$(shell pwd)
modules:
	make -C $(KERNELDIR) M=$(PWD) modules
clean:
	make -C $(KERNELDIR) M=$(PWD) clean
obj-m:=driver.o
