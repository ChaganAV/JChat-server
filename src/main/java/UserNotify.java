public class UserNotify implements Subscriber{
    @Override
    public void showNotification(String text) {
        System.out.println(text);
    }
}
