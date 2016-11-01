//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by Christina on 2/28/16.
// */
//public class NestedList {
//    List<Integer> val;
//    List<NestedList> next;
//
//    NestedList() {
//        val = new ArrayList<>();
//        next = new ArrayList<>();
//    }
//
//    public static List<Integer> flattenNestedList(NestedList list) {
//        if (list == null) {
//            return new ArrayList<>();
//        }
//        if (list.next.size() != 0) {
////            return Arrays.asList(list.val);
//        }
//
//        List<Integer> ret = new ArrayList<>();
////        ret.add(list.val);
//        for (NestedList next : list.next) {
//            ret.addAll(flattenNestedList(next));
//        }
//
//        return ret;
//    }
//
//    public static void main(String[] arg) {
//        NestedList list = new NestedList(1);
//
//    }
//}
