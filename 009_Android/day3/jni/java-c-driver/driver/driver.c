#include <linux/init.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/device.h>

#define NAME "hello"
int major = 0;
struct class *cls = NULL;
struct device *dev = NULL;

int demo_open(struct inode *inode, struct file *file)
{
	printk("%s:%s:%d\n",__FILE__,__func__,__LINE__);
	return 0;
}

long demo_ioctl(struct file *file, unsigned int cmd, unsigned long args)
{
	printk("%s:%s:%d\n",__FILE__,__func__,__LINE__);
	printk("cmd = %d,args = %ld\n",cmd,args);
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

	return 0;
}

static void __exit demo_exit(void)
{
	device_destroy(cls,MKDEV(major,0));
	class_destroy(cls);
	unregister_chrdev(major,NAME);
}
module_init(demo_init);
module_exit(demo_exit);
MODULE_LICENSE("GPL");
