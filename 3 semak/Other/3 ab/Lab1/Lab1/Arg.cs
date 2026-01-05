using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    internal class Arg
    {
        String name;
        public String type;
        int value;

        public Arg(String name, String type, int value)
        {
            this.name = name;
            this.type = type;
            this.value = value;
        }
    }
}
