public class Main {
    public static void main(String[] args) {
        Page page1 = new Page("[1]", 100, 10);
        Page page2 = new Page("[2]", 1000, 100);
        Page page3 = new Page("[3]", 400, 40);
        Page page4 = new Page("[4]", 400, 50);
        Page page5 = new Page("[5]", 100, 15);

        PagesStack MyPages = new PagesStack("WebSite", 10);
        MyPages.Add(page1);
        MyPages.Add(page2);
        MyPages.Add(page3);
        MyPages.Add(page4);
        MyPages.Add(page5);

        MemoryManager manager = new MemoryManager("Memory Manager", 2048, MyPages);
        manager.Print();
        manager.Run();
        manager.Run();
        manager.Run();
        manager.Run();
        manager.Run();
    }
}
