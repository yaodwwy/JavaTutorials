import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SystemTest {

    /**
     * 将数组a中，从下标为1开始，复制到数组b从下标3开始的位置，总共复制2个。
     */
    @Test
    public void arraycopy() {
        int[] a = {1, 2, 3, 4};
        int[] b = new int[5];
        System.arraycopy(a, 1, b, 3, 2);

    }

    /**
     * 当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
     */
    @Test
    public void currentTimeMillis() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            int c = 0;
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(("time: " + time));
    }

    /**
     * status的值为0代表正常退出，非零代表异常退出
     */
    @Test
    public void exit() {
        try {
            System.exit(0);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    /**
     * 系统中常见的属性名以及属性的作用如下表所示
     * java.version     * Java 运行时环境版本
     * java.home        * Java 安装目录
     * os.name          * 操作系统的名称
     * os.version       * 操作系统的版本
     * user.name        * 用户的账户名称
     * user.home        * 用户的主目录
     * user.dir         * 用户的当前工作目录
     */
    @Test
    public void getProperty() {
        String osName = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        System.out.println("当前操作系统是：" + osName);
        System.out.println("当前用户是：" + user);
    }
}
