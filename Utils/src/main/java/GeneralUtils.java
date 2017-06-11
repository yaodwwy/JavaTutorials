import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public class GeneralUtils {
    private static final Logger logger = Logger.getLogger("GeneralUtils");

    public static class StringUtil {

        public static void main(String[] args) {
            //3.String Tokenizer 获取分割字符串集合
            List<String> stringByTokenizer = getStringByTokenizer("hello,hi", ",");
            System.out.println(stringByTokenizer);
        }

        /**
         * String Tokenizer 获取分割字符串集合 包含分隔符
         */
        public static List<String> getStringByTokenizer(String str, String delim, boolean returnDelims) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, delim, returnDelims);
            List<String> list = new ArrayList<>();
            while (stringTokenizer.hasMoreElements()) {
                list.add(stringTokenizer.nextToken());
            }
            return list;
        }

        /**
         * String Tokenizer 获取分割字符串集合 不包含分隔符
         */
        public static List<String> getStringByTokenizer(String str, String delim) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, delim);
            List<String> list = new ArrayList<>();
            while (stringTokenizer.hasMoreElements()) {
                list.add(stringTokenizer.nextToken());
            }
            return list;
        }
    }

    static class SysUtil {
        public static void main(String[] args) throws Exception {

            System.out.println(getSystemProperty("bufferedOutputStream.name"));
            //1.获取环境变量
            String path = getSystemEvn("PATH");
            String javaHome = getSystemEvn("JAVA_HOME");
            //System.out.println(path + " \n " + javaHome);

            //2.获取系统属性
            String systemProperty = getSystemProperty("java.specification.version");
            System.out.println(systemProperty);
            Properties systemProperties = getSystemProperty();
            //System.out.println(systemProperties);  //得到所有属性值

            //JAVA 操作系统注册表 写入
            String s = setWindowsPreferenceReg(PreferencesRoot.USER, "string", "test", String.class, null);
            System.out.println(s);
            //JAVA 操作系统注册表 读取
            Boolean windowsPreferenceReg = getWindowsPreferenceReg(PreferencesRoot.USER, "string", "test", Boolean.class, true);
            setWindowsPreferenceReg(PreferencesRoot.USER, "", "ok", int.class, 9);
            Integer ok = getWindowsPreferenceReg(PreferencesRoot.USER, "", "ok", int.class, 1);
            System.out.println(ok);
        }

        /**
         * JAVA 操作系统注册表 写入
         * 计算机\HKEY_CURRENT_USER\Software\JavaSoft\Prefs
         * 计算机\HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Prefs
         */
        public static <T> T setWindowsPreferenceReg(PreferencesRoot rootType, String dir, String key, Class<T> valueType, T value) throws Exception {
            if (value == null || valueType == null || key == null) {
                return null;
            }
            Preferences prefs = rootType.getRoot().node(dir);
            if (String.class.getName().equals(valueType.getName())) {
                prefs.put(key, (String) value);
            } else if (Boolean.class.getName().equals(valueType.getName())) {
                prefs.putBoolean(key, (Boolean) value);
            } else if (byte.class.getName().equals(valueType.getName())) {
                prefs.putByteArray(key, (byte[]) value);
            } else if (Double.class.getName().equals(valueType.getName())) {
                prefs.putDouble(key, (Double) value);
            } else if (Float.class.getName().equals(valueType.getName())) {
                prefs.putFloat(key, (Float) value);
            } else if (int.class.getName().equals(valueType.getName())) {
                prefs.putInt(key, (Integer) value);
            } else if (long.class.getName().equals(valueType.getName())) {
                prefs.putLong(key, (Long) value);
            } else {
                throw new Exception("value type bufferedReader unavailable");
            }
            prefs.flush();
            return value;
        }

        /**
         * JAVA 操作系统注册表 移除目录
         *
         * @throws BackingStoreException
         */
        public static void removeWindowsPreferenceReg(PreferencesRoot rootType, String dir) throws BackingStoreException {
            rootType.getRoot().node(dir).removeNode();
        }

        /**
         * JAVA 操作系统注册表 移除 k v
         */
        public static void removeWindowsPreferenceReg(PreferencesRoot rootType, String dir, String key) {
            rootType.getRoot().node(dir).remove(key);
        }

        /**
         * JAVA 操作系统注册表 读出
         */
        public static <T> T getWindowsPreferenceReg(PreferencesRoot rootType, String dir, String key, Class<T> valueType, Object defaultValue) throws Exception {
            Preferences prefs = rootType.getRoot().node(dir);
            Object obj = null;
            if (String.class.getName().equals(valueType.getName())) {
                obj = prefs.get(key, null != defaultValue ? (String) defaultValue : null);
            } else if (Boolean.class.getName().equals(valueType.getName())) {
                obj = prefs.getBoolean(key, (null != defaultValue) ? (Boolean) defaultValue : null);
            } else if (byte.class.getName().equals(valueType.getName())) {
                obj = prefs.getByteArray(key, null != defaultValue ? (byte[]) defaultValue : null);
            } else if (Double.class.getName().equals(valueType.getName())) {
                obj = prefs.getDouble(key, null != defaultValue ? (Double) defaultValue : null);
            } else if (Float.class.getName().equals(valueType.getName())) {
                obj = prefs.getFloat(key, null != defaultValue ? (Float) defaultValue : null);
            } else if (int.class.getName().equals(valueType.getName())) {
                obj = prefs.getInt(key, null != defaultValue ? (int) defaultValue : null);
            } else if (long.class.getName().equals(valueType.getName())) {
                obj = prefs.getLong(key, (null != defaultValue) ? (long) defaultValue : null);
            } else {
                throw new Exception("value type bufferedReader unavailable");
            }
            return (T) obj;
        }

        /**
         * 枚举类 系统参数、用户参数
         * 这是如果设计成单例应该性能更高
         */
        public enum PreferencesRoot {
            SYSTEM(Preferences.systemRoot()), USER(Preferences.userRoot());
            private final Preferences root;

            PreferencesRoot(Preferences root) {
                this.root = root;
            }

            public Preferences getRoot() {
                return root;
            }
        }

        /**
         * 获取系统属性 list
         */
        public static Properties getSystemProperty() {
            return System.getProperties();
        }

        /**
         * 获取系统属性
         */
        public static String getSystemProperty(String key) {
            return System.getProperty(key);
        }

        /**
         * 获取环境变量
         */
        public static String getSystemEvn(String env) {
            return System.getenv(env);
        }


    }

    static class NumberUtil {

    }

    static class DateUtil {

        public static void main(String[] args) {
            System.out.println(valiDateTimeWithLongFormat("2012/01/01 99:01:02"));
            System.out.println(getDateFromString("2017-01-01 23:01:09"));
        }

        /**
         * 输出 格式化默认区域 日期
         */
        public static String getStringFromString(Date date) {
            return DateFormat.getInstance().format(date);
        }

        /**
         * 输出 格式化日期
         */
        public static String getStringFromString(String pattern, Date date) {
            if (datePatternCheck(pattern)) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.format(date);
            }
            return null;
        }

        /**
         * 匹配以下字符串 输出日期
         * 2017-01-01 00:00:00
         * 2017-01-01
         * 2017/01/01 00:00:00
         * 2017/01/01
         */
        public static Date getDateFromString(String datevalue) {
            if (valiDateTimeWithLongFormat(datevalue)) {
                SimpleDateFormat sdf;
                if (datevalue.length() > 10 & '-' == (datevalue.charAt(4))) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                } else if (datevalue.length() > 10 & '/' == (datevalue.charAt(4))) {
                    sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                } else if ("-".equals(datevalue.charAt(4))) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                } else {
                    sdf = new SimpleDateFormat("yyyy/MM/dd");
                }
                try {
                    return sdf.parse(datevalue);
                } catch (ParseException e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            }
            return null;
        }


        /**
         * 日期格式化输入 检查
         * "yyyy-MM-dd hh:mm:ss"
         * "yyyy-MM-dd"
         * "yyyy/MM/dd hh:mm:ss"
         * "yyyy/MM/dd"
         */
        public static boolean valiDateTimeWithLongFormat(String timeStr) {
            String format = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                    + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])" +
                    "|((((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])))" +
                    "|(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]) " +
                    "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])" +
                    "|((((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])))";
            Pattern pattern = Pattern.compile(format);
            Matcher matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
                matcher = pattern.matcher(timeStr);
                if (matcher.matches()) {
                    int y = Integer.valueOf(matcher.group(1));
                    int m = Integer.valueOf(matcher.group(2));
                    int d = Integer.valueOf(matcher.group(3));
                    if (d > 28) {
                        Calendar c = Calendar.getInstance();
                        c.set(y, m - 1, 1);
                        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                        return (lastDay >= d);
                    }
                }
                return true;
            }
            return false;
        }

        /**
         * 日期格式化输出 检查
         *
         * @param pattern
         * @return
         */
        public static boolean datePatternCheck(String pattern) {
            if (pattern != null)
                switch (pattern) {
                    case "yyyy-MM-dd hh:mm:ss":
                        break;
                    case "yyyy-MM-dd":
                        break;
                    case "yyyy/MM/dd hh:mm:ss":
                        break;
                    case "yyyy/MM/dd":
                        break;
                    case "hh:mm:ss":
                        break;
                    case "hh:mm":
                        break;
                    default:
                        return false;
                }
            return true;
        }
    }

    static class PropertiesUtil {
        public static void main(String[] args) throws Exception {
            String ok = getValue("msconfig.properties", "", "aa");
            System.out.println(ok);
            Properties properties = getProperties("msconfig.properties");
            System.out.println(properties);
            Properties properties2 = getProperties("");
            System.out.println(properties2);
        }

        /**
         * 根据 key 获取属性文件的 value
         * @throws IOException
         */
        public static String getValue(String pathName, String key, String def) throws IOException {
            if (pathName == null || "".equals(pathName.trim()) || key == null || "".equals(key.trim())) {
                return null;
            }
            // Properties - 类似名-值对，key和value之间，可以用"="，":"或空格分隔，用"#"和"!"注释
            InputStream in = PropertiesDemo.class.getResourceAsStream(pathName);
            Properties prop = new Properties();
            prop.load(in);
            in.close();
            return prop.getProperty(key, def);
        }

        /**
         * 根据 pathName 目录及文件名 获取属性文件的 k v 列表
         * @throws IOException
         */
        public static Properties getProperties(String pathName) throws IOException {
            if (pathName == null || "".equals(pathName.trim())) {
                return null;
            }
            // Properties - 类似名-值对，key和value之间，可以用"="，":"或空格分隔，用"#"和"!"注释
            InputStream in = PropertiesDemo.class.getResourceAsStream(pathName);
            Properties prop = new Properties();
            prop.load(in);
            in.close();
            return prop;
        }
    }

    static class Main {
        public static void main(String[] args) throws IOException, BackingStoreException {
            test2();
        }

        public static void test1() throws IOException {

            // 从标准输入设备读数据
            //1. 用System.in的BufferedInputStream()读取字节
            int b = System.in.read();
            System.out.println("Read data: " + (char)b);  // 强制转换为字符
            //2. BufferedReader读取文本
            //如果从Stream转成Reader，使用InputStreamReader类
            BufferedReader is = new BufferedReader(new
                    InputStreamReader(System.in));
            String inputLine;
            for (int i = 0; i <2 ; i++) {
                if((inputLine = is.readLine()) != null) {
                    System.out.println(inputLine);
                    int val = Integer.parseInt(inputLine);  // 如果inputLine为整数
                }
            }
            is.close();
        }

        public static void test2() throws IOException {
            // 向标准输出设备写数据
            //1. 用System.out的println()打印数据
            //2. 用PrintWriter打印
            PrintWriter pw = new PrintWriter(System.out);
            pw.println("The answer bufferedReader name at this time.");
            pw.flush();
            System.out.println("..");
            // Formatter类
            //格式化打印内容
            Formatter fmtr = new Formatter();
            fmtr.format("%1$04d - the year of %2$f", 1951, Math.PI);
            System.out.println(fmtr);
            //或者System.out.printf();或者System.out.format();
            // 原始扫描
            InputStream resourceAsStream = GeneralUtils.class.getResourceAsStream("msconfig.properties");
            System.out.println(resourceAsStream);
            BufferedReader is = new BufferedReader(new
                    InputStreamReader(resourceAsStream));
//            String inputLine;
//            for (int i = 0; i <2 ; i++) {
//                if((inputLine = bufferedReader.readLine()) != null) {
//                    System.out.println(inputLine);
//                }
//            }
            doFile(is);
            // Scanner扫描
           // Scanner可以读取File, InputStream, String, Readable
            try {
                Scanner scan = new Scanner(new File("d:/a.txt"));
                while (scan.hasNext()) {
                    String s = scan.next();
                    System.out.println(s);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 读取文件
            BufferedReader is2 = new BufferedReader(new FileReader("d:/a.txt"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/bytes.bat"));
            is2.close();
            bos.close();

            // 复制文件
            BufferedInputStream is3 = new BufferedInputStream(new FileInputStream("d:/a.txt"));
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("d:/newFile.txt"));
            int b;
            while ((b = is3.read()) != -1) {
                os.write(b);
            }
            is3.close();
            os.close();

            // 文件读入字符串
            StringBuffer sb = new StringBuffer();
            char[] b5 = new char[8192];
            b5[0] = 'a';
            int n = 1;
            // 读一个块，如果有字符，加入缓冲区
            while ((n = is.read(b5)) > 0) {
                sb.append(b5, 0, n);
                System.out.println("1" + sb.toString());
            }
            System.out.println("2" + sb.toString());


        }
        }
        // 原始扫描
        static void doFile(Reader is) throws IOException {
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char)c);
            }
        }
    }




