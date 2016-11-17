package strings;

import java.util.*;

/**
 * Created by xuanwang on 11/16/16.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");

        LinkedList<String> s = new LinkedList<>();

        for(String str: arr){
            if(str.equals("..")){
                if(!s.isEmpty()){
                    s.pop();
                }
            } else if(str.equals(".") || str.equals("")){
                continue;
            } else{
                s.push(str);
            }
        }


        String ans = "";
        if(!s.isEmpty()){
            ans = s.pop();
        }
        while(!s.isEmpty()){
            ans = s.pop() + "/" + ans;
        }

        return "/" + ans;
    }
}
