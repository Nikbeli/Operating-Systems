import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class Methods {

    //Диск
    private Disc disc;

    //Дерево
    private JTree tree;

    public Methods(Disc disc, JTree tree) {
        this.disc = disc;
        this.tree = tree;
    }

    public void initializeJTreeNodes(DefaultMutableTreeNode root) {
        DefaultMutableTreeNode tempFolder;
        DefaultMutableTreeNode tempFile;
        TreeNode treeNode;

        tempFolder = new DefaultMutableTreeNode(new TreeNode("Фотографии", true));
        root.add(tempFolder);

        treeNode = new TreeNode("IMG_0001", "jpg", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);

        disc.addToDisc(treeNode);

        treeNode = new TreeNode("IMG_0002", "jpg", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        treeNode = new TreeNode("IMG_0003", "jpg", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        selectNode(tempFolder, 1, true);

        tempFolder = new DefaultMutableTreeNode(new TreeNode("Картинки", true));
        root.add(tempFolder);

        treeNode = new TreeNode("pepe_frog", "png", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        treeNode = new TreeNode("crazy_frog", "png", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        selectNode(tempFolder, 1, true);

        tempFolder = new DefaultMutableTreeNode(new TreeNode("Учеба", true));
        root.add(tempFolder);

        treeNode = new TreeNode("Деление столбиком", "java", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        treeNode = new TreeNode("Lab_Rab_5", "rar", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        treeNode = new TreeNode("Отчет_Практическая_работа_№4", "txt", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        selectNode(tempFolder, 1, true);

        tempFolder = new DefaultMutableTreeNode(new TreeNode("Музыка", true));
        root.add(tempFolder);

        treeNode = new TreeNode("girl in red - 4am", "wav", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        treeNode = new TreeNode("Oasis - Stop Crying Your Heart Out", "wav", false);
        tempFile = new DefaultMutableTreeNode(treeNode);
        tempFolder.add(tempFile);
        disc.addToDisc(treeNode);

        selectNode(tempFolder, 1, true);
    }

    public TreeNode addToJTree( DefaultMutableTreeNode selectedNode, TreeNode file){

        if(selectedNode.isRoot() || ((TreeNode) selectedNode.getUserObject()).isFolder() ) {
            StringBuilder tempNameOfFile = new StringBuilder(file.getName());
            boolean isAlready = false;
            while (true) {
                for (int i = 0; i < selectedNode.getChildCount(); i++) {
                    if (!(((TreeNode) ((DefaultMutableTreeNode) selectedNode.getChildAt(i)).getUserObject()).isFolder()) && ((TreeNode) ((DefaultMutableTreeNode) selectedNode.getChildAt(i)).getUserObject()).getName().contentEquals(tempNameOfFile) && ((TreeNode) ((DefaultMutableTreeNode) selectedNode.getChildAt(i)).getUserObject()).getFileExtension().equals(file.getFileExtension())) {
                        isAlready = true;
                    }
                    if (((TreeNode) ((DefaultMutableTreeNode) selectedNode.getChildAt(i)).getUserObject()).isFolder() && ((TreeNode) ((DefaultMutableTreeNode) selectedNode.getChildAt(i)).getUserObject()).getName().contentEquals(tempNameOfFile)) {
                        isAlready = true;
                    }
                }
                if (!isAlready) {
                    break;
                } else {
                    tempNameOfFile.insert(0, "Новый - ");
                    isAlready = false;
                }
            }

            TreeNode newTreeNode;
            if(!file.isFolder()){
                newTreeNode = new TreeNode(tempNameOfFile.toString(), file.getFileExtension(), file.isFolder());
            } else {
                newTreeNode = new TreeNode(tempNameOfFile.toString(),  file.isFolder());
            }

            DefaultMutableTreeNode newJTreeNode = new DefaultMutableTreeNode(newTreeNode);
            selectedNode.add(newJTreeNode);
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.reload();

            tree.scrollPathToVisible(new TreePath(newJTreeNode.getPath()));
            tree.setSelectionPath(new TreePath(newJTreeNode.getPath()));

            return newTreeNode;
        } else {
            JOptionPane.showMessageDialog(disc, "Файл можно добавить только в папку","Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean RecursiveMethod(DefaultMutableTreeNode selectedNode, DefaultMutableTreeNode newNodeToCopy){
        for (int i = 0; i < selectedNode.getChildCount()-1; i++) {
            if (((DefaultMutableTreeNode) newNodeToCopy.getChildAt(i)).getUserObject() == selectedNode.getUserObject()){
                return true;
            }
        }
        return false;
    }
    public void copyToJTree (DefaultMutableTreeNode selectedNode, DefaultMutableTreeNode newNodeToCopy, boolean createNewFile) {

        TreeNode file = (TreeNode) selectedNode.getUserObject();
        TreeNode file2 = (TreeNode) newNodeToCopy.getUserObject();
        StringBuilder tempNameOfFile = new StringBuilder(file.getName());
        StringBuilder NameOfFile = new StringBuilder(file2.getName());
        if(RecursiveMethod(selectedNode,newNodeToCopy)){
            return;
        }
        boolean isAlready = false;
        while (true) {
            for (int i = 0; i < newNodeToCopy.getChildCount(); i++) {
                if (!(((TreeNode) ((DefaultMutableTreeNode) newNodeToCopy.getChildAt(i)).getUserObject()).isFolder()) && ((TreeNode) ((DefaultMutableTreeNode) newNodeToCopy.getChildAt(i)).getUserObject()).getName().contentEquals(tempNameOfFile) && ((TreeNode) ((DefaultMutableTreeNode) newNodeToCopy.getChildAt(i)).getUserObject()).getFileExtension().equals(file.getFileExtension())) {
                    isAlready = true;
                }
                if (((TreeNode) ((DefaultMutableTreeNode) newNodeToCopy.getChildAt(i)).getUserObject()).isFolder() && ((TreeNode) ((DefaultMutableTreeNode) newNodeToCopy.getChildAt(i)).getUserObject()).getName().contentEquals(tempNameOfFile)) {
                    isAlready = true;
                }
            }

            if (!isAlready) {
                break;
            } else {
                tempNameOfFile.insert(0, "Новый - ");
                isAlready = false;
            }
        }

        TreeNode newTreeNode;

        if(createNewFile){
            if(!file.isFolder()){
                newTreeNode = new TreeNode(selectedNode.getParent() == newNodeToCopy ? tempNameOfFile.toString()  + " - копия" : tempNameOfFile.toString(), file.getFileExtension(), file.isFolder());
            } else {
                newTreeNode = new TreeNode(selectedNode.getParent() == newNodeToCopy ? tempNameOfFile.toString()  + " - копия" : tempNameOfFile.toString(), file.isFolder());
            }
        } else {
            newTreeNode = (TreeNode) selectedNode.getUserObject();
        }

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newTreeNode);
        newNodeToCopy.add(newNode);

        if(!file.isFolder() && createNewFile){
            disc.addToDisc(newTreeNode);
        }

        for (int i = 0; i < selectedNode.getChildCount(); i++) {
            copyToJTree((DefaultMutableTreeNode) selectedNode.getChildAt(i), newNode, createNewFile);
        }

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();
        tree.scrollPathToVisible(new TreePath(newNode.getPath()));

        selectNode(newNode, 3, true);
    }

    public void replaceIntoJTree (DefaultMutableTreeNode selectedNode, DefaultMutableTreeNode newNode){
        copyToJTree(selectedNode, newNode, false);
        tree.setSelectionPath(new TreePath(selectedNode.getPath()));
        removeFromJTree(false);
    }

    public void removeFromJTree(boolean removeFromDisc){
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(selectedNode != tree.getModel().getRoot()) {

            if (removeFromDisc) {
                selectNode(selectedNode, 0, true);
            } else {
                selectNode(selectedNode, 3, true);
            }

            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.removeNodeFromParent(selectedNode);

            model.reload();
        }
    }

    public int calculateFolderWeight(DefaultMutableTreeNode folder){
        int result = 0;

        for (int i = 0; i < folder.getChildCount(); i++) {
            DefaultMutableTreeNode tempDefaultMutableTreeNode = (DefaultMutableTreeNode) folder.getChildAt(i);
            if (!((TreeNode) tempDefaultMutableTreeNode.getUserObject()).isFolder()){
                result += ((TreeNode) tempDefaultMutableTreeNode.getUserObject()).getFileSize();
            } else {
                result += calculateFolderWeight((DefaultMutableTreeNode) folder.getChildAt(i));
            }
        }

        return result;
    }

    public void selectNode(DefaultMutableTreeNode selectedNode, int typeOfSelection, boolean re) {

        if(!re){
            disc.removeSelection();
        }

        if(!selectedNode.isRoot()) {

            if (!((TreeNode) selectedNode.getUserObject()).isFolder()) {
                ((TreeNode) selectedNode.getUserObject()).setTypeOfSelection(typeOfSelection);
            } else {
                for (int i = 0; i < selectedNode.getChildCount(); i++) {
                    DefaultMutableTreeNode tempDefaultMutableTreeNode = (DefaultMutableTreeNode) selectedNode.getChildAt(i);
                    selectNode(tempDefaultMutableTreeNode, typeOfSelection, true);
                }
            }
        }
        disc.repaint();
    }
}
