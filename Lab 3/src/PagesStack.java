public class PagesStack {
    public String name;
    private Page[] pages;
    private int size;
    private int head = 0;

    public PagesStack(String name, int size) {
        this.name = name;
        pages = new Page[size];
    }

    public void Add(Page page) {
        pages[head++] = page;
    }

    //поиск страницы с наименьшим счётчиком посещения
    public int CheckMin() {
        if (head == 0) { return -1; }

        int min = 100;
        int indexMin = 0;
        for (int i = 0; i < head; i++) {
            if (pages[i].flag == true && pages[i].appeal < min) {
                min = pages[i].appeal;
                indexMin = i;
            }
        }
        return indexMin;
    }

    //алгоритм нечастого востребования  (замещения страниц)
    //выталкивание редко используемой страницы
    public void NFU() {
        pages[CheckMin()].flag = false; //прерывание
    }

    public int MemorySum() {
        if (head == 0) { return -1; }
        int sum = 0;
        for (int i = 0; i < head; i++) {
            sum += pages[i].memory_size;
        }
        return sum;
    }

    // Вывод
    public void Print() {
        for (int i = 0; i < head; i++) {
            if (pages[i].flag) {
                System.out.println("Name: " + pages[i].name + " | Memory size: " +
                            pages[i].memory_size + " MB | (" + pages[i].appeal + ")" + "  " + pages[i].time + "mc");
            }
        }
    }
}
