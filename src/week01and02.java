import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        cache.database.put("video1", "Movie Data");

        System.out.println(cache.getVideo("video1"));
        System.out.println(cache.getVideo("video1"));
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {

    int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

class MultiLevelCache {

    LRUCache<String, String> L1 = new LRUCache<>(10000);
    LRUCache<String, String> L2 = new LRUCache<>(100000);

    Map<String, String> database = new HashMap<>();

    public String getVideo(String id) {

        if (L1.containsKey(id))
            return L1.get(id);

        if (L2.containsKey(id)) {

            String v = L2.get(id);
            L1.put(id, v);
            return v;
        }

        String v = database.get(id);

        if (v != null)
            L2.put(id, v);

        return v;
    }
}