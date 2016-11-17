package graph;

import java.util.*;

/**
 * Created by xuanwang on 11/3/16.
 */
public class ContactGroups {
    // Use a hashmap to map the email to person called emailToPerson.
// Use another hashmap to map the person to root, called personToRoot
// the root is a person in this group, everyone in this group will map to this root
// root will map to root itself
// So we traverse through the input,
// When we meet a person, we check the hashmap emailToPerson
// if it does not contains this email, then we map this email to this person
// if it does, which means this email maps to other people. lets call him A
// So we go to check personToRoot, if A maps to another root,
// which means the person we meet should also maps to this root
// then we check aother mails of this person, if other mail maps to another person B
// this person should also maps to B's root, which means A's root should also be B's root,
// So we change A's root's root to B's root(Other people in A's group still point to A's root, but A's root point to B's root)
// For example, 1 - {x, y, z} 2 - {a, b}, 3 - {x} 4 - {x, a}
// first we map x -> 1, y -> 1, z -> 1 and 1 - > 1
// we meet 2, then map a -> 2, b -> 2, and 2 -> 2
// Then meet 3, find out that x -> 1, so 3 -> 1
// Then meet 4, find out that x -> 1, so 4 -> 1
// and find out that a -> 2, so  4's root 1's root should be 2,
// that is 1 -> 2
// in the end we have 1 -> 2, 2 -> 2, 3 -> 1, 4 -> 2
// So the gourp is [1, 2, 3, 4]

/* 'Time complexity: O(nklgn) - n person and k emails in average,


    findRoot, act like find node in a tree - O(lgn)
    Space complexity: O(nk)'
*/
    public List<List<Integer>> contactGroup(HashMap<Integer, String[]> contact) {
        HashMap<String, Integer> emailToPerson = new HashMap<>();
        HashMap<Integer, Integer> personToRoot = new HashMap<>();
        for (int person : contact.keySet()) {
            personToRoot.put(person, person);
            int curRoot = person;
            for (String email : contact.get(person)) {
                if (!emailToPerson.containsKey(email)) {
                    emailToPerson.put(email, person);
                    continue;
                }
                int newRoot = emailToPerson.get(email);
                newRoot = findRoot(personToRoot, newRoot);
                if (newRoot != curRoot) {
                    personToRoot.put(curRoot, newRoot);
                    curRoot = newRoot;
                }
            }
        }
        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int person : personToRoot.keySet()) {
            int root = findRoot(personToRoot, person);
            if (!groups.containsKey(root)) {
                groups.put(root, new ArrayList<Integer>());
            }
            groups.get(root).add(person);
        }
        for (int group : groups.keySet()) {
            result.add(groups.get(group));
        }
        return result;
    }

    private int findRoot(HashMap<Integer, Integer> personToRoot, int root) {
        while (personToRoot.get(root) != root) {
            root = personToRoot.get(root);
        }
        return root;
    }

    static class UnionFind{
        String[] emailArray;
        //index in the array
        int[] groups;
        public UnionFind(String[] emailArray){
            this.emailArray = emailArray;
            this.groups = new int[emailArray.length];
            for(int i = 0; i < groups.length; i ++){
                groups[i] = i;
            }
        }

        public int find(int i){
            while(groups[i] != i){
                i = groups[i];
            }
            return i;
        }

        public void union(int to, int from){
            int toParent = find(to);
            int fromParent = find(from);
            if(toParent != fromParent){
                groups[fromParent] = toParent;
            }
        }

        public void printUnion(){
            HashMap<Integer,List<Integer>> map = new HashMap<Integer, List<Integer>>();
            for(int i = 0; i < groups.length; i ++){
                int parent = find(i);
                map.putIfAbsent(parent, new ArrayList<Integer>());
                map.get(parent).add(i);
            }
            for(Integer key: map.keySet()){
                for(Integer entry: map.get(key)){
                    System.out.print(emailArray[entry] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void contactDedup(HashMap<Integer, List<String>> input){
        Set<String> emailSet = new HashSet<String>();
        for(Integer key: input.keySet()){
            for(String email: input.get(key)){
                emailSet.add(email);
            }
        }
        String[] emailArray = new String[emailSet.size()];
        int count = 0;
        HashMap<String, Integer> indexMapping = new HashMap<String, Integer>();
        for(String email: emailSet){
            indexMapping.put(email, count);
            emailArray[count ++] = email;
        }
        UnionFind uf = new UnionFind(emailArray);
        for(Integer key: input.keySet()){
            List<String> l = input.get(key);
            if(l.size() == 0){
                continue;
            }
            int parent = indexMapping.get(l.get(0));
            for(int i = 1; i < l.size(); i ++){
                uf.union(parent, indexMapping.get(l.get(i)));
            }
        }
        uf.printUnion();
    }

    public static void main(String[] args){
        HashMap<Integer, List<String>> input = new HashMap<Integer, List<String>>();
        input.put(1, new ArrayList<String>(Arrays.asList("a","b","c")));
        input.put(2, new ArrayList<String>(Arrays.asList("a")));
        input.put(3, new ArrayList<String>(Arrays.asList("x")));
        input.put(4, new ArrayList<String>(Arrays.asList("y","z")));
        input.put(5, new ArrayList<String>(Arrays.asList("y","z")));
        contactDedup(input);
    }
}







