using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4
{
    internal class FileSegment
    {
        private int memoryIndex;
        private int nextIndex = -1;
        private bool isSelected = false;
        private String node_name;

        public FileSegment(int memoryIndex, String node_name)
        {
            this.memoryIndex = memoryIndex;
            this.node_name = node_name;
        }

        public bool getIsSelect()
        {
            return isSelected;
        }

        public void setIsSelected(bool isSelected)
        {
            this.isSelected = isSelected;
        }

        public int getMemoryIndex()
        {
            return memoryIndex;
        }

        public int getNextIndex()
        {
            return nextIndex;
        }

        public void setNextIndex(int nextIndex)
        {
            this.nextIndex = nextIndex;
        }

        public String getName()
        {
            return node_name;
        }
    }
}
