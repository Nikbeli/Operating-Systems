import java.util.Random;

public class Page {
    public String name;
    int memory_size;
    public int time;
    int appeal;
    boolean flag = true;

    public Page(String name, int memory_size, int time) {
        this.name = name;
        this.memory_size = memory_size;
        this.time = time;
        Random rnd = new Random();
        this.appeal = rnd.nextInt(20);
    }
}