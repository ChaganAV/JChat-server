import model.Server;
import view.ClientGUI;

public class Program {
    public static void main(String[] args) {
        //new ChatServer();
        Server server = new Server();
        new ClientGUI("Вася");
    }
}
