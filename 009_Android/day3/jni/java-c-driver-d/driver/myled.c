#include <linux/init.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/device.h>
#include <linux/io.h>

#define CON 0x01c208b4
#define DAT 0x01c208c4
#define NAME "hello"
int major = 0;
struct class *cls = NULL;
struct device *dev = NULL;
unsigned int *con;
unsigned int *dat;  	

int demo_open(struct inode *inode, struct file *file)
{
	printk("%s:%s:%d\n",__FILE__,__func__,__LINE__);
	return 0;
}

long demo_ioctl(struct file *file, unsigned int cmd, unsigned long args)
{
	printk("%s:%s:%d\n",__FILE__,__func__,__LINE__);
	printk("cmd = %d,args = %ld\n",cmd,args);
	if(args == 0){
		*dat |= (1<<2);
	
	}else{
		*dat &= ~(1<<2);
	}
	return 0;
}

int demo_close(struct inode *inode, struct file *file)
{
	printk("%s:%s:%d\n",__FILE__,__func__,__LINE__);
	return 0;
}

static struct file_operations fops = {
	.open = demo_open,
	.unlocked_ioctl = demo_ioctl,
	.release = demo_close,
};

static int __init demo_init(void)
{
	major = register_chrdev(major,NAME,&fops);
	if(major <= 0){
		printk("register char device error\n");
		return -EAGAIN;
	}

	cls = class_create(THIS_MODULE,NAME);
	if(IS_ERR(cls)){
		printk("class create error\n");
		return PTR_ERR(cls);
	}
	
	dev = device_create(cls,NULL,MKDEV(major,0),NULL,NAME);
	if(IS_ERR(dev)){
		printk("device create error\n");
		return PTR_ERR(dev);
	}
	con = ioremap(CON,4);
	if(con == NULL){
		printk("ioremap con error\n");
		return -ENOMEM;
	}
	dat = ioremap(DAT,4);
	if(con == NULL){
		printk("ioremap data error\n");
		return -ENOMEM;
	}

	//led init
	*con &= ~(0x7<<8);
	*con |= (0x1<<8);
	*dat |= (1<<2);

	return 0;
}

static void __exit demo_exit(void)
{
	iounmap(con);
	iounmap(dat);
	device_destroy(cls,MKDEV(major,0));
	class_destroy(cls);
	unregister_chrdev(major,NAME);
}
module_init(demo_init);
module_exit(demo_exit);
MODULE_LICENSE("GPL");
