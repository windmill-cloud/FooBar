package idontknow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Christina on 3/3/16.
 */
public class GroupContact {
    /**
     * put the String into the map and give the String an id, the same person will have same id
     * when put the string into the map, check if the
     *
     * */
    public static void main(String[] args) {
        HashMap<String, Integer> h = new HashMap();

        String[][] A = {{"John", "john@gmail.com", "john@fb.com"},
                {"Dan", "dan@gmail.com", "+1234567"},
                {"john123", "+5412312", "john123@skype.com"},
                {"john1985", "+5412312", "john@fb.com"}};

        int code = 0;
        for (int i = 0; i < A.length; i++) {
            int c = -1;
            Set<Integer> groupings = new HashSet<>();
            for (int j = 0; j < A[i].length; j++) {
                String s = A[i][j];
                if (h.containsKey(s)) {
                    int value = h.get(s);
                    groupings.add(value);
                    c = Math.min(c, value);
                }
            }
            if (c == -1) {
                c = code++;
            } else {
                groupings.remove(c);
                for (Integer k : groupings) {
                    for (int m = 0; m < A[0].length; m++) {
                        h.put(A[k][m], c);
                    }
                }
            }
            for (int j = 0; j < A[i].length; j++) {
                String s = A[i][j];
                h.put(s, c);
            }
        }
        for (int i = 0; i < A.length; i++) {
            System.out.println(h.get(A[i][0]));
        }
    }
}
