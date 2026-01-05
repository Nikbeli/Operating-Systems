using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    internal class SystemCall
    {
        public int id;
        public String name;
        public String args;

        public SystemCall(int id, String name, String args)
        {
            this.id = id;
            this.name = name;
            this.args = args;
        }
    }
}
