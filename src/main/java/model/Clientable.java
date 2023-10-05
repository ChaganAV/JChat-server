package model;

public interface Clientable {
    public boolean sendMessage(String msg);
    public void getLog();
    public void response();
    public boolean connected();
}
