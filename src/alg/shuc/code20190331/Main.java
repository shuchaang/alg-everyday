package alg.shuc.code20190331;

/**
 * @author shuc001
 * @Date 2019-03-31 13:24
 * <p>
 * <p>
 * 1->2->3->8->10
 * 2->5->10->11
 * <p>
 * 循环比较l1 l2 ,合并到新的链表l3上去
 */
public class Main {

    public Node merge(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        Node p1 = l1;
        Node p2 = l2;
        Node curr = new Node();
        while (p1 != null && p2 != null) {
            if (p2.getData() <= p1.getData()) {
                Node node = new Node();
                node.setData(p2.getData());
                curr.setNext(node);
                p2 = p2.getNext();
            } else {
                Node node = new Node();
                node.setData(p1.getData());
                curr.setNext(node);
                p1 = p1.getNext();
            }
            curr = curr.getNext();
        }
        if(p1!=null){
            curr.setNext(p1);
        }
        if(p2!=null){
            curr.setNext(p2);
        }
        return curr;
    }
}
