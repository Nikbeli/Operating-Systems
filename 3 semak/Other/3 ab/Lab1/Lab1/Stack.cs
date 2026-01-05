using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    internal class Stack
    {
        private int size;
        private Arg[] stack;
        private int head;

        public Stack(int size)
        {
            this.size = size;
            stack = new Arg[size];
            head = -1;
        }

        public void Push(Arg arg)
        {
            stack[++head] = arg;
        }

        public Arg Pop()
        {
            return stack[head--];
        }
    }
}
