import javax.swing.*;
import java.awt.*;

public class CellMeshMemoryPanel extends JPanel {
    private CellMeshMemory[] cellsMeshMemory;

    public CellMeshMemoryPanel(RAM ram) {
        if (ram != null)
            cellsMeshMemory = ram.getCellsMeshMemory();
    }

    public void paint(Graphics g) {
        //расшифровка состояния ячеек памяти
        g.setColor( Color.lightGray );
        g.fillRect( 30, 480, 40, 40 );
        g.setColor( Color.blue );
        g.drawRect( 30, 480, 40, 40 );
        g.setColor( Color.green );
        g.fillRect( 30, 540, 40, 40 );
        g.setColor( Color.blue );
        g.drawRect( 30, 540, 40, 40 );
        g.setColor( Color.red );
        g.fillRect( 30, 590, 40, 40 );
        g.setColor( Color.blue );
        g.drawRect( 30, 590, 40, 40 );
        int sizeX = (int) (600 / Math.sqrt( cellsMeshMemory.length ));
        int sizeY = (int) (600 / Math.sqrt( cellsMeshMemory.length ));
        if (cellsMeshMemory.length <= 40) {
            sizeX = 100;
            sizeY = 100;
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < cellsMeshMemory.length; i++) {
            if (x + sizeX >= 900 - sizeX) {
                x = 0;
                y += sizeY;
            }
            if (cellsMeshMemory[i].getStatusCellMeshMemory() == 2) { // выбранная ячейка памяти диска
                g.setColor( Color.red );
            } else if (cellsMeshMemory[i].getStatusCellMeshMemory() == 0) { // пустая ячейка памяти диска
                g.setColor( Color.lightGray );
            } else {
                g.setColor( Color.green ); // занятая ячейка памяти диска
            }
            g.fillRect( x, y, sizeX, sizeY );
            g.setColor( Color.blue );
            g.drawRect( x, y, sizeX, sizeY );
            x += sizeX;
        }
    }
}