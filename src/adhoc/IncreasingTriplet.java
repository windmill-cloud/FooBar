package adhoc;

public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {return false;}
        int min = Integer.MAX_VALUE, max = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max) { return true;}
            if (n > min) {
                max = n;
            } else {
                min = n;
            }
        }
        return false;
    }
}
