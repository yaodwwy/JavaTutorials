package utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        //11. 目录和文件操作
        // 获取文件信息
        // exists(): 如果文件存在，返回true
        // getCanonicalPath(): 获取全名
        // getName(): 文件名
        // getParent(): 父目录
        // canRead(): 如果文件可读，返回true
        // canWrite(): 如果文件可写，返回true
        // lastModified(): 文件更新时间
        // length(): 文件大小
        // isFile(): 如果是文件，返回true
        // ifDirectory(): 如果是目录，返回true
        // 要调用文件的这些方法，必须
        File f = new File("fileName");

        // 创建文件
        File file = new File("c:\\test\\mytest.txt");
        file.createNewFile();  // 创建mytest.txt文件到test目录下

        // 修改文件名
        File file1 = new File("c:\\test\\mytest.txt");
        file1.renameTo(new File("c:\\test\\google.txt"));
        //把mytest.txt修改成google.txt

        // 删除文件
        File file3 = new File("c:\\test\\mytest.txt");
        file3.delete();

        // 临时文件
        File file2 = new File("C:\\test");  // 指定一个文件夹
        // 在test文件夹中创建foo前缀，tmp后缀的临时文件
        File tmp = File.createTempFile("foo", "tmp", file2);
        tmp.deleteOnExit();  // 在程序结束时删除该临时文件

        // 更改文件属性
        //setReadOnly();设置为只读
        //setlastModified():设置最后更改时间

        // 列出当前文件夹的文件列表
        String[] dir = new java.io.File(".").list();
        java.util.Arrays.sort(dir);
        for (int i = 0; i < dir.length; i++) {
            System.out.println(dir[i]);
        }

        // 过滤文件列表
        class OnlyJava implements FilenameFilter {
            public boolean accept(File dir, String s) {
                if (s.endsWith(".java") || s.endsWith(".class") || s.endsWith(".jar"))
                    return true;
                return false;
            }
        }

        // 获取根目录
        File[] rootDir = File.listRoots();
        for (int i = 0; i < rootDir.length; i++) {
            System.out.println(rootDir[i]);
        }

        // 创建新目录
        new File("/home/ian/bin").mkdir();  // 如果"/home/ian"存在，则可以创建bin目录
        new File("/home/ian/bin").mkdirs();  // 如果"/home/ian"不存在，会创建所有的目录
    }
}
