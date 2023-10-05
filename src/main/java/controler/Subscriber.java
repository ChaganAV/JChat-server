package controler;

/**
 * наблюдатель
 */

public interface Subscriber {
    /**
     * метод, который будет выполняться после получения
     * сообщения от того за кем наблюдаем
     */
    public void showNotification(String text);
}
