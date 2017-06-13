import java.io.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    static class SysUtil {
        public static void main(String[] args) throws Exception {
            InputStream inputStream = new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            };
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

    static class StringUtil {

        public static void main(String[] args) {
            //3.String Tokenizer 获取分割字符串集合
            //List<String> stringByTokenizer = getStringByTokenizer("hello,hi", ",");
            //System.out.println(stringByTokenizer);

            String str = "能A文16adw文";
            System.out.println("中文首字母：" + getWordsIndexStr(str, false));

        }

        /**
         * 返回句首字母
         */
        public static Character getWordsIndexStr(String strChinese, boolean UpCase) {
            try {
                byte b[] = strChinese.getBytes("GBK");//把中文转化成byte数组
                for (int i = 0; i < b.length; i++) {
                    if ((b[i] & 255) > 128) {
                        int char1 = b[i++] & 255;
                        /**
                         * 左移运算符用“<<”表示，是将运算符左边的对象，
                         * 向左移动运算符右边指定的位数，并且在低位补零。
                         * 其实，向左移n位，就相当于乘上2的n次方
                         */
                        char1 <<= 8;
                        int chart = char1 + (b[i] & 255);
                        char pyIndexChar = getPYIndexChar((char) chart, UpCase);
                        return pyIndexChar;
                    }
                    char c = (char) b[i];
                    /**
                     * 确定指定字符是否可以是 Java 标识符中首字符以外的部分
                     */
                    if (!Character.isJavaIdentifierPart(c))
                        c = 'z';
                    return Character.toLowerCase(c);
                }
            } catch (Exception e) {
                System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519").append(e.getMessage()).toString());
            }
            return null;
        }

        /**
         * 得到首字母
         */
        private static char getPYIndexChar(char strChinese, boolean bUpCase) {

            int charGBK = strChinese;
            char result;
            if (charGBK >= 45217 && charGBK <= 45252)
                result = 'A';
            else if (charGBK >= 45253 && charGBK <= 45760)
                result = 'B';
            else if (charGBK >= 45761 && charGBK <= 46317)
                result = 'C';
            else if (charGBK >= 46318 && charGBK <= 46825)
                result = 'D';
            else if (charGBK >= 46826 && charGBK <= 47009)
                result = 'E';
            else if (charGBK >= 47010 && charGBK <= 47296)
                result = 'F';
            else if (charGBK >= 47297 && charGBK <= 47613)
                result = 'G';
            else if (charGBK >= 47614 && charGBK <= 48118)
                result = 'H';
            else if (charGBK >= 48119 && charGBK <= 49061)
                result = 'J';
            else if (charGBK >= 49062 && charGBK <= 49323)
                result = 'K';
            else if (charGBK >= 49324 && charGBK <= 49895)
                result = 'L';
            else if (charGBK >= 49896 && charGBK <= 50370)
                result = 'M';
            else if (charGBK >= 50371 && charGBK <= 50613)
                result = 'N';
            else if (charGBK >= 50614 && charGBK <= 50621)
                result = 'O';
            else if (charGBK >= 50622 && charGBK <= 50905)
                result = 'P';
            else if (charGBK >= 50906 && charGBK <= 51386)
                result = 'Q';
            else if (charGBK >= 51387 && charGBK <= 51445)
                result = 'R';
            else if (charGBK >= 51446 && charGBK <= 52217)
                result = 'S';
            else if (charGBK >= 52218 && charGBK <= 52697)
                result = 'T';
            else if (charGBK >= 52698 && charGBK <= 52979)
                result = 'W';
            else if (charGBK >= 52980 && charGBK <= 53688)
                result = 'X';
            else if (charGBK >= 53689 && charGBK <= 54480)
                result = 'Y';
            else if (charGBK >= 54481 && charGBK <= 55289)
                result = 'Z';
            else
                result = (char) (65 + (new Random()).nextInt(25));
            if (!bUpCase)
                result = Character.toLowerCase(result);
            return result;
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
         *
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
         *
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

    static class NumberUtil {

        private static final int DEF_DIV_SCALE = 10;

        public static boolean isInteger(String str) {
            if (str != null && !"".equals(str.trim())) {
                str = str.trim();
                Pattern pattern = Pattern.compile("(\\d+)\\.?[0]*");
                Matcher isNumStr = pattern.matcher(str);
                return isNumStr.matches();
            }
            return false;
        }

        public static boolean isDouble(String str) {
            if (str != null && !"".equals(str.trim())) {
                Pattern pattern = Pattern.compile("(\\d*\\.)?\\d+");
                Matcher isNum = pattern.matcher(str.trim());
                return isNum.matches();
            }
            return false;
        }

        public static Integer parseInt(String str) {
            if (str != null && !"".equals(str.trim())) {
                str = str.trim();
                if (str.matches("\\d*")) {
                    return Integer.parseInt(str);
                } else if (str.matches("(\\d+)\\.?[0]*")) {
                    String[] split = str.split("\\.");
                    return Integer.parseInt(split[0]);
                }
            }
            return null;
        }

        /**
         * 提供精确的加法运算。
         *
         * @param v1 被加数
         * @param v2 加数
         * @return 两个参数的和
         */
        public static double add(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.add(b2).doubleValue();
        }

        /**
         * 提供精确的减法运算。
         *
         * @param v1 被减数
         * @param v2 减数
         * @return 两个参数的差
         */
        public static double sub(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.subtract(b2).doubleValue();
        }

        /**
         * 提供精确的乘法运算。
         *
         * @param v1 被乘数
         * @param v2 乘数
         * @return 两个参数的积
         */
        public static double mul(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.multiply(b2).doubleValue();
        }

        /**
         * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
         *
         * @param v1 被除数
         * @param v2 除数
         * @return 两个参数的商
         */
        public static double div(double v1, double v2) {
            return div(v1, v2, DEF_DIV_SCALE);
        }

        /**
         * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
         *
         * @param v1    被除数
         * @param v2    除数
         * @param scale 表示表示需要精确到小数点以后几位。
         * @return 两个参数的商
         */

        public static double div(double v1, double v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        /**
         * 提供精确的小数位四舍五入处理。
         *
         * @param v     需要四舍五入的数字
         * @param scale 小数点后保留几位
         * @return 四舍五入后的结果
         */
        public static double round(double v, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    static class EncryptUtil {

        public static void main(String[] args) {

            String str = encryption("");
            System.out.println("一次加密Md5(VOD_MOVE)结果：" + str);
            String string = encryption("" + str);
            System.out.println("二次加密Md5(secondMD5" + str + ")结果：" + string);
            String s = SHA1("");
            System.out.println("sha1: "+s);

        }

        /**
         * 获取一个新的UUID
         */
        public static String createUUID() {
            return UUID.randomUUID().toString();
        }
        /**
         * @param plain 明文
         * @return 32位小写密文
         */
        public static String encryption(String plain) {
            String re_md5 = new String();
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plain.getBytes());
                byte b[] = md.digest();

                int i;

                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }

                re_md5 = buf.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return re_md5;
        }

        public static String SHA1(String decript) {
            try {
                MessageDigest digest = MessageDigest
                        .getInstance("SHA-1");
                digest.update(decript.getBytes());
                byte messageDigest[] = digest.digest();
                // Create Hex String
                StringBuffer hexString = new StringBuffer();
                // 字节数组转换为 十六进制 数
                for (int i = 0; i < messageDigest.length; i++) {
                    String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                    if (shaHex.length() < 2) {
                        hexString.append(0);
                    }
                    hexString.append(shaHex);
                }
                return hexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    static class Main {
        public static void main(String[] args) throws Exception {
        }
    }
}