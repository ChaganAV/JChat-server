package data;

import data.Repository;
import model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage{
    private File fileStorage = new File("messages.txt");
    private List<Client> clients = new ArrayList<>();
    private List<String> repository;

    private File getFile() {
        return fileStorage;
    }
    // region getters
    public List<String> getRepository() throws IOException {
        if(fileStorage.exists()) {
            Reader reader = new InputStreamReader(new FileInputStream(fileStorage));
            BufferedReader in = new BufferedReader(reader);
            String line;
            while ((line = in.readLine()) != null){
                repository.add(line);
            }

        }
        return repository;
    }

    public void setRepository(List<String> repository) throws IOException {
        List<String> messages = repository;
        if(fileStorage.exists()){
            FileWriter fw = new FileWriter(fileStorage,true);
            for (String msg: messages) {
                fw.write(msg+'\n');
            }
            fw.close();
        }

    }

    private void setFile(File file) {
        this.fileStorage = file;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    // endregion
}
