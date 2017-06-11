package utils;

import java.io.*;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static javax.script.ScriptEngine.FILENAME;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class IOTest {
    void doFile(Reader is) throws IOException {
        int c;
        while ((c = is.read()) != -1) {
            System.out.println((char) c);
        }
    }

    public static void main(String[] args) throws Exception {

        //10. 输入和输出
        // Stream, Reader, Writer
        //Stream:
        //处理字节流
        //Reader / Writer:处理字符，通用Unicode

        // 从标准输入设备读数据
        //1. 用System.in的BufferedInputStream() 读取字节
        int b = System.in.read();
        System.out.println("Read data: " + (char) b);  // 强制转换为字符
        //2. BufferedReader读取文本
        //如果从Stream转成Reader，使用InputStreamReader类
        BufferedReader bufferedReader1 = new BufferedReader(new
                InputStreamReader(System.in));
        String inputLine;
        while ((inputLine = bufferedReader1.readLine()) != null) {
            System.out.println(inputLine);
            int val = Integer.parseInt(inputLine);  // 如果inputLine为整数
        }
        bufferedReader1.close();

        // 向标准输出设备写数据
        //1. 用System.out的println() 打印数据
        //2. 用PrintWriter打印
        PrintWriter pw = new PrintWriter(System.out);
        String myAnswer = "--";
        pw.println("The answer bufferedReader " + myAnswer + " at this time.");

        // Formatter类
        //格式化打印内容
        Formatter fmtr = new Formatter();
        fmtr.format("%1$04d - the year of %2$f", 1951, Math.PI);
        //或者System.out.printf();
        //或者System.out.format();

        // 原始扫描

        /**
         *  Scanner扫描
         */
        //Scanner可以读取File,InputStream,String,Readable
        Scanner scan = new Scanner(new File("a.txt"));
        while (scan.hasNext()) {
            String s = scan.next();
        }

        // 读取文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader("myFile.txt"));
        BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream("bytes.bat"));
        bufferedReader.close();
        bufferedOutputStream1.close();

        // 复制文件
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("oldFile.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("newFile.txt"));
        int i;
        while ((i = bufferedReader1.read()) != -1) {
            bufferedOutputStream.write(i);
        }
        bufferedReader1.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();

        // 文件读入字符串
        StringBuffer sb = new StringBuffer();
        char[] chars = new char[8192];
        int n;
        // 读一个块，如果有字符，加入缓冲区
        while ((n = bufferedReader1.read(chars)) > 0)

        {
            sb.append(chars, 0, n);
        }
        String s = sb.toString();
        System.out.println(s);

        // 重定向标准流
        String logfile = "error.log";
        System.setErr(new PrintStream(new FileOutputStream(logfile)));

        // 读写不同字符集文本
        BufferedReader chinese = new BufferedReader(new InputStreamReader(new FileInputStream("chinese.txt"), "ISO8859_1"));
        PrintWriter standard = new PrintWriter(new OutputStreamWriter(new FileOutputStream("standard.txt"), "UTF-8"));

        // 读取二进制数据
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("a.txt"));
        dataOutputStream.writeInt(new Integer(12));
        dataOutputStream.writeDouble(new Double(123));
        dataOutputStream.close();

        // 从指定位置读数据
        RandomAccessFile raf = new RandomAccessFile("fileName", "r");  // r表示已只读打开
        raf.seek(15);  // 从15开始读
        raf.readInt();
        raf.readLine();

        // 串行化对象
        //对象串行化，必须实现Serializable接口
        // 保存数据到磁盘
        ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILENAME)));
        os.writeObject(new Object());
        os.close();
        // 读出数据
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILENAME));
        is.readObject();
        is.close();

        // 读写Jar或Zip文档
        ZipFile zippy = new ZipFile("a.jar");
        Enumeration all = zippy.entries();  // 枚举值列出所有文件清单
        while (all.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) all.nextElement();
            if (entry.isDirectory()) {
                System.out.println("Directory: " + entry.getName());
            }
            // 读写文件
            FileOutputStream fileOutputStream = new FileOutputStream(entry.getName());
            InputStream inputStream = zippy.getInputStream(entry);
            int i1 = 0;
            byte[] bytes = new byte[8092];
            while ((i1 = inputStream.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, i1);
                inputStream.close();
                fileOutputStream.close();
            }
        }

        // 读写gzip文档
        FileInputStream fin = new FileInputStream(FILENAME);
        GZIPInputStream gzis = new GZIPInputStream(fin);
        InputStreamReader xover = new InputStreamReader(gzis);
        BufferedReader bufferedReader2 = new BufferedReader(xover);
        String line;
        while ((line = bufferedReader2.readLine()) != null)
            System.out.println("Read: " + line);
    }
}
