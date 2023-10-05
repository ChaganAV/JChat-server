package view;

import model.Client;
import model.Clientable;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private Client client;
    private static final int WINDOW_HEIGHT = 420;
    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static final String MSG_SERVER_501 = "Сервер не доступен";

    // header
    JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
    JPanel pnlAddress = new JPanel(new GridLayout(1, 3));
    JPanel pnlLogin = new JPanel(new GridLayout(1, 3));
    JPanel pnlFooter = new JPanel(new GridLayout(1, 5));
    JTextField textAddress = new JTextField();
    JTextField textPort = new JTextField();
    JTextField textLogin = new JTextField();
    Box boxLogin = Box.createHorizontalBox();
    JButton btnEmpty = new JButton();
    JTextField textPassword = new JTextField();
    JButton btnLogin = new JButton("login");

    // центр
    JPanel pnlCenter = new JPanel();
    JTextArea textMessages = new JTextArea();
    Document doc = textMessages.getDocument();

    // footer
    Container boxHorizonal = Box.createHorizontalBox();
    JTextField textInput = new JTextField(20);
    JButton btnInput = new JButton("send");

    public ClientGUI(String name) {
        this.client = new Client(name);

        setting();

        pnlAddress.add(textAddress);
        pnlAddress.add(textPort);
        //pnlAddress.add(Box.createHorizontalStrut(10));
        btnEmpty.setEnabled(false);
        pnlAddress.add(btnEmpty);

        pnlLogin.add(textLogin);
        pnlLogin.add(textPassword);
        pnlLogin.add(btnLogin);

        pnlHeader.add(pnlAddress);
        pnlHeader.add(pnlLogin);

        textInput.setSize(new Dimension(300, 35));
        textInput.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() != null) {
                    if (!sendMessage(textInput.getText().trim())) {
                        textMessages.append(MSG_SERVER_501 + "\n");
                    }
                }
            }
        });
        btnInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textInput.getText().trim().length() > 0) {
                    if (!sendMessage(textInput.getText().trim())) {
                        textMessages.append(MSG_SERVER_501 + "\n");
                    }
                }
            }

//            void revalidate() {
//
//            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connected()){
                    btnEmpty.setBackground(Color.GREEN);
                    btnEmpty.setText("Connected");
                    //System.out.println("Connected");
                }
            }
        });
        boxHorizonal.add(textInput);
        boxHorizonal.add(btnInput);
        pnlFooter.add(boxHorizonal);

        pnlCenter.add(textMessages);

        add(pnlHeader, BorderLayout.NORTH);
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlFooter, BorderLayout.SOUTH);
        revalidate();
        setVisible(true);
    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        textAddress.setText(client.getURL());
        textPort.setText(String.valueOf(client.getPort()));
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

    private void responseMessage(String msg) {
        textMessages.append(msg + "\n");
    }

    private boolean sendMessage(String msg) {
        boolean result = client.sendMessage(msg);
        if(result) {
            textMessages.append(textInput.getText() + "\n");
            textInput.setText("");
        }
        return result;
    }
    private boolean connected(){
        return client.connected();
    }
}
