public class TreeNode {
    // Имя файла или папки
    private String name;

    //Расширение папки
    private String fileExtension;

    //Папка это или файл
    private boolean isFolder;

    // Размер папки или файла
    private int fileSize;

    // Первый кластер файла
    private FileCluster firstFileCluster;

    public TreeNode(String name, String fileExtension, boolean isFolder) {
        this.name = name;
        this.fileExtension = fileExtension;
        this.isFolder = isFolder;

        setSize(fileExtension);
        firstFileCluster = new FileCluster(fileSize, false);
    }

    private void setSize(String fileExtension) {
        switch (fileExtension) {

            case "txt":
                fileSize = 1;
                break;
            case "wav":
                fileSize = 20;
                break;
            case "jpg":
                fileSize = 10;
                break;
            case "png":
                fileSize = 15;
                break;
            case "rar":
                fileSize = 3;
                break;
            case "exe":
                fileSize = 5;
                break;
            case "java":
                fileSize = 2;
                break;
            default:
                fileSize = 0;
                break;
        }
    }

    public TreeNode(String name, boolean isFolder) {
        this.name = name;
        this.isFolder = isFolder;
    }

    public String toString() {
        if(isFolder) {
            return name;
        } else {
            return name + "." + fileExtension;
        }
    }

    public String getName() { return name; }
    public String getFileExtension() { return fileExtension; }
    public boolean isFolder() { return isFolder; }
    public int getFileSize() { return fileSize; }
    public FileCluster getFirstFileCluster() { return firstFileCluster; }
    public void setTypeOfSelection(int typeOfSelection) { firstFileCluster.setTypeOfSelection(typeOfSelection); }
}
