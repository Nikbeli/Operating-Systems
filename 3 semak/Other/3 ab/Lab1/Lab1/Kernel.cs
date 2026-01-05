using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    internal class Kernel
    {
        Stack stack;

        ArrayList SystemCalls = new ArrayList()
        {new SystemCall(7,"Open file","String"),
            new SystemCall(17,"Delete file","String"),
            new SystemCall(27,"Save file","Integer"),
            new SystemCall(37,"Print file","String"),
            new SystemCall(47,"Start process","String")
        };

        public Kernel(Stack stack)
        {
            this.stack = stack;
        }

        public void Call(int id)
        {
            foreach (SystemCall sc in SystemCalls)
            { 
                if (sc.id == id)
                { 
                    Console.WriteLine("__________________________");
                    Console.WriteLine("Вызов:(" + id + ")");

                    Arg element = stack.Pop();
                    if (element.type != sc.args)
                    {
                        Console.WriteLine("Ошибка! Не подходящий аргумент");
                        return;
                    }

                    Console.WriteLine("Вызов (ID: " + id + ") прошел успешно!");
                    return;
                }
            }
            Console.WriteLine("__________________________");
            Console.WriteLine("Ошибка! Вызов (ID: " + id + ") не найден!");
        }
        public void ShowAll()
        {
            Console.WriteLine("Список системых вызовов:");
            foreach (SystemCall sc in SystemCalls)
            { 
                Console.WriteLine("__________________________");
                Console.WriteLine("ID:" + sc.id +
                                   "\nИмя: " + sc.name +
                                   "\nАргумент: " + sc.args);
            }
            Console.WriteLine("__________________________");
        }

    }
}
