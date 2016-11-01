package adhoc;

public class FindtheCelebrity {

    /**
     * iterate the array
     * to abandon the one
     * */
    //O(n), O(1)
    public static int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i)){
                candidate = i;
            }
        }

        for(int i = 0; i < n; i++){
            if(i != candidate && ( knows(candidate, i) || !knows(i, candidate)) ) return -1;
        }
        return candidate;
    }

    private static boolean knows(int a, int b) {
        return false;
    }
}
