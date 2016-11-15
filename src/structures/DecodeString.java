package structures;

import java.util.LinkedList;

/**
 * Created by xuanwang on 11/14/16.
 */
public class DecodeString {
    public String decodeString(String s) {
        String res = "";

        LinkedList<String> resS = new LinkedList<>();
        LinkedList<Integer> cntS = new LinkedList<>();
        int idx = 0;
        while(idx < s.length()){
            char c = s.charAt(idx);
            if(Character.isDigit(c)){
                int cnt = 0;
                while(Character.isDigit(s.charAt(idx))){
                    cnt = cnt*10 + s.charAt(idx) - '0';
                    idx++;
                }
                cntS.push(cnt);
            }else if(c == '['){
                resS.push(res);
                res = "";
                idx++;
            }else if(c == ']'){
                StringBuilder sb = new StringBuilder(resS.pop());
                int cnt = cntS.pop();
                for(int i = 0; i< cnt; i++){
                    sb.append(res);
                }
                res = sb.toString();
                idx++;
            }else{
                res += c;
                idx++;
            }
        }
        return res;
    }
}
