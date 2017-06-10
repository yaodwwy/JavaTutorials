import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {

        // Properties - 类似名-值对，key和value之间，可以用"="，":"或空格分隔，用"#"和"!"注释
        InputStream in = PropertiesDemo.class.getResourceAsStream("msconfig.properties");
        Properties prop = new Properties();
        prop.load(in);
        in.close();
//        prop.setProperty("o2", "ok2");
        System.out.println(prop.getProperty("o"));
    }
}
