import java.io.FileOutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * 在Windows平台中，用户参数项在注册表中的根节点是
 HKEY_CURRENT_USER/Software/JavaSoft/Prefs
 系统参数项在注册表中的根节点是
 HKEY_LOCAL_MACHINE/SOFTWARE/JavaSoft/Prefs
 而我们指定的节点路径是位于这些根节点之下的.
 如何读写数据
 Preferences提供了多种类型数据的读写方法。
 写(put)的方法,
 读(get)的方法,

 六月 10, 2017 9:22:23 下午 java.util.prefs.WindowsPreferences <init>
 WARNING: Could not open/create prefs root node Software\JavaSoft\Prefs at root 0x80000002. Windows RegCreateKeyEx(...) returned error code 5.
 */
public class PrefsDemo {
    public static void main(String args[]) throws BackingStoreException {


        String keys[] = {"sunway", "copyright", "author"};
        String values[] = {"sunway technology company", "copyright 2002", "turbochen@163.com"};

        Preferences prefsdemo = Preferences.userNodeForPackage(PrefsDemo.class).node("/im");
//        Preferences prefsdemo = Preferences.userRoot().node("/com/sunway/spc");

        for (int i = 0; i < keys.length; i++) {
            prefsdemo.put(keys[i], values[i]);
        }
        try {
            FileOutputStream fos = new FileOutputStream("/prefsdemo.xml");
            prefsdemo.exportNode(fos);
        } catch (Exception e) {
            System.err.println("Cannot export nodes: " + e);
        }
        Preferences.userRoot().node("/im").remove("sunway");
        Preferences.userRoot().node("/com").removeNode();
        Preferences.userRoot().node("/im").removeNode();
        Preferences.userRoot().node("/<unnamed>").removeNode();
        Preferences.userRoot().node("/user").removeNode();

    }
}