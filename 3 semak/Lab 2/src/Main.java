public class Main {

    public static void main(String[] args) {
        Fibers f1 = new Fibers(3);
        f1.Push("print");
        f1.Push("copy");
        Fibers f2 = new Fibers(3);
        f2.Push("open");
        Fibers f3 = new Fibers(3);
        f3.Push("save");
        f3.Push("delete");
        f3.Push("insert");

        MyThread th1 = new MyThread("Thread N1", 40, f1);
        MyThread th2 = new MyThread("Thread N2", 10, f2);
        MyThread th3 = new MyThread("Thread N3", 90, f3);

        MyProcess proc1 = new MyProcess("Start BeginOS", 140, 5);
        proc1.AddThread(th3);
        proc1.AddThread(th2);
        proc1.AddThread(th1);
        proc1.Sorting();

        MyThread th4 = new MyThread("Thread N4", 50, f1);
        MyThread th5 = new MyThread("Thread N5", 30, f2);
        MyThread th6 = new MyThread("Thread N6", 70, f3);


        MyProcess proc2 = new MyProcess("Process OS №1", 150, 5);
        proc2.AddThread(th4);
        proc2.AddThread(th5);
        proc2.AddThread(th6);
        proc2.Sorting();

        MyThread th7 = new MyThread("Thread N7", 50, f1);
        MyThread th8 = new MyThread("Thread N8", 40, f2);
        MyThread th9 = new MyThread("Thread N9", 100, f3);

        MyProcess proc3 = new MyProcess("Process OS №2", 190, 5);
        proc3.AddThread(th7);
        proc3.AddThread(th8);
        proc3.AddThread(th9);
        proc3.Sorting();

        Scheduler Lab = new Scheduler(3);
        Lab.AddProc(proc1);
        Lab.AddProc(proc2);
        Lab.AddProc(proc3);
        Lab.Sorting();
        Lab.PrintMore();

        System.out.println("------Graphic------");
        Lab.PrintGraphic();
    }
}
