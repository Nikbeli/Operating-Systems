using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4
{
    internal class Disc
    {
        private int maxMemorySize;
        protected FileSegment[] physicalMemory;

        public Disc(int maxMemorySize)
        {
            this.maxMemorySize = maxMemorySize;
            this.physicalMemory = new FileSegment[maxMemorySize];
        }

        public void setAllFilesNotSelected()
        {
            for (int i = 0; i < getMaxMemorySize(); i++)
            {
                if (getPhysicalMemory()[i] != null)
                {
                    getPhysicalMemory()[i].setIsSelected(false);
                }
            }
        }
        public int getMaxMemorySize()
        {
            return maxMemorySize;
        }

        public FileSegment[] getPhysicalMemory()
        {
            return physicalMemory;
        }

        public FileSegment getSegmentFromPhysicalMemory(int index)
        {
            return physicalMemory[index];
        }

        public int searchFreeSegmentInPhysicalMemory()
        {
            for (int i = 0; i < maxMemorySize; i++)
            {
                if (physicalMemory[i] == null)
                {
                    if (i > (maxMemorySize / 10) * 9)
                    {
                        //JOptionPane.showMessageDialog(frame, "Зтаканчиваеся место для записи");
                    }
                    return i;
                }
            }
            //JOptionPane.showMessageDialog(frame, "Нет места в памяти для новой записи");
            return -1;
        }

        public void insertInPhysicalMemory(FileSegment newSegment, int index, int size)
        {
            if (size == 1)
            {
                physicalMemory[index] = newSegment;
                return;
            }
            for (int i = 0; i < size; i++)
            {
                Random rnd = new Random();
                int ind = rnd.Next(maxMemorySize - 1);
                while (physicalMemory[ind] != null)
                {
                    ind = rnd.Next(maxMemorySize - 1);
                }
                physicalMemory[ind] = newSegment;
            }
        }

        public void deleteFromPhysicalMemory(int index)
        {
            physicalMemory[index] = null;
        }

        public int CheckIndex(String node_name)
        {
            for (int index = 0; index < maxMemorySize; index++)
            {
                if (physicalMemory[index] != null && node_name.Equals(physicalMemory[index].getName()))
                {
                    return index;
                }
            }
            return -1;
        }
    }
}
