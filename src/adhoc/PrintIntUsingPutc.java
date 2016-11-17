package adhoc;

/**
 * Created by xuanwang on 11/16/16.
 */
public class PrintIntUsingPutc {

    public static void printInt(int n){
        printIntHelper((long) n);
    }

    public static void printIntHelper(long n){
        if(n == 0){
            putc('0');
        }

        if(n < 0){
            putc('-');
            n = -n;
        }
        if(n/10 > 0){
            printIntHelper(n/10);
        }
        putc((char)(n % 10 + '0'));
    }

    public static void putc(char c){
        System.out.print(c);
    }

    public static void main(String[] args){
        printInt(-1234567);
    }
}
