import java.util.LinkedList;

public class File {
    private String fileName;
    private File parent;
    private int ifItISFolder;
    public LinkedList<File> child;
    private int indexFirstCellMeshMemory;
    private int size = -1;

    public File(String name, File parent, int ifItISFolder, int size) {
        this.fileName = name;
        this.parent = parent;
        this.ifItISFolder = ifItISFolder;
        this.size = size;
        if (ifItISFolder == 1) {
            child = new LinkedList<File>();
        }
    }

    public File() {
    }

    public File clone() {
        File newFile = new File();
        newFile.setSize( size );
        newFile.setName( fileName );
        newFile.setFolder( ifItISFolder );
        if (ifItISFolder == 1) {
            LinkedList<File> child = new LinkedList<>();
            for (File file : this.child) {
                child.add( file.clone() );
            }
            newFile.setChild( child );
        }
        return newFile;
    }

    public String toString() {
        return fileName;
    }

    public void setName(String name) {
        this.fileName = name;
    }

    public File getParent() {
        return parent;
    }

    public void setParent(File parent) {
        this.parent = parent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int isFolder() {
        return ifItISFolder;
    }

    public void setFolder(int ifItISFolder) {
        this.ifItISFolder = ifItISFolder;
    }

    public LinkedList<File> getChild() {
        return child;
    }

    public void setChild(LinkedList<File> child) {
        this.child = child;
    }

    public int getIndexFirstCell() {
        return indexFirstCellMeshMemory;
    }

    public void setIndexFirstCell(int indexFirstCellMeshMemory) {
        this.indexFirstCellMeshMemory = indexFirstCellMeshMemory;
    }
}