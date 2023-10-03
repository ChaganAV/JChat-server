import java.util.List;

// наблюдатель
public interface Observer {
    public void handleEvent(List<String> msg);
}
