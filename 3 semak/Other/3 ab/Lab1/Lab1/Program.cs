using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

//Разработать программу, иллюстрирующую работу с системными вызовами. Создать 5
//системных вызовов с несколькими аргументами. Каждый системный вызов должен иметь
//числовой идентификатор. Ядро операционной системы должно иметь 2 метода: вывод списка
//системных вызовов (с перечислением аргументов и описанием) и выполнение системного
//вызова по его уникальному идентификатору. Аргументы передаются в ядро через стек. В
//случае некорректных данных выводить ошибку.



namespace Lab1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Stack stack = new Stack(10);
            Kernel kernel = new Kernel(stack);
            kernel.ShowAll();

            stack.Push(new Arg("1", "Integer", 5));
            stack.Push(new Arg("Lab", "Integer", 10));
            kernel.Call(17);

            stack.Push(new Arg("Copy", "Integer", 10));
            stack.Push(new Arg("Color", "Integer", 20));
            stack.Push(new Arg("Out", "String", 100));
            kernel.Call(17);
            kernel.Call(27);

            stack.Push(new Arg("1", "Integer", 120));
            stack.Push(new Arg("Lab", "Datetime", 10));
            kernel.Call(72);
            Console.Read();
        }
    }
}
