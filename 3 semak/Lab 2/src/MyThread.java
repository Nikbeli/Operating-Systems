public class MyThread {
    protected String name;
    protected int max_time;
    protected Fibers stack;
    public int time;

    // состоянии потока
    public boolean IsCheckThread = false;

    public MyThread(String name, int time, Fibers stack) {
        this.name = name;
        this.time = time;
        this.stack = stack;
    }
}
