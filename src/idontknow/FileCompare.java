import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christina on 2/19/16.
 */
public class FileCompare {

    /**
     * public class IntFileIterator {
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
     }*/
    //"", ""
    //abc, bc
    //abc, ab
    //abc, acc
    //abc, abc  false
    /**
     * iterate all iterator into a string
     *
     * */
    public boolean isDistanceZeroOrOne(IntFileIterator a, IntFileIterator b) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        while (a.hasNext() && b.hasNext()) {
            aList.add(a.next());
            bList.add(b.next());
        }
        while (a.hasNext()) {
            aList.add(a.next());
            if (aList.size() - bList.size() > 1) return false;
        }
        while (b.hasNext()) {
            bList.add(b.next());
            if (bList.size() - aList.size() > 1) return false;
        }
        for (int i = 0; i < aList.size() && i < bList.size(); i++) {
            if (aList.get(i) != bList.get(i)) {
                if (aList.size() == bList.size()) {
                    return partListEquals(aList, i + 1, bList, i + 1);
                }
                if (aList.size() < bList.size()) {
                    return partListEquals(aList, i, bList, i + 1);
                }
                return partListEquals(aList, i + 1, bList, i);
            }
        }
        return false;
    }

    private boolean partListEquals(List<Integer> aList, int aStart, List<Integer> bList, int bStart) {
        while (aStart < aList.size()) {
            if (aList.get(aStart++) != bList.get(bStart++)) return false;
        }
        return true;
    }



    public static void main(String[] arg) {
        FileCompare a = new FileCompare();
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2, 1))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2, 1))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 3, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2, 1))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2, 4))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 1))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2, 1, 8))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(2, 1, 2, 1))));
        System.out.println(a.isDistanceZeroOrOne(new IntFileIterator(Arrays.asList(2, 1, 2, 1)),
                new IntFileIterator(Arrays.asList(1, 1, 2, 1))));

    }
}
