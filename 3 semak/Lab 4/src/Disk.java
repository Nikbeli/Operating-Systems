import javax.swing.*;
import java.awt.*;
import java.nio.channels.FileChannel;

public class Disk extends JPanel {
    // Количество пустых кластеров диска
    private int countOfEmptySectors;

    // Массив кластеров
    private FileCluster[] sectorsOfDisk;

    public Disk(int diskPartitionSize, int diskSectorSize) {
         countOfEmptySectors = diskPartitionSize / diskSectorSize;
         sectorsOfDisk = new FileCluster[countOfEmptySectors];

         for(int i = 0; i < sectorsOfDisk.length; i++) {
             sectorsOfDisk[i] = new FileCluster(0,true);
             sectorsOfDisk[i].setNextClusterNumberOnDisk(-1);
         }
    }

    public void addToDisk(TreeNode file) {
        if(countOfEmptySectors < file.getFileSize()) {
            JOptionPane.showMessageDialog(this, "Недостаточно места на диске", "Ошибка", JOptionPane.ERROR_MESSAGE);
        } else {
            FileCluster tempFileCluster = file.getFirstFileCluster();
            for(int i = 0; i < file.getFileSize(); i++) {
                for(int j = 0; j < sectorsOfDisk.length; j++) {
                    if(sectorsOfDisk[j].isEmpty()) {
                        sectorsOfDisk[j] = tempFileCluster;
                        assert tempFileCluster != null;
                        if(tempFileCluster.getNextCluster() != null) {
                            for(int k = 0; k < sectorsOfDisk.length; k++) {
                                if(sectorsOfDisk[k].isEmpty()) {
                                    sectorsOfDisk[j].setNextClusterNumberOnDisk(k);
                                }
                            }
                        }
                        tempFileCluster = tempFileCluster.getNextCluster();
                        break;
                    }
                }
            }
            countOfEmptySectors -= file.getFileSize();
        }
    }

    public void removeSelection() {
        for(FileCluster fileCluster : sectorsOfDisk) {
            if(!fileCluster.isEmpty()) {
                fileCluster.setTypeOfSelection(1);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int tempWidthOfCell = 20;
        int tempHeightOfCell = 20;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.GRAY);

        int x = 0;
        int y = 0;

        for(FileCluster fileCluster : sectorsOfDisk) {
            if (x + tempWidthOfCell + 5 >= this.getWidth()) {
                x = 0;
                y += tempHeightOfCell;
            }

            if (fileCluster.isEmpty()) {
                g2.setColor(Color.GRAY);
            } else {
                switch (fileCluster.getTypeOfSelection()) {
                    case 1:
                        g2.setColor(Color.BLUE);
                        break;
                    case 2:
                        g2.setColor(Color.RED);
                        break;
                    case 3:
                        g2.setColor(Color.GREEN);
                        break;
                    default:
                        g2.setColor(Color.GRAY);
                        break;
                }
            }
            g2.fillRect(x + 5, y + 5, tempWidthOfCell - 5, tempHeightOfCell - 5);
            x += tempWidthOfCell;
        }
    }

    public int getCountOfEmptySectors() { return countOfEmptySectors; }
}
