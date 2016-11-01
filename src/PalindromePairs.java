import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */
public class PalindromePairs {

    /**
     *
     * cigar
     * tragic
     * none
     * xenon
     *
     *
     *(cigar, tragic) -> (none, xenon)
     * */

    //O(n^2)
    public List<List<String>> getPairs(Set<String> set) {
        List<List<String>> ret = new ArrayList<>();
        List<String> list = new ArrayList<>(set);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(list.get(i) + list.get(j));
                if (sb.toString().equals(sb.reverse().toString())) {
                    ret.add(Arrays.asList(list.get(i), list.get(j)));
                }

                sb = new StringBuilder();
                sb.append(list.get(j) + list.get(i));
                if (sb.toString().equals(sb.reverse().toString())) {
                    ret.add(Arrays.asList(list.get(j), list.get(i)));
                }
            }
        }
        return ret;
    }

    public static void main(String agrs[]) {
        PalindromePairs a = new PalindromePairs();
        System.out.println(a.getPairs(new HashSet<>(Arrays.asList("cigar", "tragic", "none", "xenon"))));
    }


}
