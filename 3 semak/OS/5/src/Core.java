import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Core {
    Random random = new Random();

    private Queue<Process> queueWithBlock = new LinkedList<Process>();
    private Queue<Process> queueWithoutBlock = new LinkedList<Process>();

    public void createProcess() {
        for (int i = 1; i <= random.nextInt(10) + 1; i++) {
            boolean workWithDevices = random.nextBoolean();
            int timeBeforeWorkWithDevices = random.nextInt(1001) + 500;
            int timeWorkWithDevices = random.nextInt(401) + 100;
            int timeAfterWorkWithDevices = random.nextInt(1001) + 500;
            Process processToQueueWithBlock = new Process(i, workWithDevices, timeBeforeWorkWithDevices, timeWorkWithDevices, timeAfterWorkWithDevices);
            queueWithBlock.add(processToQueueWithBlock);
            Process processToQueueWithoutBlock = new Process(i, workWithDevices, timeBeforeWorkWithDevices, timeWorkWithDevices, timeAfterWorkWithDevices);
            queueWithoutBlock.add(processToQueueWithoutBlock);
        }
    }

    public void printAllProcess() {
        List<Process> listForPrint = (List<Process>) queueWithBlock;
        System.out.println("Мы имеем " + listForPrint.size() + " процессов:");
        for (int i = 0; i < listForPrint.size(); i++) {
            System.out.println("Процесс " + listForPrint.get(i).getID() + " требует " + listForPrint.get(i).getTotalTime() + " такт(а)(ов) на выполнение" +
                    " - данный процесс " + listForPrint.get(i).getWorkWithDevices() + listForPrint.get(i).getTimeWorkWithDevicesToPrint());
        }
        System.out.println();
    }

    public void planingWithBlock() {
        int totalWorkTime = 0;
        System.out.println("\n====================================================");
        System.out.println("Начинает работать планировщик с блокировками \n");
        System.out.println("=====================================================\n");
        Process process = queueWithBlock.poll();
        while (process != null) {
            String result = process.executeProcessWithBlock();
            System.out.println(result + "\n");
            if (result.contains("из-за")) {
                totalWorkTime += process.getTimeWorkWithDevices();
                Process newProcess = queueWithBlock.peek();
                result = newProcess.executeProcessWithBreaking(process.getTimeWorkWithDevices());
                process.setTimeWorkWithDevices();
                System.out.println(result + "\n");
                result = process.continueExecuteProcessWithBlock();
                System.out.println(result + "\n");
            }
            if (result.contains("приостановлен")) {
                queueWithBlock.add(process);
            }
            totalWorkTime += process.getWorkTime();
            process = queueWithBlock.poll();
        }
        System.out.println("Общее время затраченное на выполнение процессов " + totalWorkTime + " такт(а)(ов) \n");
    }

    public void planingWithoutBlock() {
        int totalWorkTime = 0;
        System.out.println("\n====================================================");
        System.out.println("Начинает работать планировщик без блокировок \n");
        System.out.println("=====================================================\n");
        Process process = queueWithoutBlock.poll();
        while (process != null) {
            String result = process.executeProcessWithoutBlock();
            System.out.println(result + "\n");
            if (result.contains("приостановлен")) {
                queueWithoutBlock.add(process);
            }
            totalWorkTime += process.getWorkTime();
            process = queueWithoutBlock.poll();
        }
        System.out.println("Общее время затраченное на выполнение процессов " + totalWorkTime + " такт(а)(ов) \n");
    }
}