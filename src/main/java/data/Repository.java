package data;

import model.Storage;

import java.util.List;

public interface Repository {
    Storage repo = new Storage();
    public void getLog();
}
