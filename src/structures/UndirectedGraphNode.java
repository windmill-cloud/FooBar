package structures;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 7990d676fe025eee3aa3754665b892641d52667a

/**
 * Created by xuanwang on 10/29/16.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;
<<<<<<< HEAD

    public UndirectedGraphNode(int x) {
        this.label = x;
        this.neighbors = new ArrayList();
    }
=======
    public UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
>>>>>>> 7990d676fe025eee3aa3754665b892641d52667a
}
