#include <linux/module.h>
#include <linux/vermagic.h>
#include <linux/compiler.h>

MODULE_INFO(vermagic, VERMAGIC_STRING);

struct module __this_module
__attribute__((section(".gnu.linkonce.this_module"))) = {
 .name = KBUILD_MODNAME,
 .init = init_module,
#ifdef CONFIG_MODULE_UNLOAD
 .exit = cleanup_module,
#endif
 .arch = MODULE_ARCH_INIT,
};

static const struct modversion_info ____versions[]
__used
__attribute__((section("__versions"))) = {
	{ 0x3bc25e7e, "module_layout" },
	{ 0x6bc3fbc0, "__unregister_chrdev" },
	{ 0x28a4bd9f, "class_destroy" },
	{ 0xca5217dc, "device_destroy" },
	{ 0xc2165d85, "__arm_iounmap" },
	{ 0x40a6f522, "__arm_ioremap" },
	{ 0x98169939, "device_create" },
	{ 0xec309150, "__class_create" },
	{ 0xed213849, "__register_chrdev" },
	{ 0x2e5810c6, "__aeabi_unwind_cpp_pr1" },
	{ 0x27e1a049, "printk" },
	{ 0xb1ad28e0, "__gnu_mcount_nc" },
};

static const char __module_depends[]
__used
__attribute__((section(".modinfo"))) =
"depends=";


MODULE_INFO(srcversion, "8FE4A8A58FE5B6CF5B6E232");