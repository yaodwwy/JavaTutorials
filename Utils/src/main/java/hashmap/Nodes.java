package hashmap;

public class Nodes {

    private Node head;

    public static class Node{

        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Nodes nodes = new Nodes();
        nodes.head = new Node(0);
        nodes.head.next = new Node(1);
        nodes.head.next.next = new Node(2);
        nodes.head.next.next.next = new Node(3);
        nodes.head.next.next.next.next = new Node(4);
        System.out.println(nodes.head.next.next.data);
    }
}
