using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    internal class Page
    {
        public String name;
        public int memory_size;
        public int time;
        public bool flag = true;

        public Page(String name, int memory_size, int time)
        {
            this.name = name;
            this.memory_size = memory_size;
            this.time = time;
        }
    }
}
