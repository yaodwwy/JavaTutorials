import java.util.*;

/**
 * Created by Adam_Yao on 2017/9/27.
 */
public class CollectionDemos {

}

class CollectionDemo {
    public static void demo() {
        List<String> sList = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
        System.out.println(sList);
        List<String> sList2 = List.of("a", "b", "c");
        System.out.println(sList2);

        Set<String> sSet = new HashSet<>(Arrays.asList("A", "B", "C"));
        System.out.println(sSet);

        Map<String, Integer> strMap = new HashMap<>();
        strMap.put("a", 1);
        strMap.put("b", 2);
        strMap.put("c", 3);
        strMap = Collections.unmodifiableMap(strMap);

        Map<String, Integer> strMap2 = Map.of("a", 1, "b", 2);
        System.out.println(strMap2);
    }

}


/**
 * //7. 结构化数据
 * // 数组拷贝
 * System.arrayCopy(oldArray, 0, newArray, 0, oldArray.length);
 * <p>
 * // ArrayList
 * add(Object o)  // 在末尾添加给定元素
 * add(int i, Object o)  // 在指定位置插入给定元素
 * clear()  // 从集合中删除全部元素
 * Contains(Object o)  // 如果Vector包含给定元素，返回真值
 * get(int i)  // 返回指定位置的对象句柄
 * indexOf(Object o)  // 如果找到给定对象，则返回其索引值；否则，返回-1
 * remove(Object o)  // 根据引用删除对象
 * remove(int i)  // 根据位置删除对象
 * toArray()  // 返回包含集合对象的数组
 * <p>
 * // Iterator
 * List list = new ArrayList();
 * Iterator it = list.iterator();
 * while (it.hasNext())
 * Object o = it.next();
 * <p>
 * // 链表
 * LinkedList list = new LinkedList();
 * ListIterator it = list.listIterator();
 * while (it.hasNext())
 * Object o = it.next();
 * <p>
 * // HashMap
 * HashMap<String, String> hm = new HashMap<String, String>();
 * hm.get(key);  // 通过key得到value
 * hm.put("No1", "Hexinyu");
 * hm.put("No2", "Sean");
 * // 方法1: 获取全部键值
 * Iterator<String> it = hm.values().iterator();
 * while (it.hasNext()) {
 * String myKey = it.next();
 * String myValue = hm.get(myKey);
 * }
 * // 方法2: 获取全部键值
 * for (String key : hm.keySet()) {
 * String myKey = key;
 * String myValue = hm.get(myKey);
 * }
 * <p>
 * // Preferences - 与系统相关的用户设置，类似名-值对
 * Preferences prefs = Preferences.userNodeForPackage(ArrayDemo.class);
 * String text = prefs.get("textFontName", "lucida-bright");
 * String display = prefs.get("displayFontName", "lucida-balckletter");
 * System.out.println(text);
 * System.out.println(display);
 * // 用户设置了新值，存储回去
 * prefs.put("textFontName", "new-bright");
 * prefs.put("displayFontName", "new-balckletter");
 * <p>
 * // Properties - 类似名-值对，key和value之间，可以用"="，":"或空格分隔，用"#"和"!"注释
 * InputStream in = MediationServer.class.getClassLoader().getResourceAsStream("msconfig.properties");
 * Properties prop = new Properties();
 * prop.load(in);
 * in.close();
 * prop.setProperty(key, value);
 * prop.getProperty(key);
 * <p>
 * // 排序
 * 1. 数组：Arrays.sort(strings);
 * 2. List：Collections.sort(list);
 * 3. 自定义类：class SubComp implements Comparator
 * 然后使用Arrays.sort(strings, new SubComp())
 * <p>
 * // 两个接口
 * 1. java.lang.Comparable: 提供对象的自然排序，内置于类中
 * int compareTo(Object o);
 * boolean equals(Object o2);
 * 2. java.util.Comparator: 提供特定的比较方法
 * int compare(Object o1, Object o2)
 * <p>
 * // 避免重复排序，可以使用TreeMap
 * TreeMap sorted = new TreeMap(unsortedHashMap);
 * <p>
 * // 排除重复元素
 * Hashset hs - new HashSet();
 * <p>
 * // 搜索对象
 * binarySearch(): 快速查询 - Arrays, Collections
 * contains(): 线型搜索 - ArrayList, HashSet, Hashtable, linkedList, Properties, Vector
 * containsKey(): 检查集合对象是否包含给定 - HashMap, Hashtable, Properties, TreeMap
 * containsValue(): 主键(或给定值) - HashMap, Hashtable, Properties, TreeMap
 * indexOf(): 若找到给定对象，返回其位置 - ArrayList, linkedList, List, Stack, Vector
 * search(): 线型搜素 - Stack
 * <p>
 * // 集合转数组
 * toArray();
 * <p>
 * // 集合总结
 * Collection: Set - HashSet, TreeSet
 * Collection: List - ArrayList, Vector, LinkedList
 * Map: HashMap, HashTable, TreeMap
 */