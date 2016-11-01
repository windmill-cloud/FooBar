import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christina on 2/21/16.
 */
public class GreyCode {
    //O(2^n)
    public List<Integer> grayCode(int n) {
        List<Integer> rst = new ArrayList<>();
        rst.add(0);
        if (n < 1) return rst;
        rst.add(1);
        int addItem = 2;
        for (int i = 2; i <= n; i++) {
            int size = rst.size();
            for (int j = size - 1; j >= 0; j--) {
                rst.add(rst.get(j) + addItem);
            }
            addItem *= 2;
        }
        return rst;
    }

    public List<Integer> grayCode0(int n) {
        List<Integer> ans = new ArrayList<>();
        if(n == 0){
            ans.add(0);
            return ans;
        }

        List<Integer> tmp = grayCode(n-1);
        ans.addAll(tmp);
        int msb = 1<<(n-1);
        for(int i = tmp.size()-1; i >=0;i--){
            ans.add(msb | tmp.get(i));
        }
        return ans;
    }

    //bit manipulation
    public List<Integer> grayCode1(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add(i ^ i >> 1);
        }
        return ret;
    }

    public static void main(String[] arg) {
        GreyCode a = new GreyCode();
        System.out.println(a.grayCode1(4));
    }
}
