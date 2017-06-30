/**
 * Created by Adam_Yao on 2017/6/20.
 */
public class Main {

    public static void main(String[] args) {


    }
}

class A{
    static int i = 1;

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println(name);

        Thread a = new Thread("A");
        a.getId();
        a.start();
        System.out.println(Thread.activeCount());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                System.out.println(i);

                if(i==100){
                    return;
                }
            }
        };

        runnable.run();
        for (;;) {
            i++;
            System.out.println(i);
            if (i == 100) {
                return;
            }
        }
    }

}