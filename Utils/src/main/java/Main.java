import java.util.UUID;

/**
 * Created by Adam_Yao on 2017/6/20.
 */
public class Main {
    public static void main(String[] args) {
        String s = generateShortUuid();
        System.out.println(s);
    }
        public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };


        public static String generateShortUuid() {
            StringBuffer shortBuffer = new StringBuffer();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            for (int i = 0; i < 8; i++) {
                String str = uuid.substring(i * 4, i * 4 + 4);
                int x = Integer.parseInt(str, 16);
                shortBuffer.append(chars[x % 0x3E]);
            }
            return shortBuffer.toString();

        }
}

class A {
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

                if (i == 100) {
                    return;
                }
            }
        };

        runnable.run();
        for (; ; ) {
            i++;
            System.out.println(i);
            if (i == 100) {
                return;
            }
        }
    }

}