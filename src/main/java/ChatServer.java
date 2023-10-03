import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends JFrame {
    private static final int WINDOW_HEIGHT = 420;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 200;
    private static final int PORT = 56565;

    JPanel footer = new JPanel(new GridLayout(1,2));
    JPanel pnlCenter = new JPanel();
    JTextArea textMessages = new JTextArea();

    JButton btnStart = new JButton("Start");
    JButton btnExit = new JButton("Stop");
    ChatServer(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Chat server");
        setResizable(false); // запрет на изменение размера


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
                System.out.println("Starting...");
            }
        });

        textMessages.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                revalidate();
            }
        });

        pnlCenter.add(textMessages);
        footer.add(btnStart);
        footer.add(btnExit);
        add(pnlCenter,BorderLayout.CENTER);
        add(footer,BorderLayout.SOUTH);
        setVisible(true);
    }

    private void startServer(){
        try {
            ServerSocket listener = new ServerSocket(PORT);
            final boolean[] stop = {false};
            while (!stop[0]){
                System.out.println("starting...");


                Socket client = listener.accept(); // ждем подключения
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                String someString = bin.readLine();
                textMessages.setText(someString+"\n");
                // отправим ответ

                String response = "Вот ответ: ";
                PrintWriter pout = new PrintWriter(out,true);
                pout.println(response+someString);
                pout.close();
                client.close();

                btnExit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        stop[0] = true;
                        System.out.println("Stop");
                    }
                });
            }

            listener.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
