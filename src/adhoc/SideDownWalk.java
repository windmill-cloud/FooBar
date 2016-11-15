package adhoc;

/**
 * Created by xuanwang on 11/13/16.
 */
public class SideDownWalk {

    public static boolean sideDownBack(int m, int n){
        if( m <1 || n <1){
            return false;
        }

        while(m >= 1 && n >= 1){
            if(m == 1 && n == 1){
                return true;
            }
            if(m > n){
                m -= n;
            }else if(m <n){
                n -=m;
            }else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int m = 3, n = 2;
        boolean res = sideDownBack(m, n);
        System.out.println();
    }
}
