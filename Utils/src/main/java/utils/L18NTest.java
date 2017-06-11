package utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class L18NTest {
    public static void main(String[] args) {
        //12. 国际化和本地化
        // I18N资源
        ResourceBundle rb = ResourceBundle.getBundle("Menus");
        String label = rb.getString("exit.label");
        // ResourceBundle相当于名值对，获取Menus按钮的区域属性
        //Menus_cn.properties:不同区域的属性文件

        // 列出有效区域
        Locale[] list = Locale.getAvailableLocales();

        // 指定区域
        Locale cnLocale = Locale.CHINA;

        // 设置默认区域
        Locale.setDefault(Locale.CHINA);


        //输出: At 10:10:08 on 2009-6-18, myfile.txt could nto be opened.

        // 从资源文件中读消息
        //Widgets.properties在com.sean.cook.chap11下
        ResourceBundle bundle = ResourceBundle.getBundle("com.sean.cook.chap11.Widgets");
        String propt = bundle.getString("filedialogs.cantopen.string");

        Object data = null;
        String result = MessageFormat.format(bundle.getString("filedialogs.cantopen.format"), data);
    }
    // 格式化消息
    public static class MessageFormatDemo {
        static Object[] data = {
                new java.util.Date(),
                "myfile.txt",
                "could nto be opened"
        };

        public static void main(String[] args) {
            String result = MessageFormat.format("At {0,time} on {0,date}, {1} {2}.", data);
            System.out.println(result);
        }
    }
}
