import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketSrv {
    private List<String> data = new ArrayList<>();
    private int port;
    private boolean stop = false;
    SocketSrv(int port){
        this.port = port;

    }
    public void run(JButton btn){
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
                btnHandler(btn);
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
