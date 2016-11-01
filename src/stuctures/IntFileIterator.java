import java.util.List;

/**
 * Created by Christina on 2/19/16.
 */
public class IntFileIterator {
    IntFileIterator(List<Integer> list) {
        this.list = list;
        curr = 0;
    }
    int curr;
    List<Integer> list;
    boolean hasNext() {
        return curr < list.size();
    }

    int next() {
        if (!hasNext()) return -1;
        return list.get(curr++);
    }
}
