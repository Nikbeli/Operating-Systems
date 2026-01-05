import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    Disk disk;

    public MyPanel(Disk disk) {
        this.disk = disk;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int fileSegmentX = 0;
        int fileSegmentY = 0;

        for (int i = 0; i < disk.getMaxMemorySize(); i++) {

            if (disk.getPhysicalMemory()[i] == null) {
                g.setColor(Color.GRAY);
            }

            if (disk.getPhysicalMemory()[i] != null) {
                g.setColor(Color.BLUE);
                if (disk.getPhysicalMemory()[i].getIsSelect()) {
                    g.setColor(Color.RED);
                }
            }

            g.fillRect(fileSegmentX, fileSegmentY, 10, 10);
            fileSegmentX += 15;
            if (i == 49 || (i - 49) % 50 == 0){
                fileSegmentY += 15;
                fileSegmentX = 0;
            }
        }
    }
}