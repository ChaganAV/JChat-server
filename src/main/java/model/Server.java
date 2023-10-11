package model;

import data.Repository;
import data.FileData;
import view.ServerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    FileData fileData;
    private ServerView view;
    private List<Client> clients = new ArrayList<>();
    boolean isWork = false;
    private static final int PORT = 56565;
    public Server(Repository repository){
        this.fileData = repository.repository();
    }
    public void connection(Client client){
        clients.add(client);
    }
    public void disconnection(Client client){
        clients.remove(client);
    }
    public void insert(String msg) throws IOException {
        fileData.insert(msg);
        view.addMessage(msg);
    }
    public List<String> select() throws IOException {
        return this.fileData.select();
    }
    // region getters

    public ServerView getView() {
        return view;
    }

    public void setView(ServerView view) {
        this.view = view;
    }

    public boolean getWork() {
        return this.isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
    // endregion
}
