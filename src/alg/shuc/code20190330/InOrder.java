package alg.shuc.code20190330;

import java.util.Stack;

/**左->中->右
 * @author shuc001
 * @Date 2019-03-30 17:23
 */
public class InOrder {
    public static void main(String[] args) {
        Node root =new Node();

        inOrderRecursive(root);
        inOrder(root);
    }

    /**
     * 非递归
     * @param root
     */
    private static void inOrder(Node root) {
        if(root==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node peek = stack.peek();
            //走到最左边
            while (peek.getLeft()!=null){
                stack.push(peek.getLeft());
            }
            Node pop = stack.pop();
            System.out.println(pop.getData());
            if(pop.getRight()!=null){
                stack.push(pop.getRight());
            }


        }
    }

    /**
     * 递归
     * @param root
     */
    private static void inOrderRecursive(Node root) {
        if(root==null){
            return;
        }
        inOrderRecursive(root.getLeft());
        System.out.println(root.getData());
        inOrderRecursive(root.getRight());
    }


}
