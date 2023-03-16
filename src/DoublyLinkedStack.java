public class DoublyLinkedStack<T> implements StackInterface<T>{
    private Node<T> head;
    private Node<T> tail;
    private int numberOfElements;

    public DoublyLinkedStack() {
        head = null;
        tail = null;
    }

    public void push(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.setNextNode(head);
            head.setPrevNode(newNode);
            head = newNode;
        }
        numberOfElements++;
    }

    public T pop() {
        if(head == null) {
            System.out.println("in the pop method, head == null part.");
            return null;
        }
        Node<T> temp = head;

        if(numberOfElements == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.getNextNode();
            head.setPrevNode(null);
        }
        numberOfElements--;
        return temp.getNodeData();
    }

    public T peek() {
        if(head == null) {
            return null;
        }
        else {
            return head.getNodeData();
        }
    }

    public boolean isEmpty() {
        if (numberOfElements == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void clear() {
        head = null;
        tail = null;

        numberOfElements = 0;
    }
    public int getNumberOfElements() {
        return numberOfElements;
    }


    // ------------ Private Node Class --------------------
    private class Node<T>
    {
        private T nodeData;
        private Node<T> prevNode = null;
        private Node<T> nextNode = null;

        public Node()
        {

        }
        public Node(T nodeData)
        {
            this.nodeData = nodeData;
        }

        public Node(T nodeData, Node<T> prevNode, Node<T> nextNode) {
            this.nodeData = nodeData;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }

        public T getNodeData() {
            return nodeData;
        }

        public void setNodeData(T nodeData) {
            this.nodeData = nodeData;
        }

        public Node<T> getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node<T> prevNode) {
            this.prevNode = prevNode;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }
}
