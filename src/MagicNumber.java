/**
 * Created by Christina on 2/29/16.
 */
public class MagicNumber {

    //fib(n)
    public int fib(int n) {
        if (n < 0) {return -1;}
        if (n <= 1) {return n;}
        return fib(n - 1) + fib(n - 2);
    }

    public int fib1(int n) {
        if (n < 0) return -1;
        int[] nums = new int[n];
        nums[0] = 0;
        nums[1] = 1;
        return fib1(n, nums);
    }

    public int fib1(int n, int[] nums) {
        if (nums[n] != 0 || n == 0) return nums[n];
        nums[n] = fib1(n - 1, nums) + fib1(n - 2, nums);
        return nums[n];
    }


    //f(n) = f(n-1) + 2*f(n-3)
    //O(n), O(n)
    public int magicNumber(int n) {
        if (n <= 0) {return 1;}
        int[] nums = new int[n + 1];
        return helper(n, nums);
    }

    private int helper(int n, int[] nums) {
        if (n <= 0) {return 1;}
        if (nums[n] != 0) {return nums[n];}
        nums[n] = helper(n - 1, nums) + 2 * helper(n - 3, nums);
        return nums[n];
    }

    //f(n, k) = f(n - 1) + 2 * f(n - k)
    //O(n), O(n)
    public int magicNumber(int n, int k) {
        if (n <= 0) {return 1;}
        int[] nums = new int[n + 1];
        return helper(n, k, nums);
    }

    private int helper(int n,int k, int[] nums) {
        if (n <= 0) {return 1;}
        if (nums[n] != 0) {return nums[n];}
        nums[n] = helper(n - 1, nums) + 2 * helper(n - k, nums);
        return nums[n];
    }

    public static void main(String[] agr) {
        MagicNumber a = new MagicNumber();
        System.out.println(a.magicNumber(5));
        System.out.println(a.magicNumber(5, 3));
    }
}
