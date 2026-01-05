import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class MyListSelectionListener implements ListSelectionListener {

    private JFrame frame;
    private Disk disk;
    private int[] select;

    MyListSelectionListener(Disk disk, JFrame frame) {
        this.frame = frame;
        this.disk = disk;
        this.select = new int[disk.getMaxMemorySize()];
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (((JList<Object>) e.getSource()).getSelectedValue() instanceof Catalog) {
            Catalog selectedCatalog = (Catalog) ((JList<Object>) e.getSource()).getSelectedValue();
            select = selectedCatalog.getAllSegment();
            disk.setAllFilesNotSelected();
            for (int i = 0; select[i] != -1; i++) {
                disk.getPhysicalMemory()[select[i]].setIsSelected(true);
            }
            frame.repaint();
            return;
        }

        if (((JList<Object>) e.getSource()).getSelectedValue() instanceof File) {
            File selectFile = (File) ((JList<Object>) e.getSource()).getSelectedValue();
            select = selectFile.getAllSegment();
            disk.setAllFilesNotSelected();
            for (int i = 0; select[i] != -1; i++) {
                disk.getPhysicalMemory()[select[i]].setIsSelected(true);
            }
            frame.repaint();
        }
    }
}