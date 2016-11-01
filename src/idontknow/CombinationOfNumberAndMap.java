package idontknow;

import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */
public class CombinationOfNumberAndMap {
    /**
     * given a string of number and a map, return all possible combination.
     * for example: given 12 AND a map <<1:'A','B','C'>,<2:'D','E','F'>> return AD, AE, AF, BD, BE, BF,...
     * Solve it recursively
     */
    //与phone number那题一样

    //O(length of ret), O(length of ret)
    public List<String> getCombinations(int num, Map<Integer, List<Character>> map) {
        List<String> ret = new ArrayList<>();

        if (num == 0) {
            if (map.containsKey(0)) {
                for (char c : map.get(0)) {
                    ret.add(String.valueOf(c));
                }
            }
            return ret;
        }

        ret.add("");
        while (num != 0) {
            int curr = num % 10;
            num /= 10;
            List<String> last = ret;
            ret = new ArrayList<>();
            for (String s : last) {
                if (!map.containsKey(curr)) {continue;}
                for (char c : map.get(curr)) {
                    ret.add(c + s);
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        CombinationOfNumberAndMap a = new CombinationOfNumberAndMap();
        Map<Integer, List<Character>> map = new HashMap<>();
        map.put(1, Arrays.asList('A', 'B', 'C'));
        map.put(2, Arrays.asList('D', 'E', 'F'));
        map.put(0, Arrays.asList('G', 'H'));
        System.out.println(a.getCombinations(12, map));
        System.out.println(a.getCombinations(0, map));
    }

}
