public class FileSegment {

    private int memoryIndex;
    private int nextIndex = -1;
    private boolean isSelected = false;

    public FileSegment(int memoryIndex) {
        this.memoryIndex = memoryIndex;
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
}