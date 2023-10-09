import controler.Provider;
import model.Client;
import model.Server;
import data.Storage;
import view.ClientView;
import view.ServerView;

public class Program {
    public static void main(String[] args) {
        //new ChatServer();
        Server server = new Server(new Provider(new Storage()));

        ServerView serverView = new ServerView(server);
        Client client = new Client("Вася");
        Client client2 = new Client("Петя");
        new ClientView(client);
        new ClientView(client2);
    }
}
