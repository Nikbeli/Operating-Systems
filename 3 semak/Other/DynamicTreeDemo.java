import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;
import javax.swing.JComponent;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.SpinnerNumberModel;
import java.util.Enumeration;

class DynamicTreeDemo extends JPanel implements ActionListener {

    private int memory;
    private int newNodeSuffix = 1;
    private int red_index = 0;
    //private int
    private static String ADD_COMMAND = "add";
    private static String REMOVE_COMMAND = "remove";
    private static String CLEAR_COMMAND = "clear";
    private static String ENLARGE_COMMAND = "enlarge";
    JTextField name = new JTextField();
    JSpinner spinner = new JSpinner();

    private DefaultMutableTreeNode rootNode;
    private final DefaultTreeModel treeModel;

    private JTree left;
    private JPanel right;
    private Disk disk;

    public DynamicTreeDemo(Disk disk) {
        super(new BorderLayout());

        this.disk = disk;
        rootNode = new DefaultMutableTreeNode("Root Node");
        treeModel = new DefaultTreeModel(rootNode);

        disk.insertInPhysicalMemory(new FileSegment(0, rootNode.toString()), 0, 1);
        repaint();
        memory = 1;

        left = new JTree(treeModel);
        left.setCellRenderer(new LeftTreeCellRenderer());
        right = new MyPanel(disk);
        //right.setCellRenderer(new RightTreeCellRenderer());

        MyExpansionListener expansionListener = new MyExpansionListener(left);

        left.addTreeExpansionListener(expansionListener);
        //right.addTreeExpansionListener(expansionListener);

        //right.setSelectionModel(left.getSelectionModel());

        JButton addButton = new JButton("Add");
        addButton.setActionCommand(ADD_COMMAND);
        addButton.addActionListener(this);

        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);

        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand(CLEAR_COMMAND);
        clearButton.addActionListener(this);

        JButton plusButton = new JButton("Enlarge");
        plusButton.setActionCommand(ENLARGE_COMMAND);
        plusButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JScrollPane(left));
        panel.add(new JScrollPane(right));
        add(panel, BorderLayout.CENTER);

        spinner.setValue(1);

        JPanel menu = new JPanel(new GridLayout(0, 3));
        menu.add(addButton);
        menu.add(removeButton);
        menu.add(clearButton);
        menu.add(name);
        //menu.add(plusButton);
        menu.add(spinner);
        add(menu, BorderLayout.SOUTH);

        left.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                if (rootNode.getChildCount() < 1) {return;}
                if (disk.CheckIndex(node.toString()) < 0) {return;}

                if (rootNode == node) {
                    clean();
                    for (int i = 0; i < disk.getMaxMemorySize(); i++) {
                        if (disk.getPhysicalMemory()[i] != null) {
                            disk.getPhysicalMemory()[i].setIsSelected(true);
                        }
                    }
                }

                disk.getPhysicalMemory()[red_index].setIsSelected(false);
                red_index = disk.CheckIndex(node.toString());
                if (disk.getPhysicalMemory()[red_index].getNextIndex() == -1) {
                    clean();
                    disk.getPhysicalMemory()[red_index].setIsSelected(true);
                }
                else {
                    clean();
                    disk.getPhysicalMemory()[red_index].setIsSelected(true);
                    int next_index = disk.getPhysicalMemory()[red_index].getNextIndex();
                    for (int i = 0; i < left.getRowCount(); i++) {
                        if (disk.getPhysicalMemory()[next_index].getNextIndex() == -1) {
                            return;
                        }
                        disk.getPhysicalMemory()[next_index].setIsSelected(true);
                        next_index = disk.getPhysicalMemory()[next_index].getNextIndex();
                    }
                }
                repaint();
            }
        });
    }

    protected TreeData createNodeData() {
        return new TreeData(name.getText(), 1);
    }

    public void clean() {
        for (int i = 0; i < disk.getMaxMemorySize(); i++) {
            if (disk.getPhysicalMemory()[i] != null) {
                disk.getPhysicalMemory()[i].setIsSelected(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (ADD_COMMAND.equals(command)) {
            // Add button clicked
            if (name.getText().length() == 0)
            {
                JOptionPane.showMessageDialog(DynamicTreeDemo.this,
                        "<html><h2>Ошибка</h2><i>введите название файла</i>");
                return;
            }
            if ((int)spinner.getValue() < 1) {return;}
            if (memory + (int)spinner.getValue() >= disk.getMaxMemorySize()) {
                JOptionPane.showMessageDialog(DynamicTreeDemo.this,
                        "<html><h2>Ошибка</h2><i>недостаточно свободного места на диске</i>");
                return;
            }

            addObject(createNodeData());
        } else if (REMOVE_COMMAND.equals(command)) {
            // Remove button clicked
            removeCurrentNode();
        } else if (CLEAR_COMMAND.equals(command)) {
            // Clear button clicked.
            clear();
        } else if (ENLARGE_COMMAND.equals(command)) {
            enlarge();
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    protected static void createAndShowGUI(Disk disk) {
        // Create and set up the window.
        JFrame frame = new JFrame("Антон Михайлович, примите, пожалуйста, эту лабу");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        DynamicTreeDemo newContentPane = new DynamicTreeDemo(disk);
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.setPreferredSize(new Dimension(620, 506));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Remove all nodes except the root node.
     */
    public void clear() {
        for (int i = 1; i < disk.getMaxMemorySize(); i++) {repaint();
            disk.deleteFromPhysicalMemory(i);
        }
        red_index = 0;
        memory = 1;

        rootNode.removeAllChildren();
        treeModel.reload();
    }

    /**
     * Add child to the currently selected node.
     */
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = left.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }

        return addObject(parentNode, child, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child) {
        return addObject(parent, child, false);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = rootNode;
        }

        // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            left.scrollPathToVisible(new TreePath(childNode.getPath()));
            //right.scrollPathToVisible(new TreePath(childNode.getPath()));
        }

        Random rnd = new Random();
        disk.insertInPhysicalMemory(new FileSegment(rnd.nextInt(disk.getMaxMemorySize() - 1), childNode.toString()), rnd.nextInt(disk.getMaxMemorySize() - 1), (int)spinner.getValue());
        memory += (int)spinner.getValue();
        repaint();
        return childNode;
    }

    //
    public void enlarge() {
        //disk.insertInPhysicalMemory(new FileSegment(ind, parentPath), ind);
        repaint();
    }

    /**
     * Remove the currently selected node.
     */
    public void removeCurrentNode() {
        TreePath currentSelection = left.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
                    .getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                red_index = disk.CheckIndex(currentNode.toString());
                String name = currentNode.toString();

                for (int i = 0; i < disk.getMaxMemorySize(); i++) {
                    if (disk.getPhysicalMemory()[i] != null && disk.getPhysicalMemory()[i].getName().equals(name))
                    {
                        memory--;
                        disk.getPhysicalMemory()[i].setIsSelected(false);
                        repaint();
                        disk.deleteFromPhysicalMemory(i);
                    }
                }
                red_index = 0;
            }
        }
    }
}