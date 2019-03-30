package alg.shuc.code20190330;

import java.util.Stack;

/**
 * 前序遍历 中->左->右
 * @author shuc001
 * @Date 2019-03-30 17:05
 */
public class PreOrder {

    public static void main(String[] args) {
        Node root = new Node();


        preOrderRecursive(root);

        preOrder(root);
    }

    /**
     * 非递归
     * @param root
     */
    private static void preOrder(Node root) {
        if(root==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop);
            if(pop.getLeft()!=null){
                stack.push(pop.getLeft());
            }
            if(pop.getRight()!=null){
                stack.push(pop.getRight());
            }
        }
    }

    /**
     * 递归形式
     * @param root
     */
    private static void preOrderRecursive(Node root) {
        if(root==null){
            return;
        }
        System.out.println(root.getData());
        preOrderRecursive(root.getLeft());
        preOrderRecursive(root.getRight());
    }



}
