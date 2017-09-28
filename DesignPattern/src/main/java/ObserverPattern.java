import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by Adam_Yao on 2017/9/28.
 * 观察者模式
 *
 * @Link https://en.wikipedia.org/wiki/Observer_pattern
 */

class EventSource extends Observable implements Runnable {
    public void run() {
        while (true) {
            String response = new Scanner(System.in).next();
            setChanged();
            notifyObservers(response);
        }
    }
}


public class ObserverPattern {
    public static void main(String[] args) {
        System.out.println("Enter Text: ");
        EventSource eventSource = new EventSource();

        eventSource.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
                System.out.println("Received response: " + arg);
            }
        });

        new Thread(eventSource).start();
    }
}
/*
interface Observer{
    public void update(Observable obj, Object arg);
}*/
