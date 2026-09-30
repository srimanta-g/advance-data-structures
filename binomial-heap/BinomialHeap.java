public class BinomialHeap {

    private Node head;
    private int size;

    public BinomialHeap () {
        this.size = 0;
        this.head = null;
    }

    public int getSize () {
        return this.size;
    }

    public boolean isEmpty () {
        return this.size == 0;
    }

    public Node getRoot () {
        return this.head;
    }

    public void insert (int value) {
       if (this.size == 0) {
           Node newnode = new Node(value);
           this.head = newnode;

           this.size ++;

           return;
       }

       BinomialHeap newHeap = new BinomialHeap();
       newHeap.insert(value);

       this.head = merge(this, newHeap);

       this.size ++;
    }

    public void merge (BinomialHeap heap) {
        this.head = merge(this, heap);
        this.size += heap.getSize();
    }

    public Node merge (BinomialHeap b1, BinomialHeap b2) {
        Node sortedList = getSortedNodesByOrder(b1.getRoot(), b2.getRoot());

        if (sortedList == null) return null;
        
        Node prev = null;
        Node current = sortedList;
        Node next = sortedList.sibling;

        while (next != null) {
            if (current.degree != next.degree) {
                prev = current;
                current = next;
                next = next.sibling;
            }

            else if (next.sibling != null && next.sibling.degree == current.degree) {
                prev = current;
                current = next;
                next = next.sibling;
            }

            else {
                if (current.value <= next.value) {
                    current.sibling = next.sibling;
                    mergeBinomialHeapNode(current, next);
                } else {
                    if (prev == null) {
                        sortedList = next;
                    } else {
                        prev.sibling = next;
                    }

                    mergeBinomialHeapNode(next, current);
                    current = next;
                }

                next = current.sibling;
            }
        }

        return sortedList;
    }

    private Node getSortedNodesByOrder(Node n1, Node n2) {
        Node dummy = new Node(-1);

        Node ptr = dummy;

        while (n1 != null && n2 != null) {
            if (n1.degree < n2.degree) {
                ptr.sibling = n1;

                ptr = ptr.sibling;
                n1 = n1.sibling;
            } else {
                ptr.sibling = n2;
                ptr = ptr.sibling;
                n2 = n2.sibling;
            }
        }

        while (n1 != null) {
            ptr.sibling = n1;

            ptr = ptr.sibling;
            n1 = n1.sibling;
        }

        while (n2 != null) {
            ptr.sibling = n2;

            ptr = ptr.sibling;
            n2 = n2.sibling;
        }

        return dummy.sibling;
    }

    private Node mergeBinomialHeapNode (Node n1, Node n2) {
        Node root1 = n1;
        Node root2 = n2;

        if (root1.value < root2.value) {
            root2.parent = root1;
            // root1.sibling = root2.sibling;
            root2.sibling = root1.child;
            root1.child = root2;
            
            root1.degree ++;
            return root1;
        } else {
            root1.parent = root2;
            root1.sibling = root2.child;
            root2.child = root1;
            
            root2.degree ++;
            return root2;
        }
    }

    public int getMin () {
        int min = Integer.MAX_VALUE;
        Node ptr = head;

        while (ptr != null) {
            min = Math.min(min, ptr.value);
            ptr = ptr.sibling;
        }

        return min;
    }

    private int deleteMin() {
        // TODO
        return 1;
    }

    private void decreaseValueFromNode(Node target) {
        // TODO
    }
    
    private class Node {
        public int value;
        public Node parent;
        public Node child;
        public Node sibling;
        public int degree;

        public Node (int value) {
            this.value = value;
            this.degree = 0;

            this.parent = null;
            this.child = null;
            this.sibling = null;
        }
    }
}
