package alg.shuc.code20190330;

import java.util.Stack;

/**
 * @author shuc001
 * @Date 2019-03-30 17:29
 */
public class PostOrder {
    public static void main(String[] args) {
        Node root = new Node();

        postOrderRecursive(root);
        postOrder(root);
    }

    /**
     * 非递归
     * @param root
     */
    private static void postOrder(Node root) {
        if(root==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node mark = null;

        stack.push(root);

        while (!stack.isEmpty()){
            Node peek = stack.peek();
            //先走到最左边
            while (peek.getLeft()!=null){
                stack.push(peek.getLeft());
            }
            Node pop = stack.pop();
            if(pop.getRight()==null||pop.getRight()==mark){
                System.out.println(pop.getData());
                mark=pop;
            }else{
                stack.push(pop);
                while (pop.getRight()!=null){
                    stack.push(pop.getRight());
                }
            }
        }
    }

    /**
     * 递归
     * @param root
     */
    private static void postOrderRecursive(Node root) {
        if(root==null){
            return;
        }
        postOrderRecursive(root.getLeft());
        postOrderRecursive(root.getRight());
        System.out.println(root.getData());
    }
}
