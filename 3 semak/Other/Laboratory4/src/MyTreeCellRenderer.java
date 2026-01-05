import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof DefaultMutableTreeNode){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

            if(node.isRoot()){
                ImageIcon imageIcon = new ImageIcon("Icons/discimage.jpg");
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(24, 24,  Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                setIcon(imageIcon);
                return this;
            }

            if (node.getUserObject() instanceof TreeNode){

                TreeNode file = (TreeNode)node.getUserObject();

                if (file.isFolder()){
                    ImageIcon imageIcon = new ImageIcon("Icons/folderimage.jpg");
                    Image image = imageIcon.getImage(); // transform it
                    Image newImage = image.getScaledInstance(24, 24,  Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImage);
                    setIcon(imageIcon);

                    return this;
                } else {

                    String string = file.getFileExtension();
                    ImageIcon imageIcon;
                    switch (string){
                        case "txt":
                            imageIcon = new ImageIcon("Icons/txtfile.jpg");
                            break;
                        case "wav":
                            imageIcon = new ImageIcon("Icons/wavfile.jpg");
                            break;
                        case "jpg":
                            imageIcon = new ImageIcon("Icons/jpgfile.jpg");
                            break;
                        case "png":
                            imageIcon = new ImageIcon("Icons/pngfile.jpg");
                            break;
                        case "rar":
                            imageIcon = new ImageIcon("Icons/rarfile.jpg");
                            break;
                        case "java":
                            imageIcon = new ImageIcon("Icons/javafile.jpg");
                            break;
                        case "exe":
                            imageIcon = new ImageIcon("Icons/exefile.jpg");
                            break;
                        default:
                            setIcon(UIManager.getIcon("FileView.fileIcon"));
                            return this;
                    }

                    Image image = imageIcon.getImage();
                    Image newImage = image.getScaledInstance(24, 24,  Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImage);
                    setIcon(imageIcon);
                }
            }
        }
        return this;
    }
}