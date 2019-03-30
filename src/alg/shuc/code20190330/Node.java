package alg.shuc.code20190330;

/**
 * @author shuc001
 * @Date 2019-03-30 17:04
 */
public class Node {
    private Object data;
    private Node left;
    private Node right;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
