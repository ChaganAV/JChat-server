package view;

import sockets.Notify;
import sockets.SocketSrv;
import sockets.UserNotify;
import model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ServerView extends JFrame {
    // region fields
    private static final int WINDOW_HEIGHT = 420;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 200;
    private static final int PORT = 56565;
    private boolean start = false;
    private Server server;
    // endregion

    // region panels
    JPanel footer = new JPanel(new GridLayout(1,2));
    JPanel pnlCenter = new JPanel();
    JTextArea textMessages = new JTextArea();

    JButton btnStart = new JButton("Start");
    JButton btnExit = new JButton("Stop");
    // endregion
    public ServerView(Server server){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Chat server");
        setResizable(false); // запрет на изменение размера

        Color colorDefault = btnStart.getBackground();

        SocketSrv socketSrv = new SocketSrv(PORT);
        Notify notify = new Notify();
        UserNotify startNotify = new UserNotify();
        UserNotify exitNotify = new UserNotify();
        notify.addSubscriber(startNotify);
        notify.addSubscriber(socketSrv);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart.setBackground(Color.GREEN);
                start = true;
                server.setWork(start);
                //notify.change(String.valueOf(btnStart.getText()));
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
                server.setWork(true);
                //notify.change(btnExit.getText());
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
