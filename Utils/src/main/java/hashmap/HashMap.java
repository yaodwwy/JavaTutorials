package hashmap;

public class HashMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] table;
    static int defaultLength = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size = 0;

    public HashMap(int length) {
        defaultLength = length;
        this.table = new Entry[defaultLength];
    }

    public HashMap() {
        this(defaultLength);
    }

    @Override
    public V get(K k) {
        Entry<K, V> entiry = getEntiry(k);
        return entiry == null ? null : entiry.getValue();
    }

    private Entry<K, V> getEntiry(K k) {
        if (size == 0) {
            return null;
        }
        int index = hash(k);

        for (Entry e = table[index]; e != null; e = e.next) {
            if (e.getKey() == k || e.getKey().equals(k)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public V put(K k, V v) {
        //扩容
        resize();

        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = addEntry(k, v, null);
            size++;
        } else {
            table[index] = addEntry(k, v, entry);
        }
        return table[index].getValue();
    }

    private void resize() {
        if (size >= defaultLength * DEFAULT_LOAD_FACTOR) {
            Entry<K, V>[] oldTab = table;
            Entry<K, V>[] newTab = new Entry[defaultLength * 2];
            for (int i = 0; i < oldTab.length; i++) {
                if (oldTab[i] != null) {
                    newTab[i] = oldTab[i];
                }
            }
            defaultLength = newTab.length;
            table = newTab;
        }
    }

    private Entry<K, V> addEntry(K k, V v, Entry next) {
        return new Entry(k, v, next);
    }

    private int hash(K k) {
        int index = k.hashCode() % defaultLength;
        return index >= 0 ? index : -index;
    }

    public static void main(String[] args) {
        long startPut = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            map.put("测试内容" + i, "hash表" + i);
        }
        System.out.println("扩容大小: " + map.size() + " 元素大小: " + HashMap.defaultLength + " Put耗时: " + (System.currentTimeMillis() - startPut));

        long startGet = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            map.get("测试内容" + i);
        }
        System.out.println("扩容大小: " + map.size() + " 元素大小: " + HashMap.defaultLength + " Get耗时: " + (System.currentTimeMillis() - startGet));

        long startPut2 = System.currentTimeMillis();
        java.util.Map<String, String> map2 = new java.util.HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            map2.put("测试内容" + i, "hash表" + i);
        }
        System.out.println("扩容大小: " + map2.size() + " 元素大小: " + HashMap.defaultLength + " Put耗时: " + (System.currentTimeMillis() - startPut2));

        long startGet2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            map2.get("测试内容" + i);
        }
        System.out.println("扩容大小: " + map2.size() + " 元素大小: " + HashMap.defaultLength + " Get耗时: " + (System.currentTimeMillis() - startGet2));
    }


    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry {
        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
