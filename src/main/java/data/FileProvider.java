package data;

import data.Repository;
import data.FileData;

public class FileProvider implements Repository {
    FileData fileData;
    public FileProvider(FileData fileData) {
        this.fileData = fileData;
    }

    @Override
    public FileData repository() {
        return fileData;
    }
}
