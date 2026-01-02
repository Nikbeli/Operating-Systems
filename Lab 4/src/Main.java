import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class Main {

    //Диск
    private Disk disk;

    //Дерево
    private JTree tree;

    //Действия с деревом
    private Methods methods;

    //Компоненты формы
    private JFrame frame;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JButton buttonCopy;
    private JButton buttonCopyTo;
    private JButton buttonReplace;
    private JButton buttonReplaceInto;

    //переменные JComboBox
    private String[] items = {"jpg", "png", "wav", "txt","exe","rar","java"};

    //Дерево
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(new TreeNode("Локальный диск (D:)", true));

    //Выбранный узел в JTree
    private DefaultMutableTreeNode selectedNode;

    //Добавляемый узел в JTree
    private DefaultMutableTreeNode newNode;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main fileSystem = new Main();
                fileSystem.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("Файловая система");


        int diskPartitionSize;
        while(true){
            diskPartitionSize = Integer.parseInt(JOptionPane.showInputDialog(frame, "Укажите размеры дискового раздела в КБ (от 1024 до 8192)",2048));
            if(diskPartitionSize <= 8192 && diskPartitionSize >= 1024){
                break;
            }
            JOptionPane.showMessageDialog(disk, "Размер дискового раздела не удовлетворяет условию","Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        int diskSectorSize;
        while(true){
            diskSectorSize = Integer.parseInt(JOptionPane.showInputDialog(frame, "Укажите размеры сектора диска в КБ (от 2 до 8)",4));
            if(diskSectorSize <= 8 && diskSectorSize >= 2){
                break;
            }
            JOptionPane.showMessageDialog(disk, "Размер сектора диска не удовлетворяет условию","Ошибка", JOptionPane.ERROR_MESSAGE);
        }


        frame.setBounds(100, 100, 1215, 622);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        disk = new Disk(diskPartitionSize, diskSectorSize);
        disk.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        disk.setPreferredSize(new Dimension(800, 800));
        frame.getContentPane().add(disk);

        tree = new JTree(root);

        methods = new Methods(disk, tree);
        methods.initializeJTreeNodes(root);

        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null) return;
            Object nodeInfo = node.getUserObject ();
            methods.selectNode(node, 2, false);
        });
        //tree.setCellRenderer(new MyTreeCellRenderer());

        textField = new JTextField("Введите имя файла");
        textField.setBounds(10, 316, 268, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        comboBox = new JComboBox<>(items);
        comboBox.setBounds(288, 316, 94, 20);
        comboBox.setFocusable(false);
        comboBox.setEditable(false);
        frame.getContentPane().add(comboBox);

        JButton buttonCreate = new JButton("Создать файл");
        buttonCreate.addActionListener(arg0 -> {
                    selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    TreeNode treeNode = methods.addToJTree(selectedNode, new TreeNode(textField.getText(), (String) comboBox.getSelectedItem(), false));
                    if(treeNode != null){
                        disk.addToDisk(treeNode);
                    }
                    disk.repaint();
                }
        );
        buttonCreate.setBounds(10, 367, 184, 43);
        frame.getContentPane().add(buttonCreate);

        //Кнопка для создания папки
        JButton buttonCreateFolder = new JButton("Создать папку");
        buttonCreateFolder.addActionListener(e -> {
                    selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    methods.addToJTree(selectedNode, new TreeNode(textField.getText(), true));
                    disk.repaint();
                }
        );
        buttonCreateFolder.setBounds(198, 367, 184, 43);
        frame.getContentPane().add(buttonCreateFolder);

        buttonCopy = new JButton("Копировать");
        buttonCopy.addActionListener(e -> {
            selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            int weightOfSelectedFolder = methods.calculateFolderWeight(selectedNode);
            if (weightOfSelectedFolder <= disk.getCountOfEmptySectors()){
                buttonCopy.setEnabled(false);
                buttonCopyTo.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(disk, "Недостаточно места","Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            disk.repaint();
        });
        buttonCopy.setBounds(10, 421, 184, 43);
        buttonCopy.setEnabled(true);
        frame.getContentPane().add(buttonCopy);

        buttonCopyTo = new JButton("Копировать в эту папку");
        buttonCopyTo.addActionListener(e -> {
            buttonCopy.setEnabled(true);
            buttonCopyTo.setEnabled(false);
            newNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if(selectedNode == newNode){
                JOptionPane.showMessageDialog(disk, "Нельзя копировать в одно и то же место","Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                methods.copyToJTree(selectedNode, newNode, true);
            }
            disk.repaint();
        });
        buttonCopyTo.setBounds(198, 421, 184, 43);
        buttonCopyTo.setEnabled(false);
        frame.getContentPane().add(buttonCopyTo);

        buttonReplace = new JButton("Переместить");
        buttonReplace.addActionListener(e -> {
            buttonReplace.setEnabled(false);
            buttonReplaceInto.setEnabled(true);
            selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            disk.repaint();
        });
        buttonReplace.setBounds(10, 475, 184, 43);
        buttonReplace.setEnabled(true);
        frame.getContentPane().add(buttonReplace);

        buttonReplaceInto = new JButton("Переместить в эту папку");
        buttonReplaceInto.addActionListener(e -> {
            buttonReplace.setEnabled(true);
            buttonReplaceInto.setEnabled(false);
            newNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if(selectedNode == newNode){
                JOptionPane.showMessageDialog(disk, "Нельзя перемещать в одно и то же место","Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                methods.replaceIntoJTree(selectedNode, newNode);
            }
            disk.repaint();
        });
        buttonReplaceInto.setBounds(198, 475, 184, 43);
        buttonReplaceInto.setEnabled(false);
        frame.getContentPane().add(buttonReplaceInto);

        JButton buttonRemove = new JButton("Удалить");
        buttonRemove.addActionListener(e -> {
                    methods.removeFromJTree(true);
                    disk.repaint();
                }
        );
        buttonRemove.setBounds(10, 529, 372, 43);
        frame.getContentPane().add(buttonRemove);

        JScrollPane scrollPane = new JScrollPane(disk);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(395, 11, 800, 561);
        frame.getContentPane().add(scrollPane);

        JScrollPane treeView = new JScrollPane(tree);
        treeView.setBounds(10, 11, 372, 294);
        frame.getContentPane().add(treeView);
    }
}