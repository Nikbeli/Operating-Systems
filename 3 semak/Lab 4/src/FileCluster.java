import java.io.File;

public class FileCluster {
    // Проверка, можно ли записывать в кластер
    private boolean isEmpty;

    //Номер кластера
    private int nextClusterNumberOnDisk;

    // Тип выделения (0-серый, 1-синий, 2-красный, 3-зелёный)
    private int typeOfSelection = 0;

    //Указатель на следующий кластер
    private FileCluster nextCluster;

    public FileCluster(int needClusters, boolean isEmpty) {
        this.isEmpty = isEmpty;

        if(needClusters > 0) {
            nextCluster = new FileCluster(needClusters - 1, false);
        }
    }

    public boolean isEmpty() { return isEmpty; }

    public FileCluster getNextCluster() { return nextCluster; }

    public void setNextClusterNumberOnDisk(int nextClusterNumberOnDisk) {
        this.nextClusterNumberOnDisk = nextClusterNumberOnDisk;
    }

    public int getTypeOfSelection() {
        return typeOfSelection;
    }

    public void setTypeOfSelection(int typeOfSelection) {
        if(typeOfSelection == 0){
            setEmpty(true);
        }
        this.typeOfSelection = typeOfSelection;
        if(nextCluster != null){
            nextCluster.setTypeOfSelection(typeOfSelection);
        }
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
