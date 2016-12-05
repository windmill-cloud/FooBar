package adhoc;

public class FindtheCelebrity {

    /**
     * iterate the array
     * to abandon the one
     * */
    //O(n), O(1)
    // each time we call knows(a, b), we will know at least one fact, so for each call, a candidate will be left
    //1.if a knows b, a is not possible to be the celebrity
    //2.if a don't know b, b is not possible to be the celebrity

    public static int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i)){ //if candidate knows i, i will become a candidate; else candidate doesn't change
                candidate = i;
            }
        }

        for(int i = 0; i < n; i++){
            if(i != candidate && ( knows(candidate, i) || !knows(i, candidate)) ) {
                return -1;//if i is not a candidate, and the candidate knows i, or i doesn't know p, then celebrity doesn't exist
            }
        }
        return candidate;
    }

    private static boolean knows(int a, int b) {
        return false;
    }
}
