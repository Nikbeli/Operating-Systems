obj-m += simple_module.o

all:
	make -C /lib/modules/$(shell uname -r)/build M=$(HOME)/'Рабочий стол'/lab3.1 modules

clean:
	make -C /lib/modules/$(shell uname -r)/build M=$(HOME)/lab3.1 clean

