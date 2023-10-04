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
    private boolean start = false;
    ChatServer(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Chat server");
        setResizable(false); // запрет на изменение размера

        Color colorDefault = btnStart.getBackground();

        SocketSrv server = new SocketSrv(PORT);
        Notify notify = new Notify();
        UserNotify startNotify = new UserNotify();
        UserNotify exitNotify = new UserNotify();
        notify.addSubscriber(startNotify);
        notify.addSubscriber(server);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart.setBackground(Color.GREEN);
                start = true;
                notify.change(String.valueOf(btnStart.getText()));
                revalidate();
                System.out.println("Starting...");
            }
        });

        textMessages.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                revalidate();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notify.change(btnExit.getText());
                btnStart.setBackground(colorDefault);
                //System.out.println("Stop");
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


    }
}
