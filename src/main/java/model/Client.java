package model;

public class Client implements Clientable {
    private String url = "192.168.9.104";
    private int port = 56565;
    private String name;
    public Client(String name){
        this.name = name;
    }

    @Override
    public boolean connected() {
        return true;
    }

    @Override
    public void response() {

    }

    @Override
    public boolean sendMessage(String msg) {
        return true;
    }

    @Override
    public void getLog() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
