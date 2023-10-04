import java.util.ArrayList;
import java.util.List;

public class Notify implements Publisher{
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber(String text) {
        subscribers.forEach(subscriber -> subscriber.showNotification(text));
    }
    public void change(String note){
        messages.add(note);
        notifySubscriber(note);
    }
}
