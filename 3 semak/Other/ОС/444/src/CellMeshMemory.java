public class CellMeshMemory {
    private int sizeCell;
    private int indexNextCellCellMeshMemory;
    private int status = 0;

    public CellMeshMemory(int size) {
        this.sizeCell = size;
    }

    public int getIndexNextCellMeshMemory() {
        return indexNextCellCellMeshMemory;
    }

    public void setIndexNextCellMeshMemory(int indexNextCellCellMeshMemory) {
        this.indexNextCellCellMeshMemory = indexNextCellCellMeshMemory;
    }

    public int getStatusCellMeshMemory() {
        return status;
    }

    public void setStatusCellMeshMemory(int status) {
        this.status = status;
    }
}