package hashmap;

public interface Map<K, V> {
    public V get(K k);

    public V put(K k, V v);

    public int size();

    interface Entry<K, V> {

        public K getKey();

        public V getValue();

    }
}
