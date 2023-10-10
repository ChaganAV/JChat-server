package model;

import java.io.IOException;

public interface Sender {
    public boolean sendMessage(String msg) throws IOException;
    public boolean connected();
    public void disconnected();
}
