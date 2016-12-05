package strings;

import java.util.*;

/**
 * Created by xuanwang on 11/16/16.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/+");//split with all consecutive "/", for cases like "/home//foo" --> "/home/foo"
        for (String s : paths) {
            if (s.equals("..")) {// /../ means going back to the last directory,so we pop out one directory if !stack.empty()
                if (!stack.empty()) {
                    stack.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {// /./ means curr directory, so we don't need to do anything
                stack.push(s);//if it's not "." or "", we just add the directory to stack
            }
        }
        String res = "";
        while (!stack.empty()) {//we build string from sub directory to root directory,so res it's added at the end of string
            res = "/" + stack.pop() + res;
        }
        if (res.length() == 0) {//if it's empty (eg. /../, or /home/../), we need to return "/"
            return "/";
        }
        return res;
    }

    public String simplifyPatho(String path) {
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
