package adhoc;

import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */
public class FriendsRecommendation {

    /**
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=174441&page=1#pid2261532
     * 有个函数getFriendList(int id)可以调用，返回的是List<Integer>，是id这个用户的所有好友的id.
     问如何给用户推荐10个好友？
     我又用了hash系列产品，返回并记录这个用户所有好友的好友，记录时排除已经是这个用户的好友的那些结果，返回top10. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
     这题我错了一个地方，忘记排除用户自己...！面试小哥还循循善诱，到他自己在那儿乐个不停，我都急死了没想出来这个bug  T.T
     另外就是小哥问我hashmap咋对value排序呢，我说做个新的comparator用来比value的值？或者用heap？他问那big O是多少？
     然后他要求用O(n)的time complexity做排序，刚开始我没想出来，他换话题说我上面写那个bug去了。说完bug又来追究这个问题
     我突然想到priority queue可以，小哥特别满意的放过我了...
     * */
    /**
     *
     * use map to count the number of the friends whether existed
     * if this friend is this original one's friend and also is not the original one
     *
     * */
    public List<Integer> friendsRecommendation(Person person, int n) {
        //key: id, value: count
        Map<Integer, Integer> mapID = new HashMap<>();
//        for (int friend : person.friends) {
//            for ( recommend : friend.friends) {
//                int id = recommend.id;
//                if (!person.friends.contains(id) && id != person.id) {
//                    mapID.put(id, mapID.containsKey(id) ? mapID.get(id) + 1 : 1);
//                }
//            }
//        }

        //<key, value>
        Queue<int[]> heap = new PriorityQueue<>(n, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o2[1];
            }
        });

        for (int key : mapID.keySet()) {
            heap.add(new int[]{key, mapID.get(key)});
        }

        List<Integer> ret = new LinkedList<>();
        for (int[] nums : heap) {
            ret.add(nums[1]);
        }

        return ret;








//        //O(n)  quick select
//        List<Integer> ret = new ArrayList<>();
//        int[] count = new int[mapID.size()];
//        int i = 0;
//
//        for (int num : mapID.values()) {
//            count[i++] = num;
//        }
//
//        int start = 0, end = count.length - 1;
//        Random random = new Random();
//        while (ret.size() < n && ret.size() < count.length) {
//            int pos = start + random.nextInt(end - start);
//            int pivot = count[pos];
//            count[pos] = count[pivot - 1];
//            int left = start, right = start;
//
//            for (; right < end - 1; right++) {
//                if (count[right] <= pivot) {
//                    int temp = count[right];
//                    count[right] = count[left];
//                    count[left++] = temp;
//                }
//            }
//
//            if (n <= left - right + 1) {
//                end = left;
//            } else {
//                int addSize = 0;
//                for (i = start; i < pos; i++) {
//                    if (i == start && count[i] != count[i - 1]) {
//                        ret.addAll(mapCount.get(count[pos]));
//                        addSize += mapCount.get(count[pos]).size();
//                    }
//                }
//                n = n - addSize;
//                start += n;
//                end--;
//            }
//        }
//        return ret.size() > n ? ret.subList(0, n) : ret;
    }





    public static class Person {
        int id;
        List<Integer> friends = new ArrayList<>();
    }
}
