package alg.zhangm.code20190330;

import java.util.Stack;

/**
 * @author zhangm003
 * @date 2019/3/30 10:08 PM
 * @description
 */
public class PreOrder {
    public static void main(String[] args){
        TreeNode treeNode = new TreeNode();
        recursive(treeNode);
        noRecursive(treeNode);
    }

    private static void noRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root !=null || stack.size()> 0){
            if(root != null){
                System.out.println(root.val);
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                root = root.right;
            }
        }
    }

    private static void recursive(TreeNode root) {
        if(root != null){
            System.out.println(root.val);
            recursive(root.left);
            recursive(root.right);
        }
    }
}
