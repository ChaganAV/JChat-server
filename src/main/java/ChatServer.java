import javax.swing.*;
import java.awt.*;

public class ChatServer extends JFrame {
    private static final int WINDOW_HEIGHT = 420;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 200;

    JPanel footer = new JPanel(new GridLayout(1,2));
    JButton btnStart = new JButton("Start");
    JButton btnExit = new JButton("Stop");
    ChatServer(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Chat server");
        setResizable(false); // запрет на изменение размера

        footer.add(btnStart);
        footer.add(btnExit);
        add(footer,BorderLayout.SOUTH);
        setVisible(true);
    }

}
