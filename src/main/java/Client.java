import java.util.ArrayList;
import java.util.List;

public class Client implements Observed{
    List<String> messages = new ArrayList<>();
    List<Observer> subscribers = new ArrayList<>();

    public void addMessage(String msg){
        this.messages.add(msg);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer: subscribers){
            observer.handleEvent(this.messages);
        }
    }
}
