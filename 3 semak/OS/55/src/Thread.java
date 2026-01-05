public class Thread {
    private int ID;
    private int time;

    public Thread(int threadID, int time) {
        this.ID = threadID;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getID() {
        return ID;
    }
}