import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Christina on 2/28/16.
 */
public class keyValueStoreImple implements KeyValueStore {
    Map<Integer, Integer> map;
    List<Integer> lastestKey;
    Map<Integer, Integer> index;
    public keyValueStoreImple() {
        map = new HashMap<>();
        lastestKey = new LinkedList<>();
    }
    @Override
    public void add(int key, int value) {
        map.put(key, value);
        lastestKey.add(key);
    }

    @Override
    public void remove(int key) {
        map.remove(key);
    }

    @Override
    public int get(int key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return -1;
        }
    }

    @Override
    public int latestKey() {
        return 0;
    }
}
