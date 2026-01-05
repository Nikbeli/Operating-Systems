using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    internal class Program
    {
        static void Main(string[] args)
        {
            PagesStack MyPages = new PagesStack("Memore-manager", 10);
            PagesStack MyPages2 = new PagesStack("Chek", 10);
            MemoryManager manager = new MemoryManager("PC", 4096, MyPages);

            Page page1 = new Page("[1]", 1024, 0);
            MyPages2.Add(page1);
            manager.pages = MyPages2;
            if (manager.Check() == false) { manager.Run(); MyPages.Add(page1); manager.pages = MyPages; manager.Print(); }
            else { MyPages.Add(page1); manager.pages = MyPages; manager.Print(); }

            Page page2 = new Page("[2]", 512, 0);
            MyPages2.Add(page2);
            manager.pages = MyPages;
            if (manager.Check() == false) { manager.Run(); MyPages.Add(page2); manager.pages = MyPages; manager.Print(); }
            else { MyPages.Add(page2); manager.pages = MyPages; manager.Print(); }

            Page page3 = new Page("[3]", 1024, 0);
            MyPages2.Add(page3);
            manager.pages = MyPages;
            if (manager.Check() == false) { manager.Run(); MyPages.Add(page3); manager.pages = MyPages; manager.Print(); }
            else { MyPages.Add(page3); manager.pages = MyPages; manager.Print(); }

            Page page4 = new Page("[4]", 2048, 0);
            MyPages2.Add(page4);
            manager.pages = MyPages;
            if (manager.Check() == false) { manager.Run(); MyPages.Add(page4); manager.pages = MyPages; manager.Print(); }
            else { MyPages.Add(page4); manager.pages = MyPages; manager.Print(); }

            Page page5 = new Page("[5]", 256, 0);
            MyPages2.Add(page5);
            if (manager.Check() == false) { manager.Run(); MyPages.Add(page5); manager.pages = MyPages; manager.Print(); }
            else { MyPages.Add(page5); manager.pages = MyPages; manager.Print(); }

            if (manager.Check() == false) { manager.Run();}
            else { manager.Print(); }

            Console.ReadLine();
        }
    }
}
