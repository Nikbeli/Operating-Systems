public class Scheduler {
    private int number;
    private MyProcess[] processes;
    private int head = 0;

    public Scheduler(int number) {
        this.processes = new MyProcess[number];
    }

    public void AddProc(MyProcess proc) {
        this.processes[head++] = proc;
    }

    public void Sorting() {
        if (head == 0 || head == 1) {return;}; //если нечего сортировать
        for (int i = 0; i < head - 1; i++) {
            for (int j = i + 1; j < head; j++) {
                if (this.processes[i].time > this.processes[j].time)
                {
                    MyProcess tmp = this.processes[i];
                    this.processes[i] = this.processes[j];
                    this.processes[j] = tmp;
                }
            }
        }
    }

    public void PrintMore() {
        for (int i = 0; i < head; i++)
        {
            System.out.println("[" + (i+1) + "]" + this.processes[i].name + " = " + this.processes[i].time + " ms");
            this.processes[i].Print();
        }
    }

    public void PrintGraphic()
    {
        int probel = 0;
        for (int i = 0; i < this.head; ++i)
        {
            System.out.print("[" + (i+1) + "]" + this.processes[i].name + " = " + this.processes[i].time + " ms \t\t");

            System.out.print("Start");

            for (int k = 0; k < this.processes[i].time; k += 5)
            {
                System.out.print("-");
                probel++;
            }

            System.out.print("End");
            System.out.println();
            this.processes[i].PrintProcess();
        }
    }
}
