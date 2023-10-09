package controler;

import data.Repository;
import data.Storage;

public class Provider implements Repository {
    Storage storage;
    public Provider(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Storage repository() {
        return null;
    }
}