/*

//10. 输入和输出
// Stream, Reader, Writer
Stream:处理字节流
        Reader/Writer:处理字符，通用Unicode

// 从标准输入设备读数据
        1.用System.in的BufferedInputStream()读取字节
        int b=System.in.read();
        System.out.println("Read data: "+(char)b);  // 强制转换为字符
        2.BufferedReader读取文本
        如果从Stream转成Reader，使用InputStreamReader类
        BufferedReader bufferedReader=new BufferedReader(new
        InputStreamReader(System.in));
        String inputLine;
        while((inputLine=bufferedReader.readLine())!=null){
        System.out.println(inputLine);
        int val=Integer.parseInt(inputLine);  // 如果inputLine为整数
        }
        bufferedReader.close();

// 向标准输出设备写数据
        1.用System.out的println()打印数据
        2.用PrintWriter打印
        PrintWriter pw=new PrintWriter(System.out);
        pw.println("The answer bufferedReader "+myAnswer+" at this time.");

// Formatter类
        格式化打印内容
        Formatter fmtr=new Formatter();
        fmtr.format("%1$04d - the year of %2$f",1951,Math.PI);
        或者System.out.printf();或者System.out.format();

// 原始扫描
        void doFile(Reader bufferedReader){
        int c;
        while((c=bufferedReader.read())!=-1){
        System.out.println((char)c);
        }
        }

// Scanner扫描
        Scanner可以读取File,InputStream,String,Readable
        try{
        Scanner scan=new Scanner(new File("a.txt"));
        while(scan.hasNext()){
        String s=scan.next();
        }
        }catch(FileNotFoundException e){
        e.printStackTrace();
        }
        }

// 读取文件
        BufferedReader bufferedReader=new BufferedReader(new FileReader("myFile.txt"));
        BufferedOutputStream bufferedOutputStream1=new BufferedOutputStream(new FileOutputStream("bytes.bat"));
        bufferedReader.close();
        bufferedOutputStream1.close();

// 复制文件
        BufferedIutputStream bufferedReader=new BufferedIutputStream(new FileIutputStream("oldFile.txt"));
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream("newFile.txt"));
        int b;
        while((b=bufferedReader.read())!=-1){
        bufferedOutputStream.write(b);
        }
        bufferedReader.close();
        bufferedOutputStream.close();

// 文件读入字符串
        StringBuffer sb=new StringBuffer();
        char[]b=new char[8192];
        int n;
// 读一个块，如果有字符，加入缓冲区
        while((n=bufferedReader.read(b))>0){
        sb.append(b,0,n);
        }
        return sb.toString();

// 重定向标准流
        String logfile="error.log";
        System.setErr(new PrintStream(new FileOutputStream(logfile)));

// 读写不同字符集文本
        BufferedReader chinese=new BufferedReader(new InputStreamReader(new FileInputStream("chinese.txt"),"ISO8859_1"));
        PrintWriter standard=new PrintWriter(new OutputStreamWriter(new FileOutputStream("standard.txt"),"UTF-8"));

// 读取二进制数据
        DataOutputStream bufferedOutputStream=new DataOutputStream(new FileOutputStream("a.txt"));
        bufferedOutputStream.writeInt(i);
        bufferedOutputStream.writeDouble(d);
        bufferedOutputStream.close();

// 从指定位置读数据
        RandomAccessFile raf=new RandomAccessFile(fileName,"r");  // r表示已只读打开
        raf.seek(15);  // 从15开始读
        raf.readInt();
        raf.radLine();

// 串行化对象
        对象串行化，必须实现Serializable接口
// 保存数据到磁盘
        ObjectOutputStream bufferedOutputStream=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILENAME)));
        bufferedOutputStream.writeObject(serialObject);
        bufferedOutputStream.close();
// 读出数据
        ObjectInputStream bufferedReader=new ObjectInputStream(new FileInputStream(FILENAME));
        bufferedReader.readObject();
        bufferedReader.close();

// 读写Jar或Zip文档
        ZipFile zippy=new ZipFile("a.jar");
        Enumeration all=zippy.entries();  // 枚举值列出所有文件清单
        while(all.hasMoreElements()){
        ZipEntry entry=(ZipEntry)all.nextElement();
        if(entry.isFile())
        println("Directory: "+entry.getName());

        // 读写文件
        FileOutputStream bufferedOutputStream=new FileOutputStream(entry.getName());
        InputStream bufferedReader=zippy.getInputStream(entry);
        int n=0;
        byte[]b=new byte[8092];
        while((n=bufferedReader.read(b))>0){
        bufferedOutputStream.write(b,0,n);
        bufferedReader.close();
        bufferedOutputStream.close();
        }
        }

// 读写gzip文档
        FileInputStream fin=new FileInputStream(FILENAME);
        GZIPInputStream gzis=new GZIPInputStream(fin);
        InputStreamReader xover=new InputStreamReader(gzis);
        BufferedReader bufferedReader=new BufferedReader(xover);
        String line;
        while((line=bufferedReader.readLine())!=null)
        System.out.println("Read: "+line);
        }
        }*/
