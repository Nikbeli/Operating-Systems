import javax.swing.*;

public class Frame {

    private JFrame frame = new JFrame();
    private Disk disk = new Disk(frame);
    private FileManager fileManager = new FileManager(disk);
    private JButton buttonPasteCopy;
    private JButton buttonPasteMove;
    private JButton buttonMove;
    private JButton buttonCopy;
    private JList<Object> explorer;
    private DefaultListModel<Object> dlm = new DefaultListModel<>();

    Frame() {

        frame.setBounds(100, 100, 785, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        explorer = new JList<>(dlm);
        explorer.setBounds(10, 40, 755, 300);
        frame.getContentPane().add(explorer);
        explorer.addListSelectionListener(new MyListSelectionListener(disk, frame));

        MyPanel panel = new MyPanel(disk);
        panel.setBounds(10, 450, 765, 200);
        frame.getContentPane().add(panel);

        JButton buttonBack = new JButton("Назад");
        buttonBack.addActionListener(e -> buttonBack());
        buttonBack.setBounds(10, 10, 100, 20);
        frame.getContentPane().add(buttonBack);

        JButton buttonOpen = new JButton("Открыть");
        buttonOpen.addActionListener(e -> buttonOpen());
        buttonOpen.setBounds(120, 10, 100, 20);
        frame.getContentPane().add(buttonOpen);

        JButton buttonCreateFile = new JButton("Создать файл");
        buttonCreateFile.setBounds(10, 350, 180, 40);
        buttonCreateFile.addActionListener(e -> buttonCreateFile());
        frame.getContentPane().add(buttonCreateFile);

        JButton buttonCreateCatalog = new JButton("Создать каталог");
        buttonCreateCatalog.setBounds(200, 350, 180, 40);
        buttonCreateCatalog.addActionListener(e -> buttonCreateCatalog());
        frame.getContentPane().add(buttonCreateCatalog);

        JButton buttonEnlargeFile = new JButton("Увеличить файл");
        buttonEnlargeFile.setBounds(10, 400, 180, 40);
        buttonEnlargeFile.addActionListener(e -> buttonEnlargeFile());
        frame.getContentPane().add(buttonEnlargeFile);

        JButton buttonDelete = new JButton("Удалить");
        buttonDelete.addActionListener(e -> buttonDelete());
        buttonDelete.setBounds(200, 400, 180, 40);
        frame.getContentPane().add(buttonDelete);

        buttonCopy = new JButton("Копировать");
        buttonCopy.setBounds(390, 350, 180, 40);
        buttonCopy.addActionListener(e -> buttonCopy());
        frame.getContentPane().add(buttonCopy);

        buttonPasteCopy = new JButton("Вставить");
        buttonPasteCopy.setEnabled(false);
        buttonPasteCopy.setBounds(390, 400, 180, 40);
        buttonPasteCopy.addActionListener(e -> buttonPasteCopy());
        frame.getContentPane().add(buttonPasteCopy);

        buttonMove = new JButton("Переместить");
        buttonMove.setBounds(580, 350, 180, 40);
        buttonMove.addActionListener(e -> buttonMove());
        frame.getContentPane().add(buttonMove);

        buttonPasteMove = new JButton("Вставить");
        buttonPasteMove.setBounds(580, 400, 180, 40);
        buttonPasteMove.addActionListener(e -> buttonPasteMove());
        buttonPasteMove.setEnabled(false);
        frame.getContentPane().add(buttonPasteMove);

        frame.repaint();
    }

    private void buttonCreateFile() {
        String filename;
        filename = JOptionPane.showInputDialog(frame, "Имя файла", "Создание файла", JOptionPane.INFORMATION_MESSAGE);

        if ((filename != null) && (filename.length() > 0)) {
            fileManager.createFile(filename);
            dlm.addElement((fileManager.getCurrentCatalog().getFilesList().get(fileManager.getCurrentCatalog().getFilesList().size() - 1)));
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Вы не ввели название файла");
        }
    }

    private void buttonCreateCatalog() {
        String catalogname;
        catalogname = JOptionPane.showInputDialog(frame, "Имя каталога", "Создание каталога", JOptionPane.INFORMATION_MESSAGE);

        if ((catalogname != null) && (catalogname.length() > 0)) {
            fileManager.createCatalog(catalogname);
            dlm.addElement((fileManager.getCurrentCatalog().getFilesList().get(fileManager.getCurrentCatalog().getFilesList().size() - 1)));
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Вы не ввели название каталога");
        }
    }

    private void buttonEnlargeFile() {
        Object selectedValue = explorer.getSelectedValue();
        if (selectedValue instanceof File) {
            fileManager.enlargeFile((File) selectedValue);
        }
        frame.repaint();
    }

    private void buttonDelete() {
        Object selectedValue = explorer.getSelectedValue();
        if (selectedValue instanceof File) {
            fileManager.deleteFile(explorer.getSelectedIndex());
            dlm.removeElement(selectedValue);
        }
        if (selectedValue instanceof Catalog) {
            fileManager.deleteCatalog(explorer.getSelectedIndex());
            dlm.removeElement(selectedValue);
        }
        frame.repaint();
    }

    private void buttonBack() {
        if (fileManager.getCurrentCatalog().getParent() == null) {
            JOptionPane.showMessageDialog(frame, "Вы в корневой папке");
        } else {
            dlm.clear();
            fileManager.back();
            dlm.addElement(fileManager.getCurrentCatalog().getFilesList());
            for (int i = 0; i < disk.getMaxMemorySize(); i++) {
                if (disk.getPhysicalMemory()[i] != null) {
                    disk.getPhysicalMemory()[i].setIsSelected(false);
                }
            }
            frame.repaint();
        }
    }

    private void buttonOpen() {
        Object selectedValue = explorer.getSelectedValue();
        if (selectedValue instanceof Catalog) {
            fileManager.openCatalog(explorer.getSelectedIndex());
            dlm.clear();
            dlm.addElement(fileManager.getCurrentCatalog().getFilesList());
            disk.setAllFilesNotSelected();
            frame.repaint();
        }
    }

    private void buttonMove() {
        buttonPasteMove.setEnabled(true);
        buttonCopy.setEnabled(false);
        buttonMove.setEnabled(false);
        Object selectedValue = explorer.getSelectedValue();
        if (selectedValue != null) {
            fileManager.move(selectedValue);
            dlm.removeElement(selectedValue);
        } else {
            buttonPasteMove.setEnabled(false);
            buttonCopy.setEnabled(true);
            buttonMove.setEnabled(true);
        }
    }

    private void buttonPasteMove() {
        buttonPasteMove.setEnabled(false);
        buttonCopy.setEnabled(true);
        buttonMove.setEnabled(true);
        dlm.addElement(fileManager.pasteMove());
        disk.setAllFilesNotSelected();
        frame.repaint();
    }

    private void buttonCopy() {
        buttonPasteCopy.setEnabled(true);
        buttonCopy.setEnabled(false);
        buttonMove.setEnabled(false);
        Object selectedValue = explorer.getSelectedValue();
        if (selectedValue != null) {
            fileManager.copy(selectedValue);
        } else {
            buttonPasteCopy.setEnabled(false);
            buttonCopy.setEnabled(true);
            buttonMove.setEnabled(true);
        }
    }

    private void buttonPasteCopy() {
        buttonPasteCopy.setEnabled(false);
        buttonCopy.setEnabled(true);
        buttonMove.setEnabled(true);
        dlm.addElement(fileManager.pasteCopy());
        disk.setAllFilesNotSelected();
        frame.repaint();
    }
}