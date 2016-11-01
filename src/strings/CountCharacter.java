package strings;

public class CountCharacter {
    /**
     * iterate the String
     * if this character is the same is the former one, count ++
     * else append the number and the character into the Stringbuilder
     * then update the count = 0
     * */
    //O(n)
    public String countCharacter(String s) {
        if (s == null || s.length() == 0) {return s;}
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < c.length; i++) {
            if (i == c.length - 1 || c[i] != c[i + 1]) {
                sb.append(count + 1);
                sb.append(c[i]);
                count = 0;
            } else if (c[i] == c[i + 1]) {
                count++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] arg) {
        CountCharacter a = new CountCharacter();
        System.out.println(a.countCharacter("AABBBCCCD"));
        System.out.println(a.countCharacter(""));
    }
}
