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
    // multiple times
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

    // read4k
    // read one time
    // 1 byte = 8 bits
    /*
    public int read(char[] buf, int n) {
        char[] content = new char[4096];
        int count = 0;
        boolean hasNext = true;
        while (hasNext && count < n) {
            int readLength = read4k(content);
            if (readLength < 4096) {
                hasNext = false;
            }
            for (int i = 0; i < readLength && count < n; i++) {
                buf[count++] = content[i];
            }
        }
        return count;
    }

    // read many times
    // record what previously read
    private char[] content = new char[4096];
    private int haveRead = 0; // the bit we read by calling read4k
    private int index = 0;
    public int read(char[] buf, int n) {
        int count = 0;
        boolean hasNext = true;
        while (hasNext && count < n) {
            if (index == 0) {
                haveRead = read4k(content);
            }
            if (haveRead < 4096) {
                hasNext = false;
            }
            while (count < n && index < haveRead) {
                buf[count] = content[index];
                count++;
                index++;
            }
            if (index == haveRead) {
                index = 0;
            }
        }
        return count;
    }
    */


    public int read4(char[] buf) {
        return 1;
    }
}
