import javax.swing.tree.DefaultMutableTreeNode;
import java.util.LinkedList;

public class FileManager {
    private File rootFile = new File( "root", null, 1, 1 );
    private File selected = rootFile;
    private File fileForCopy;
    private File fileForMove;
    private RAM ram;
    private int nodeIndex = 1;
    private int fileNumber = 1;

    public FileManager(RAM ram) {
        this.ram = ram;
        rootFile.setSize( 1 );
        ram.searchPlaceForFilePlacement( rootFile );
    }

    public File getRootFile() {
        return rootFile;
    }

    public File getSelected() {
        return selected;
    }

    public void setSelectedFile(DefaultMutableTreeNode node) {
        this.selected = (File) node.getUserObject();
    }

    public File copy() {
        return fileForCopy = selected;
    }

    public void copyFiles(File newFile) {
        for (File file : newFile.getChild()) {
            ram.searchPlaceForFilePlacement( file );
            if (file.isFolder() == 1) {
                copyFiles( file );
            }
        }
    }

    public boolean paste() {
        if (selected.isFolder() == 1) {
            try {
                File newFile = fileForCopy.clone();
                newFile.setParent( selected );
                selected.getChild().add( newFile );
                ram.searchPlaceForFilePlacement( newFile );
                if (newFile.isFolder() == 1) {
                    copyFiles( newFile );
                }
            } catch (Exception eх) {
                eх.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean createFile(String nameFile, int folder, int size) {
        if (selected.isFolder() == 1) {
            File newFile = new File( nameFile, selected, 1, size );
            NodeFileSystem node = new NodeFileSystem( newFile, nodeIndex, fileNumber );
            fileNumber++;
            if (fileNumber > node.getNodeFileSystemSize()) {
                nodeIndex++;
                fileNumber = 1;
            }

            if (folder == 1) {
                newFile.setSize( 1 );
            } else {
                newFile.setSize( size );
            }

            ram.searchPlaceForFilePlacement( newFile );
            selected.getChild().add( newFile );
            return true;
        } else {
            return false;
        }
    }

    public boolean delete() {
        if (selected == rootFile) {
            return false;
        } else {
            selected.getParent().getChild().remove( selected );
            if (selected.isFolder() == 1) {
                delFolder( selected.getChild() );
            }
            ram.clearFile( selected );
        }
        return true;
    }

    public void delFolder(LinkedList<File> files) {
        for (File file : files) {
            if (file.isFolder() == 1) {
                delFolder( file.getChild() );
            }
            ram.clearFile( file );
        }
    }

    public void move() {
        for (int i = 0; i < selected.getParent().child.size(); i++) {
            if (selected.getParent().child.get( i ) == selected) {
                selected.getParent().child.remove( i );
            }
        }
        fileForMove = selected;
    }

    public void moveComplete() {
        selected.child.add( fileForMove );
    }
}