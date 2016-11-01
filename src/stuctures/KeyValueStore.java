/**
 * Created by Christina on 2/28/16.
 */
public interface KeyValueStore {
    public void add(int key, int value);

    public void remove(int key);

    public int get(int key);

    public int latestKey();
}
