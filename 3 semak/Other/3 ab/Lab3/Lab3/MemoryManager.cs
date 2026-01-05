using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    internal class MemoryManager
    {
        public String name;
        public int RAM;
        public PagesStack pages;

        public MemoryManager(String name, int RAM, PagesStack pages)
        {
            this.name = name;
            this.RAM = RAM;
            this.pages = pages;
        }

        public void RandomRAM()
        {
            Random random = new Random();
        }
        
        public bool Check()
        {
            if (pages.MemorySum() > RAM)
            {
                Console.WriteLine(">>>ERROR!!! NOT MEMORY!<<<");
                return false;
            }
            return true;
        }

        public void Run()
        {
            pages.LRU();
            pages.RandomRAN();
            Console.WriteLine("Used LRU");
            Print();
        }

        public void Print()
        {
            Console.WriteLine("========================================");
            Console.WriteLine("Name: " + name + " | RAM: " + RAM + " MB");
            Console.WriteLine("========================================");
            Console.WriteLine("    "); pages.Print();
            Console.WriteLine("========================================");
        }
    }
}
