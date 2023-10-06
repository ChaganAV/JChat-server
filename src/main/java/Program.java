import model.Client;
import model.Server;
import view.ClientView;

public class Program {
    public static void main(String[] args) {
        //new ChatServer();
        Server server = new Server();
        Client client = new Client("Вася");
        Client client2 = new Client("Петя");
        new ClientView(client);
        new ClientView(client2);
    }
}
