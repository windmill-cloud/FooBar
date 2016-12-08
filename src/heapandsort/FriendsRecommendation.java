package heapandsort;

import java.util.*;

/**
 * Created by xuanwang on 12/4/16.
 */

class Person {
    int id;
    HashSet<Person> friends = new HashSet<>();
    HashSet<Person> getFriends(){
        return this.friends;
    }

    static HashSet<Person> getFriends(Person p){
        return p.friends;
    }

}
public class FriendsRecommendation {
    //TODO: BFS search
    //return friends of friends that are not this person's friends

    //Friend Recommendation:bucket sort,O(m) time,O(n) space,m is num of person's friends' friends,n is num of valid friends
    public class Solution {

        private List<Person> friendsRecommend(Person person, int k) {
            List<Person> res = new ArrayList<>();
            if (person == null) {
                return res;
            }
            HashMap<Person, Integer> map = new HashMap<>();
            int maxFreq = 0;
            for (Person friend : person.friends) {
                for (Person recommend : friend.friends) {
                    if (person.friends.contains(recommend)) {
                        continue;//if person already has this friend,or this is person himself/herself,continue
                    }
                    map.put(recommend, map.getOrDefault(recommend, 0) + 1);
                    int freq = map.get(recommend);
                    if(freq > maxFreq){
                        maxFreq = freq;
                    }
                }
            }
            List<List<Person>> buckets = new ArrayList<>();
            for (int i = 0; i <= maxFreq; i++) {
                buckets.add(new ArrayList<>());//we use frequency as index, so <= length
            }
            for (Person key : map.keySet()) {//unlike heap solution, we only need keySet() here
                buckets.get(map.get(key)).add(key);//get the id(key), add the id to its frequency bucket
            }
            for (int i = buckets.size() - 1; i >= 0; i--) {
                for (int j = 0; j < buckets.get(i).size(); j++) {//start adding the vals at the last bucket's last index
                    res.add(buckets.get(i).get(j));
                    if (res.size() == k) {
                        return res;//this two loops are O(k) time, when res has k nums, return it
                    }
                }
            }
            return res;
        }
    }

    //Friend Recommendation: Quick Select, average O(m + n) time, O(1) space, worst case O(m + n^2) time
//m is num of person's friends' friends,n is num of valid friends
    public class SolutionQuickSelect {

        private List<Person> friendsRecommend(Person person, int k) {
            List<Person> res = new ArrayList<>();
            if (person == null) {
                return res;
            }
            HashMap<Person, Integer> map = new HashMap<>();
            for (Person friend : person.friends) {
                for (Person recommend : friend.friends) {
                    if (person.friends.contains(recommend)) {
                        continue;//if person already has this friend,or this is person himself/herself,continue
                    }
                    map.put(recommend, map.getOrDefault(recommend, 0) + 1);
                }
            }

            ArrayList<Map.Entry<Person, Integer>> list = new ArrayList<>(map.entrySet());
            int left = 0;
            int right = list.size() - 1;
            int index = -1;
            while (true) {
                int pos = partition(list, left, right);
                if (pos + 1 == k) {
                    index = pos;
                    break;
                } else if (pos + 1 > k) {
                    right = pos - 1;
                } else {
                    left = pos + 1;
                }
            }
            if (index == -1) {
                return res;
            }
            for (Map.Entry<Person, Integer> entry: list) {
                Person p = entry.getKey();
                res.add(p);
            }
            return res;
        }

        private int partition(ArrayList<Map.Entry<Person, Integer>> list, int left, int right) {
            Random rand = new Random();
            int index = rand.nextInt(right - left + 1) + left;//remember to add + left !!!
            Map.Entry<Person, Integer> pivot = list.get(index);
            int pVal = pivot.getValue();
            swap(list, left, index);//index, not pivot !!
            int l = left + 1;
            int r = right;
            while (l <= r) {
                int lVal = list.get(l).getValue();
                int rVal = list.get(r).getValue();
                if (lVal < pVal && rVal > pVal) {
                    swap(list, l, r);
                }
                if (lVal >= pVal) {
                    l++;
                }
                if (rVal <= pVal) {
                    r--;
                }
            }
            swap(list, left, r);
            return r;
        }

        private void swap(ArrayList<Map.Entry<Person, Integer>> list, int left, int right) {
            Map.Entry<Person, Integer> temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
        }
    }
}
