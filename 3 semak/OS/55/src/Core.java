import java.util.ArrayList;
import java.util.Random;

public class Core {
    //все процессы
    ArrayList<Process> arrProcess = new ArrayList<>();
    //для храненния заблокированных процесов
    ArrayList<Process> blockedProcess = new ArrayList<>();
    Random rand = new Random();
    private int sumTime = 0;

    public void createProcess() {
        for (int i = 0; i < 3 + rand.nextInt( 15 ); i++) {
            Process processNew = new Process( i, 1 );
            processNew.setBlocked( rand.nextBoolean() );
            arrProcess.add( processNew );
            arrProcess.get( i ).start();
            sumTime += arrProcess.get( i ).getSumTime();
        }
    }

    public void executePlanProcess() {
        //выполнение работы прерываний с заблокированными и незаблокированными процессами
        System.out.print( "=====================================================\n" );
        System.out.print( "Работа прерываний с блокировкой процессов:" + "\n" );
        System.out.print( "=====================================================\n" );
        executeBlockedProcess();

        System.out.print( "\n\n\n=====================================================\n" );
        System.out.print( "Работа прерываний без блокировки:" + "\n" );
        System.out.print( "=====================================================\n" );
        executeUnBlockedProcess();
    }

    public void executeBlockedProcess() {
        Device device = new Device();
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println( "Процесс " + arrProcess.get( i ).getID() + " начал свою работу" );
            if (arrProcess.get( i ).getIsBlocked()) {
                System.out.println( "Процесс " + arrProcess.get( i ).getID() + " был заблокирован " );
                System.out.println( "Устройство начало свою работу.Пожалуйста, дождитесь выполнения операции" );
                arrProcess.get( i ).setNecessaryTime( device.executingTimeOperationDevice() );
                blockedProcess.add( arrProcess.get( i ) );
            } else {
                for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                    System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getID() + " процесса " + arrProcess.get( i ).getID() + " был запущен" );
                }
                System.out.print( "Процесс " + arrProcess.get( i ).getID() + " успешно завершил свою работу!" + "\n" );
            }
            workingBlockedProcess();
        }
        workingBlockedProcess();
        System.out.print( "\n" );
    }

    public void workingBlockedProcess() {
        System.out.print( "\n" + "Возобновление работы процессов..." + "\n" );
        if (!blockedProcess.isEmpty()) {
            Process currProcess = blockedProcess.get( 0 );
            for (int j = 0; j < currProcess.getArrThread().size(); j++) {
                System.out.println( "Поток " + currProcess.getArrThread().get( j ).getID() + " процесса " + currProcess.getID() + " был запущен" );
            }
            System.out.print( "Процесс " + currProcess.getID() + " успешно завершил свою работу!" + "\n" );
            blockedProcess.remove( currProcess );
        }
    }

    public void executeUnBlockedProcess() {
        Device device = new Device();
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println( "Процесс " + arrProcess.get( i ).getID() + " начал свою работу" );
            if (arrProcess.get( i ).getIsBlocked()) {
                System.out.println( "Процесс " + arrProcess.get( i ).getID() + " был заблокирован " );
                System.out.println( "Устройство начало свою работу. Пожалуйста, дождитесь выполнения операции" );
                for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                    System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getID() + " процесса " + arrProcess.get( i ).getID() + " был запущен" );
                }
            } else {
                for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                    System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getID() + " процесса " + arrProcess.get( i ).getID() + " был запущен" );
                }
            }
        }
        System.out.print( "\n\n" );
    }

    public void printPlanProcess() {
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println( "Процесс " + arrProcess.get( i ).getID() );
            int timeProcess = 0;
            for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getID() + " время: " + arrProcess.get( i ).getArrThread().get( j ).getTime() );
                timeProcess += arrProcess.get( i ).getArrThread().get( j ).getTime();
            }
            System.out.println( "Итого процесс " + arrProcess.get( i ).getID() + " выполнился за время: " + timeProcess + "\n" );
        }
    }

    public void printTimeExecuteProcess() {
        int timeWithoutBlock = 0;
        int timeWithBlock = 0;
        for (int i = 0; i < arrProcess.size(); i++) {
            int timeProcess = 0;
            for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                timeProcess += arrProcess.get( i ).getArrThread().get( j ).getTime();
                timeProcess += arrProcess.get( i ).getNecessaryTime();
            }
            String state = arrProcess.get( i ).getIsBlocked() ? " заблокирован " : "незаблокирован";
            System.out.println( "Итого процесс " + arrProcess.get( i ).getID() + ", с состоянием: " + state + " выполнился за время " + timeProcess + "\n" );
            if (arrProcess.get( i ).getIsBlocked())
                timeWithBlock += timeProcess;
            else timeWithoutBlock += timeProcess;
        }
        System.out.print( "Итоговое время работы с блокировкой и без:\n" );
        System.out.print( "с блокировкой :" + timeWithBlock + "\n" );
        System.out.print( "без блокировки :" + timeWithoutBlock + "\n" );
    }

    public void startProgram() {
        createProcess();
        executePlanProcess();
        printTimeExecuteProcess();
        Process process = new Process();
        process.planProcessThread( arrProcess );
    }
}