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

    public int read4(char[] buf) {
        return 1;
    }
}
