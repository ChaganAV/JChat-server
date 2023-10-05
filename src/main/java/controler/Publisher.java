package controler;

/**
 * подписчик
 */
public interface Publisher {
    public void addSubscriber(Subscriber subscriber);
    public void removeSubscriber(Subscriber subscriber);
    /**
     * информирование подписчиков
     * @param text
     */
    public void notifySubscriber(String text);
}
