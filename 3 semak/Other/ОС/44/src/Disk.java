import javax.swing.*;

public class Disk {
    private JFrame frame;
    private int maxMemorySize = 600;
    private FileSegment[] physicalMemory = new FileSegment[maxMemorySize];

    public Disk(JFrame frame) {
        this.frame = frame;
    }

    public void setAllFilesNotSelected(){
        for (int i = 0; i <getMaxMemorySize(); i++) {
            if (getPhysicalMemory()[i] != null) {
                getPhysicalMemory()[i].setIsSelected(false);
            }
        }
    }
    public int getMaxMemorySize() {
        return maxMemorySize;
    }

    public FileSegment[] getPhysicalMemory() {
        return physicalMemory;
    }

    public FileSegment getSegmentFromPhysicalMemory(int index) {
        return physicalMemory[index];
    }

    public int searchFreeSegmentInPhysicalMemory() {
        for (int i = 0; i < maxMemorySize; i++) {
            if (physicalMemory[i] == null) {
                if (i > (maxMemorySize / 10) * 9) {
                    JOptionPane.showMessageDialog(frame, "Заканчивается место для записи");
                }
                return i;
            }
        }
        JOptionPane.showMessageDialog(frame, "Нет места в памяти для новой записи");
        return -1;
    }

    public void insertInPhysicalMemory(FileSegment newSegment, int index) {
        physicalMemory[index] = newSegment;
    }

    public void deleteFromPhysicalMemory(int index) {
        physicalMemory[index] = null;
    }
}