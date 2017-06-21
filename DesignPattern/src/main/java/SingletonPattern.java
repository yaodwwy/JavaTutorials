/**
 * Created by Adam Yao on 2017/6/11.
 * 单例模式
 *
 * @Link https://en.wikipedia.org/wiki/Singleton_pattern
 */

public class SingletonPattern {
    private static final SingletonPattern INSTANCE = new SingletonPattern();

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance() {
        return INSTANCE;
    }
}
final class LazySingleton {
    private static volatile LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}