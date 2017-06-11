/**
 * Created by Adam Yao on 2017/6/11.
 * 单例模式
 *
 * @Link https://en.wikipedia.org/wiki/Singleton_pattern
 */

public class SingletonPatternMain {
    private static final SingletonPatternMain INSTANCE = new SingletonPatternMain();

    private SingletonPatternMain() {
    }

    public static SingletonPatternMain getInstance() {
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