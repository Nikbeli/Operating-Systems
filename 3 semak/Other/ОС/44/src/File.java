public class File {

    private Disk disk;
    private String filename;
    private int firstFileSegmentIndex;
    private int countOfSegment = 0;

    public File(Disk disk, String filename) {
        this.filename = filename;
        this.disk = disk;
        int index = disk.searchFreeSegmentInPhysicalMemory();
        if (index != -1) {
            disk.insertInPhysicalMemory(new FileSegment(index), index);
            firstFileSegmentIndex = index;
            countOfSegment++;
        }
    }

    public File(File fileToCopy) {
        this.disk = fileToCopy.disk;
        this.filename = fileToCopy.filename;
        int index = disk.searchFreeSegmentInPhysicalMemory();
        if (index != -1) {
            disk.insertInPhysicalMemory(new FileSegment(index), index);
            firstFileSegmentIndex = index;
            countOfSegment++;
        }
        while (this.countOfSegment != fileToCopy.countOfSegment) {
            this.enlargeFile();
        }
    }

    public void enlargeFile() {
        int index = disk.searchFreeSegmentInPhysicalMemory();
        if (index != -1) {
            disk.insertInPhysicalMemory(new FileSegment(index), index);
            disk.getPhysicalMemory()[index].setIsSelected(true);
            countOfSegment++;
        }
        FileSegment segment = disk.getSegmentFromPhysicalMemory(firstFileSegmentIndex);
        while (segment.getNextIndex() != -1) {
            segment = disk.getSegmentFromPhysicalMemory(segment.getNextIndex());
        }
        segment.setNextIndex(index);
    }

    public void deleteFile() {
        FileSegment segment = disk.getSegmentFromPhysicalMemory(firstFileSegmentIndex);
        for (int i = 1; i < countOfSegment; i++) {
            disk.deleteFromPhysicalMemory(segment.getMemoryIndex());
            segment = disk.getSegmentFromPhysicalMemory(segment.getNextIndex());
        }
        disk.deleteFromPhysicalMemory(segment.getMemoryIndex());
    }

    public int[] getAllSegment() {
        int[] segmentArray = new int[disk.getMaxMemorySize()];
        FileSegment segment = disk.getSegmentFromPhysicalMemory(firstFileSegmentIndex);
        segmentArray[0] = segment.getMemoryIndex();
        for (int i = 1; i < countOfSegment; i++) {
            segment = disk.getSegmentFromPhysicalMemory(segment.getNextIndex());
            segmentArray[i] = segment.getMemoryIndex();
        }
        segmentArray[countOfSegment] = -1;

        return segmentArray;
    }

    @Override
    public String toString() {
        return "File " + filename;
    }
}