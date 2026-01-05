import java.util.LinkedList;

public class FileManager {

    private Disk disk;
    private Catalog rootCatalog = new Catalog(disk, "C", null);
    private Catalog currentCatalog = rootCatalog;
    private Object fileToCopy;

    public FileManager(Disk disk) {
        this.disk = disk;
    }

    public Catalog getCurrentCatalog() {
        return currentCatalog;
    }

    public void enlargeFile(File file) {
        file.enlargeFile();
    }

    public void copy(Object abstractFile) {
        fileToCopy = abstractFile;
    }

    public Object pasteCopy() {
        Object copy = null;
        if (fileToCopy instanceof File) {
            copy = new File((File) fileToCopy);
            currentCatalog.getFilesList().add(copy);
        } else if (fileToCopy instanceof Catalog) {
            copy = new Catalog((Catalog) fileToCopy, currentCatalog);
            currentCatalog.getFilesList().add(copy);
        }
        fileToCopy = null;
        return copy;
    }

    public void move(Object abstractFile) {
        fileToCopy = abstractFile;
        currentCatalog.getFilesList().remove(abstractFile);
    }

    public Object pasteMove() {
        currentCatalog.getFilesList().add(fileToCopy);
        Object returnedFile = fileToCopy;
        fileToCopy = null;
        return returnedFile;
    }

    public void deleteFile(int index) {
        LinkedList<Object> filesList = currentCatalog.getFilesList();
        ((File) filesList.get(index)).deleteFile();
        filesList.remove(index);
    }

    public void deleteCatalog(int index) {
        LinkedList<Object> filesList = currentCatalog.getFilesList();
        ((Catalog) filesList.get(index)).deleteCatalog();
        filesList.remove(index);
    }

    public void createFile(String filename) {
        LinkedList<Object> filesList = currentCatalog.getFilesList();
        filesList.add(new File(disk, filename));
    }

    public void createCatalog(String catalogname) {
        LinkedList<Object> filesList = currentCatalog.getFilesList();
        filesList.add(new Catalog(disk, catalogname, currentCatalog));
    }

    public void openCatalog(int index) {
        LinkedList<Object> filesList = currentCatalog.getFilesList();
        if (filesList.get(index) instanceof Catalog) {
            currentCatalog = (Catalog) (filesList.get(index));
        }
    }

    public void back() {
        currentCatalog = currentCatalog.getParent();
    }
}