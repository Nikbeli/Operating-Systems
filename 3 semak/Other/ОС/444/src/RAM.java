import java.util.LinkedList;

public class RAM {
    private int sizeMemoryDisc;
    private int sizeMemorySector;
    private CellMeshMemory[] cellsMemory;

    public RAM(int sizeDisc, int sizeSector) {
        this.sizeMemoryDisc = sizeDisc;
        this.sizeMemorySector = sizeSector;
        cellsMemory = new CellMeshMemory[sizeDisc / sizeSector];
        for (int i = 0; i < cellsMemory.length; i++) {
            cellsMemory[i] = new CellMeshMemory( sizeSector );
        }
    }

    public void searchPlaceForFilePlacement(File file) {
        int buf = -2;
        for (int i = 0, writeCells = 0; i < cellsMemory.length && writeCells != file.getSize(); i++) {
            if (cellsMemory[i].getStatusCellMeshMemory() == 0) {
                cellsMemory[i].setStatusCellMeshMemory( 1 );
                if (buf == -2) {
                    file.setIndexFirstCell( i );
                } else {
                    cellsMemory[buf].setIndexNextCellMeshMemory( i );
                }
                buf = i;
                writeCells++;
                if (writeCells == file.getSize()) {
                    cellsMemory[i].setIndexNextCellMeshMemory( -1 );
                }
            }
        }
    }

    public void chooseFile(File file) {
        notChooseFile();
        if (file.isFolder() == 1) {
            for (int i = 0; i < file.getChild().size(); i++) {
                chooseFileWithoutUnchoose( file.getChild().get( i ) );
            }
        }
        int index = file.getIndexFirstCell();
        for (int i = 0; i < file.getSize(); i++) {
            if (index == -1) {
                break;
            }
            cellsMemory[index].setStatusCellMeshMemory( 2 );
            index = cellsMemory[index].getIndexNextCellMeshMemory();
        }
    }

    public void chooseFileWithoutUnchoose(File file) {
        if (file.isFolder() == 1) {
            for (int i = 0; i < file.getChild().size(); i++) {
                chooseFileWithoutUnchoose( file.getChild().get( i ) );
            }
        }
        int index = file.getIndexFirstCell();
        for (int i = 0; i < file.getSize(); i++) {
            if (index == -1) {
                break;
            }
            cellsMemory[index].setStatusCellMeshMemory( 2 );
            index = cellsMemory[index].getIndexNextCellMeshMemory();
        }
    }

    public void notChooseFile() {
        for (int i = 0; i < cellsMemory.length; i++) {
            if (cellsMemory[i].getStatusCellMeshMemory() == 2) {
                cellsMemory[i].setStatusCellMeshMemory( 1 );
            }
        }
    }

    public void clearFile(File file) {
        if (file.isFolder() != 1) {
            int index = file.getIndexFirstCell();
            for (int i = 0; i < file.getSize(); i++) {
                if (index == -1) {
                    continue;
                }
                cellsMemory[index].setStatusCellMeshMemory( 0 );
                index = cellsMemory[index].getIndexNextCellMeshMemory();
            }
        }
        if (file.isFolder() == 1) {
            LinkedList<File> deletedChild = file.getChild();
            for (int i = 0; i < deletedChild.size(); i++) {
                clearFile( deletedChild.get( deletedChild.size() - 1 ) );
            }
            cellsMemory[file.getIndexFirstCell()].setStatusCellMeshMemory( 0 );
        }
    }

    public CellMeshMemory[] getCellsMeshMemory() {
        return cellsMemory;
    }
}