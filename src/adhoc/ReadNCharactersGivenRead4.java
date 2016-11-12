package adhoc;

/**
 * Created by Christina on 2/24/16.
 */
public class ReadNCharactersGivenRead4 {
    /**
     * repeatedly call the function until reaches n or the buffer return does not have full items
     *
     *
     * */
    public int read(char[] buf, int n) {
        int count = 0;
        boolean isEnd = false;
        char[] temp = new char[4];
        while (!isEnd && count < n) {
            int num = read4(temp);
            if (num < 4) {isEnd = true;}
            for (int i = 0; i < num && count < n; buf[count++] = temp[i++]) {}
        }
        return count;
    }

    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int readII(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }

    public int read4(char[] buf) {
        return 1;
    }
}
