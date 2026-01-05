import java.util.LinkedList;

public class NodeFileSystem {
    private int nodeSize;
    private LinkedList<File> fileList;
    private int nodeIndex;
    private int fileIndex;

    public NodeFileSystem(File file, int nodeIndex, int fileIndex) {
        nodeSize = 5;
        this.fileIndex = fileIndex;
        fileList = new LinkedList<File>();
        if(file!=null)
            fileList.add( file );
        this.nodeIndex = nodeIndex;
    }

    public int getNodeFileSystemSize() {
        return nodeSize;
    }

    public void setNodeFileSystemSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }

    public int getNodeFileSystemIndex() {
        return nodeIndex;
    }

    public void setNodeFileSystemIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
}