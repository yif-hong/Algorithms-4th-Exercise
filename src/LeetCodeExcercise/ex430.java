package LeetCodeExcercise;

/**
 * 430. 扁平化多级双向链表
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 *
 * 示例:
 *
 * 输入:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * 输出:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */
public class ex430 {
    //递归1，一层一层递归
    public Node flatten(Node head) {
        if (head == null) return null;
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                Node next = curr.next;
                Node nextLayer = flatten(curr.child);
                curr.next = nextLayer;
                nextLayer.prev = curr;
                curr.child = null;
                while (nextLayer.next != null) nextLayer = nextLayer.next;
                nextLayer.next = next;
                if (next != null) next.prev = nextLayer;
            }
            curr = curr.next;
        }
        return head;
    }

    //递归2，一个一个递归
    public Node flatten2(Node head) {
        if (head == null) return null;
        if (head.child == null) head.next = flatten2(head.next);
        else {
            Node next = flatten2(head.next);
            Node nextLayer = flatten2(head.child);
            nextLayer.prev = head;
            head.next = nextLayer;
            head.child = null;
            while (nextLayer.next != null) nextLayer = nextLayer.next;
            nextLayer.next = next;
            if (next != null) next.prev = nextLayer;
        }
        return head;
    }

    //迭代解决
    public Node flatten3(Node head) {
        for (Node curr = head; curr != null; curr = curr.next) {
            Node next = curr.next;
            if (curr.child != null) {
                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null;
                Node temp = curr.next;
                while (temp.next != null) temp = temp.next;
                temp.next = next;
                if (next != null) next.prev = temp;
            }
        }
        return head;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

}
