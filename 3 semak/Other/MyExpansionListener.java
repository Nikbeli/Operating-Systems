import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreePath;

public class MyExpansionListener implements TreeExpansionListener {

    private JTree left;
    private MyPanel right;

    public MyExpansionListener(JTree left) {
        this.left = left;
    }

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        TreePath path = event.getPath();
        if (event.getSource() == left) {
            //right.expandPath(path);
        } else {
            left.expandPath(path);
        }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        TreePath path = event.getPath();
        if (event.getSource() == left) {
            //right.collapsePath(path);
        } else {
            left.collapsePath(path);
        }
    }
}