using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4
{
    internal class TreeData
    {
        private String left;
        private int right;

        public TreeData(String left, int memory)
        {
            this.left = left;
            this.right = memory;
        }

        public String getLeft()
        {
            return left;
        }

        public int getRight()
        {
            return right;
        }
    }
}
