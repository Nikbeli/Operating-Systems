import java.util.ArrayList;
import java.util.Random;

public class Process {
    private int ID;
    private int time = 0;
    private int sumTime = 0;
    private int necessaryTime = 0;
    private boolean isBlocked;
    public ArrayList<Thread> arrThread = new ArrayList<>();
    Random rand = new Random();

    public Process(int processID, int time) {
        this.ID = processID;
        this.time = time;
    }

    public Process() {

    }

    public ArrayList<Thread> getArrThread() {
        return arrThread;
    }

    public void setNecessaryTime(int timeThread) {
        necessaryTime += timeThread;
    }

    public int getNecessaryTime() {
        return necessaryTime;
    }

    public void setSumTime(int time) {
        this.sumTime = time;
    }

    public int getSumTime() {
        return sumTime;
    }

    public int getID() {
        return ID;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void createThread() {
        for (int i = 0; i < 1 + rand.nextInt( 10 ); i++) {
            int time = 1 + rand.nextInt( 5 );
            arrThread.add( new Thread( i, time ) );
            sumTime += time;
        }
    }

    public void planProcessThread(ArrayList<Process> arrProcess) {
        while (!arrProcess.isEmpty()) {
            for (int i = 0; i < arrProcess.size(); i++) {
                for (int j = 0; j < arrProcess.get( i ).arrThread.size(); j++) {
                    int timeThr = arrProcess.get( i ).arrThread.get( j ).getTime();
                    arrProcess.get( i ).setNecessaryTime( timeThr );
                    arrProcess.get( i ).arrThread.remove( j );
                    arrProcess.get( i ).setSumTime( arrProcess.get( i ).getSumTime() - timeThr );
                    sumTime -= timeThr;
                    if (arrProcess.get( i ).getSumTime() == 0) {
                        arrProcess.remove( i );
                        break;
                    }
                }
            }
        }
    }

    public void start() {
        createThread();
    }
}