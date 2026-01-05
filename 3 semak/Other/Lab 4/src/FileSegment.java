import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class FileSegment {

    private int memoryIndex;
    private int nextIndex = -1;
    private boolean isSelected = false;
    private String node_name;

    public FileSegment(int memoryIndex, String node_name) {
        this.memoryIndex = memoryIndex;
        this.node_name = node_name;
    }

    public boolean getIsSelect() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getMemoryIndex() {
        return memoryIndex;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public String getName() {
        return node_name;
    }
}