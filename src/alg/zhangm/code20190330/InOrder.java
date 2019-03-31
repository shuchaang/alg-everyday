package alg.zhangm.code20190330;

import java.util.Stack;

/**
 * @author zhangm003
 * @date 2019/3/30 9:13 PM
 * @description 中序遍历
 */
public class InOrder {
    public static void main(String[] args){
        TreeNode root = new TreeNode();
        recursive(root);
        noRecursive(root);
    }

    private static void noRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root !=null || stack.size() >0){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }

        }
    }

    private static void recursive(TreeNode root) {
        if(root != null){
            recursive(root.left);
            System.out.println(root.left);
            recursive(root.right);
        }
    }

}
