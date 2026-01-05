public class MyProcess {
    protected String name;
    public int time;
    private MyThread[] threads;
    public int allTime = 0;
    private int number;

    private int head = 0;

    // 0 - не выполнено
    // 1 - в процессе
    // 2 - выполнено
    public int flag = 0;

    public MyProcess(String name, int time, int number) {
        this.name = name;
        this.time = time;
        this.threads = new MyThread[number];
    }

    public void AddThread(MyThread th) {
        this.threads[this.head++] = th;
    }

    public void Check() {
        if (head == 0) {return;}; //если нечего проверять
        int sum = 0;
        for (int i = 0; i < head; i++) {
            sum += threads[i].time;
        }

        if (sum > time) {
            name += " (edited [:" + time + " ms])";
            time = sum;
        }
    }

    public void Sorting() {
        Check();
        if (head == 0 || head == 1) {return;}; //если нечего сортировать
        //сортировка (по возрастанию) потоков по макс.времени выполнения
        for (int i = 0; i < head - 1; i++) {
            for (int j = i + 1; j < head; j++) {
                if (threads[i].time > threads[j].time) {
                    MyThread tmp = threads[i];
                    threads[i] = threads[j];
                    threads[j] = tmp;
                }
            }
        }
        //если имеются одинаковые параметры времени
        //то сортируем (по возрастанию) их по количеству нитей, исходящих от потоков
        for (int i = 0; i < head - 1; i++) {
            for (int j = i + 1; j < head; j++) {
                if (threads[i].max_time == threads[j].max_time) {
                    if (threads[i].stack.head > threads[j].stack.head) {
                        MyThread tmp = threads[i];
                        threads[i] = threads[j];
                        threads[j] = tmp;
                    }
                }
            }
        }

        Run();
    }

    public void Run() {
        flag = 1;
        for (int i = 0; i < number; i++) {
            this.threads[i].IsCheckThread = true;
        }

        flag = 2;
    }

    public void Print() {
        for (int i = 0; i < this.head; i++)
        {
            System.out.println("    " + (i+1) + ") " + this.threads[i].name + " = " + this.threads[i].time + " ms (" +
                    (this.threads[i].stack.head+1) + " fibers)");
        }
    }

    public void PrintProcess()
    {
        for (int i = 0; i < this.head; ++i)
        {
            System.out.print("       " + (i + 1) + ") " + this.threads[i].name + " = " + this.threads[i].time + " ms \t");
            for (int k = 0; k < ((allTime/5)); ++k)
            {
                System.out.print(" ");
            }

            //Console.Write("Start");
            System.out.print("     ");

            for (int k = 0; k < this.threads[i].time; k += 5)
            {
                System.out.print("-");
            }

            allTime += this.threads[i].time;
            //Console.Write("End");
            System.out.println();
        }

    }
}
