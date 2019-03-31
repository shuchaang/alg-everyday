package alg.zhangm.code20190330;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhangm003
 * @date 2019/3/30 10:17 PM
 * @description 后续遍历
 */
public class PostOrder {
    public static void main(String[] args){
        TreeNode root = new TreeNode();
        recursive(root);
        noRecursive(root);
    }

    private static void noRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while(root !=null || stack.size()> 0){
            if(root != null){
                stack.push(root);
                list.add(0,root.val);
                root = root.right;
            }else{
                root = stack.pop();
                root = root.left;
            }
        }
    }

    private static void recursive(TreeNode root) {
        if(root !=null){
            recursive(root.left);
            recursive(root.right);
            System.out.println(root.val);
        }
    }
}
