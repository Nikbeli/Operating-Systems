using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    internal class PagesStack
    {
        public String name;
        private Page[] pages;
        private int size;
        private int head = 0;

        public PagesStack(String name, int size)
        {
            this.name = name;
            pages = new Page[size];
        }

        public void Add(Page page)
        {
            pages[head++] = page;
        }

        public void RandomRAN()
        {
            Random random = new Random();
            for (int i = 0; i < head - 1; i++)
            {
                do
                {
                    pages[i].memory_size -= random.Next(0, 64);
                }
                while (pages[i].memory_size % 2 != 0);
            }
        }

        public int CheckMax()
        {
            if (head == 0) { return -1; }

            int max = 0;
            int indexMax = 0;
            for (int i = 0; i < head-1; i++)
            {
                if (pages[i].flag == true && pages[i].time > max)
                {
                    max = pages[i].time;
                    indexMax = i;
                }
            }
            return indexMax;
        }

        public void LRU()
        {
            pages[CheckMax()].flag = false; 
        }

        public int MemorySum()
        {
            if (head == 0) { return -1; }
            int sum = 0;
            for (int i = 0; i < head; i++)
            {
                sum += pages[i].memory_size;
            }
            return sum;
        }

        public void Print()
        {
            for (int i = 0; i < head; i++)
            {
                if (pages[i].flag)
                {
                    Console.WriteLine("Page: " + pages[i].name + " | Memory size: " +
                                pages[i].memory_size + " MB | Time: " + (pages[i].time) + "ms");
                    (pages[i].time) += 10;
                }
            }
        }
    }
}
