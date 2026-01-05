import java.util.LinkedList;

public class Catalog {

    private LinkedList<Object> filesList = new LinkedList<Object>();
    private String catalogname;
    private Catalog parent;
    private Disk disk;

    public Catalog(Disk disk, String catalogname, Catalog parent) {
        this.disk = disk;
        this.catalogname = catalogname;
        this.parent = parent;
    }

    public Catalog(Catalog catalogToCopy, Catalog parent) {
        this.disk = catalogToCopy.disk;
        this.catalogname = catalogToCopy.catalogname;
        this.parent = parent;

        for (int i = 0; i < catalogToCopy.filesList.size(); i++) {
            if (catalogToCopy.filesList.get(i) instanceof Catalog) {
                Catalog catalog = (Catalog) (catalogToCopy.filesList.get(i));
                filesList.add(new Catalog(catalog,this));
            }
            if (catalogToCopy.filesList.get(i) instanceof File) {
                File file = (File) (catalogToCopy.filesList.get(i));
                filesList.add(new File(file));
            }
        }
    }

    public LinkedList<Object> getFilesList() {
        return filesList;
    }

    public Catalog getParent() {
        return parent;
    }

    public int[] getAllSegment() {
        int[] segmentArray = new int[disk.getMaxMemorySize()];
        int index = 0;
        for (int i = 0; i < filesList.size(); i++) {
            if (filesList.get(i) instanceof Catalog) {
                Catalog catalog = (Catalog) (filesList.get(i));
                int[] newSegments = catalog.getAllSegment();
                for (int j = 0; newSegments[j] != -1; j++) {
                    segmentArray[index] = newSegments[j];
                    index++;
                }
            }
            if (filesList.get(i) instanceof File) {
                File file = (File) (filesList.get(i));
                int[] newSegments = file.getAllSegment();
                for (int j = 0; newSegments[j] != -1; j++) {
                    segmentArray[index] = newSegments[j];
                    index++;
                }
            }
        }
        segmentArray[index] = -1;
        return segmentArray;
    }

    public void deleteCatalog() {
        while (filesList.size() != 0) {
            if (filesList.get(0) instanceof File) {
                File file = (File) filesList.get(0);
                file.deleteFile();
                filesList.remove(0);
            } else if (filesList.get(0) instanceof Catalog) {
                Catalog catalog = (Catalog) filesList.get(0);
                catalog.deleteCatalog();
                filesList.remove(0);
            }
        }
    }

    @Override
    public String toString() {
        return "Catalog " + catalogname;
    }
}