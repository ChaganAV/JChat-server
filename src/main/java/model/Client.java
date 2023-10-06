package model;

import data.Repository;

public class Client implements Sender, Repository {
    // region fields
    private String url = "192.168.9.104";
    private int port = 56565;
    private String name;
    private final String MSG_SERVER_501 = "Сервер не доступен";
    private final String MSG_CLIENT_ERROR = "Подключитесь к серверу";
    private boolean isConnected;
    // endregion
    public Client(String name){
        this.name = name;
    }

    @Override
    public boolean connected() {
        setConnected(true);
        return true;
    }

    @Override
    public boolean sendMessage(String msg) {
        return true;
    }

    @Override
    public void getLog() {
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
