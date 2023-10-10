package model;

import data.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client implements Sender {
    // region fields
    private String url = "192.168.9.104";
    private int port = 56565;
    private String name;
    private final String MSG_SERVER_501 = "Сервер не доступен";
    private final String MSG_CLIENT_ERROR = "Подключитесь к серверу";
    private Server server;
    private boolean isConnected;
    // endregion
    public Client(String name, Server server){
        this.name = name;
        this.server = server;
    }

    @Override
    public boolean connected() {
        this.server.connection(this);
        setConnected(true);
        return true;
    }

    @Override
    public void disconnected() {
        this.server.disconnection(this);
        setConnected(false);
    }

    @Override
    public boolean sendMessage(String msg) {
        LocalDate date = LocalDate.now();
        try {
            server.insert(String.format("%s (%s) %s",date.toString(), this.getName(),msg));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return true;
    }
    public List<String> getLog(){
        List<String> log = new ArrayList<>();
        try {
            log = server.select();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return log;
    }
    // region getters

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getErrorServer() {
        return MSG_SERVER_501;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMSG_SERVER_501() {
        return MSG_SERVER_501;
    }

    public String getMSG_CLIENT_ERROR() {
        return MSG_CLIENT_ERROR;
    }
    // endregion
}
