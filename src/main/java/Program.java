import data.FileProvider;
import model.Client;
import model.Server;
import data.FileData;
import view.ClientView;
import view.ServerView;

public class Program {
    public static void main(String[] args) {
        //new ChatServer();
        Server server = new Server(new FileProvider(new FileData()));

        ServerView serverView = new ServerView(server);
        //Client client = new Client("Вася", server);
        Client client2 = new Client("Петя", server);
        //new ClientView(client);
        new ClientView(client2);
    }
}
