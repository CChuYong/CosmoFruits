package fruits.cosmo.util;

import java.util.Objects;

public class DataPair<K, V> {
    private K k;
    private V v;

    public DataPair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, v);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DataPair && ((DataPair) obj).k.equals(k) && ((DataPair) obj).v.equals(v);
    }
}
