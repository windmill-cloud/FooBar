package binarytree;

import structures.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanwang on 1/31/17.
 */
public class EvenNumberSubtree {

    public static List<TreeNode> getEvenNumberSubtree(TreeNode root){
        List<TreeNode> ans = new ArrayList<>();

        helper(ans, root);
        return ans;
    }

    private static int helper(List<TreeNode> ans, TreeNode node){
        if (node == null){
            return 0;
        }

        int numNodes = helper(ans, node.left) + 1 + helper(ans, node.right);

        if((numNodes & 1) == 0) {
            ans.add(node);
        }
        return numNodes;
    }

    public static void main(String[] args){
        TreeNode root = SerializeandDeserializeBinaryTree.deserialize("1 2 3 4 5 n n 6 7 8 9 n n n n n n 10 n");
        List<TreeNode> ans = getEvenNumberSubtree(root);
        System.out.println();
    }
}
