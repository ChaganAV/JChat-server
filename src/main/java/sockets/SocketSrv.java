package sockets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketSrv implements Subscriber {
    private List<String> data = new ArrayList<>();
    private int port;
    private boolean stop = false;
    public SocketSrv(int port){
        this.port = port;

    }

    @Override
    public void showNotification(String text) {
        System.out.println("Кнопка: "+ text);
        if(text.equals("start")){
            stop = false;
        }
        if(text.equals("stop")){
            stop = true;
        }
        run();
    }

    public void run(){
        try {
            ServerSocket listener = new ServerSocket(port);
            while (!stop){
                System.out.println("starting...");

                Socket client = listener.accept(); // ждем подключения
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                String someString = bin.readLine();
                setMessage(someString+"\n");
                // отправим ответ

                String response = "Принял: ";
                PrintWriter pout = new PrintWriter(out,true);
                pout.println(response+someString);
                pout.close();
                client.close();

                //btnHandler(btn);
            }

            listener.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void btnHandler(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = false;
            }
        });
    }
    public void setMessage(String msg){
        this.data.add(msg);
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public String getMessage(){
        return null;
    }
    public void setPort(int port){
        this.port = port;
    }
    public int getPort() {
        return port;
    }
}
