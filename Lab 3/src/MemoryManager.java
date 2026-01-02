public class MemoryManager {
    public String name;
    public int RAM;
    private PagesStack pages;

    public MemoryManager(String name, int RAM, PagesStack pages) {
        this.name = name;
        this.RAM = RAM;
        this.pages = pages;
    }

    public boolean Check() {
        if (pages.MemorySum() > RAM) {
            System.out.println(">>>ERROR!!! NOT MEMORY!<<<");
            return false;
        }
        return true;
    }

    public void Run() {
        if(!Check()) {
            return;
        }
        pages.NFU();
        System.out.println("OK!");
        Print();
    }

    public void Print() {
            System.out.println("================-LAB_3-=================");
            System.out.println("Name: " + name + " | RAM: " + RAM + " MB");
            System.out.println("========================================");
            System.out.println("    "); pages.Print();
            System.out.println("=================-END-===================");
    }
}
