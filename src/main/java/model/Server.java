package model;

import data.Repository;
import data.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    Storage storage;
    boolean isWork = false;
    private static final int PORT = 56565;
    public Server(Repository repository){
        this.storage = repository.repository();
    }
    public void connection(Client client){
        List<Client> clients = storage.getClients();
        clients.add(client);
    }
    public void disconnection(Client client){
        List<Client> clients = storage.getClients();
        clients.remove(client);
    }
    public void sendMessage(String msg) throws IOException {
        List<String> messages = new ArrayList<>();
        if(msg.trim().length()>0) {
            messages.add(msg);
        }
        storage.setRepository(messages);
    }
    // region getters
    public boolean getWork() {
        return this.isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
    // endregion
}
