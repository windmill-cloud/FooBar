package structures;
import java.util.List;

public class IntFileIterator {
    public IntFileIterator(List<Integer> list) {
        this.list = list;
        curr = 0;
    }
    public int curr;
    public List<Integer> list;
    public boolean hasNext() {
        return curr < list.size();
    }

    public int next() {
        if (!hasNext()) return -1;
        return list.get(curr++);
    }
}
