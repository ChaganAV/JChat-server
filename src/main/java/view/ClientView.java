package view;

import model.Client;
import model.Sender;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    // region field
    private Client client;
    private static final int WINDOW_HEIGHT = 420;
    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    // endregion

    // region header
    JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
    JPanel pnlAddress = new JPanel(new GridLayout(1, 3));
    JPanel pnlLogin = new JPanel(new GridLayout(1, 3));
    JPanel pnlFooter = new JPanel(new GridLayout(1, 5));
    JTextField textAddress = new JTextField();
    JTextField textPort = new JTextField();
    JTextField textLogin = new JTextField();
    JButton btnBoard = new JButton("Disconnected");
    Color colorDefault = btnBoard.getBackground();
    JTextField textPassword = new JTextField();
    JButton btnLogin = new JButton("login");
    // endregion

    // region центр
    JPanel pnlCenter = new JPanel();
    JTextArea textMessages = new JTextArea();
    Document doc = textMessages.getDocument();
    // endregion

    // region footer
    Container boxHorizonal = Box.createHorizontalBox();
    JTextField textSend = new JTextField(20);
    JButton btnSend = new JButton("send");
    // endregion

    public ClientView(Client client) {
        this.client = client;

        setting();
        // region panels

        pnlAddress.add(textAddress);
        pnlAddress.add(textPort);
        btnBoard.setEnabled(false);
        pnlAddress.add(btnBoard);

        pnlLogin.add(textLogin);
        pnlLogin.add(textPassword);
        pnlLogin.add(btnLogin);

        pnlHeader.add(pnlAddress);
        pnlHeader.add(pnlLogin);

        textSend.setSize(new Dimension(300, 35));
        textSend.setFont(new Font("Times New Roman", Font.BOLD, 18));
        // endregion

        // region actions
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connected();
            }
        });

        textSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() != null) {
                    sendMessage(textSend.getText().trim());
                }
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textSend.getText().trim().length() > 0) {
                    sendMessage(textSend.getText().trim());
                }
            }
        });
        // endregion

        // region visible
        boxHorizonal.add(textSend);
        boxHorizonal.add(btnSend);
        pnlFooter.add(boxHorizonal);

        pnlCenter.add(textMessages);

        add(pnlHeader, BorderLayout.NORTH);
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlFooter, BorderLayout.SOUTH);
        revalidate();
        setVisible(true);
        // endregion
    }

    // region methods
    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        textAddress.setText(client.getUrl());
        textPort.setText(String.valueOf(client.getPort()));
        textLogin.setText(String.valueOf(client.getName()));
        setTitle("Chat client");
        setResizable(false); // запрет на изменение размера
    }

    private Component settingHeader() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        pnlAddress.add(textAddress);
        pnlAddress.add(textPort);
        pnlAddress.add(Box.createHorizontalStrut(10));

        pnlLogin.add(textLogin);
        pnlLogin.add(textPassword);
        pnlLogin.add(btnLogin);

        pnlHeader.add(pnlAddress);
        pnlHeader.add(pnlLogin);
        return panel;
    }

    private void getLog() {
        for (String msg: client.getLog()) {
            textMessages.append(msg + "\n");
        }
    }

    private void sendMessage(String msg) {
        if(client.isConnected()) {
            boolean result = client.sendMessage(msg);
            if (result) {
                textMessages.append(textSend.getText() + "\n");
                textSend.setText("");
            } else {
                textMessages.append(client.getErrorServer() + "\n");
            }
        }else {
            textMessages.append(client.getMSG_CLIENT_ERROR() + "\n");
        }
    }
    private void connected(){
        if(!client.isConnected()){
            if(client.connected()) {
                btnBoard.setBackground(Color.GREEN);
                btnBoard.setText("Connected");
                btnLogin.setText("Logout");
                getLog();
            }else{
                textMessages.append(client.getErrorServer() + "\n");
            }
        }else{
            client.setConnected(false);
            btnBoard.setBackground(colorDefault);
            btnBoard.setText("Disconnected");
            btnLogin.setText("Login");
        }

    }
    // endregion
}
