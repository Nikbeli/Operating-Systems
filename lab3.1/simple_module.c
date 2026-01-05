#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("User-student");
MODULE_DESCRIPTION("A simple Linux kernel module");
MODULE_VERSION("1.0");

static int __init simple_module_init(void) {
    printk(KERN_INFO "Hello, kernel! \n");
    return 0;
}

static void __exit simple_module_exit(void) {
    printk(KERN_INFO "Goodbye, kernel! \n");
}

module_init(simple_module_init);
module_exit(simple_module_exit);