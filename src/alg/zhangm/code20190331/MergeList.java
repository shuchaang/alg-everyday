package alg.zhangm.code20190331;

/**
 * @author zhangm003
 * @date 2019/3/31 10:26 PM
 * @description 将两个有序列表合并为一个返回
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 类似于归并排序，最后的合并过程
 */
public class MergeList {
    public static void main(String[] args){
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();

        mergeTwoLists(l1, l2);
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode();
        ListNode node = cur;
        while(l1 != null && l2 != null){
            if(l1.val<=l2.val){
                node.next = l1;
                node = node.next;
                l1= l1.next;
            }else{
                node.next = l2;
                node = node.next;
                l2=l2.next;

            }
        }

        if(l1!=null){
            node.next = l1;
        }else{
            node.next = l2;
        }

        return cur.next;
    }
}
