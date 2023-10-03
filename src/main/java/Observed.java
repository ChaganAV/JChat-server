// наблюдаемые, то что будем наблюдать
public interface Observed {
    public void addObserver(Observer observer);
    public void notifyObservers(); // уведомления для наблюдателей
}
