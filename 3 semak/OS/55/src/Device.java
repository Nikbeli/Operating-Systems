import java.util.Random;

public class Device {

    private int timeExecuteOperation;
    private Random rand = new Random();

    public Device() {
        timeExecuteOperation = rand.nextInt( 10 );
    }

    public int executingTimeOperationDevice() {
        System.out.println( "Операция выполнена, работа устройства ввода-вывода закончена, время выполнения = " + timeExecuteOperation );
        return timeExecuteOperation;
    }
}