public class RedBlackTree {

    Node root;
    Node NIL;

    public RedBlackTree() {
        NIL = new Node(0);
        
        NIL.nodeType = NodeType.BLACK;
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;

        root = NIL;
    }

    public boolean search(int value) {
        Node current = root;

        while (current != NIL) {
            if (current.value == value) return true;
            else if (value > current.value) current = current.right;
            else current = current.left;
        }

        return false;
    }
    
    public void insert(int value) {
        Node newnode = new Node(value);
        newnode.nodeType = NodeType.RED;

        newnode.left = NIL;
        newnode.right = NIL;

        Node prev = NIL;
        Node current = root;

        while (current != NIL) {
            prev = current;
            if (newnode.value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newnode.parent = prev;

        if (prev == NIL) {
            root = newnode;
        } else if (newnode.value < prev.value) {
            prev.left = newnode;
        } else {
            prev.right = newnode;
        }

        insertFixup(newnode);
    }

    private void insertFixup(Node node) {
        while (node.parent.nodeType == NodeType.RED) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;

                if (uncle.nodeType == NodeType.RED) {
                    node.parent.nodeType = NodeType.BLACK;
                    uncle.nodeType = NodeType.BLACK;
                    node.parent.parent.nodeType = NodeType.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }

                    node.parent.nodeType = NodeType.BLACK;
                    node.parent.parent.nodeType = NodeType.RED;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;

                if (uncle.nodeType == NodeType.RED) {
                    node.parent.nodeType = NodeType.BLACK;
                    uncle.nodeType = NodeType.BLACK;
                    node.parent.parent.nodeType = NodeType.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }

                    node.parent.nodeType = NodeType.BLACK;
                    node.parent.parent.nodeType = NodeType.RED;
                    leftRotate(node.parent.parent);
                }
            }
        }
        
        root.nodeType = NodeType.BLACK;
    }

    private boolean delete(int value) {
        return false;
    }


    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isTail) {
        if (node == NIL) {
            return;
        }

        System.out.println(
            prefix +
            (isTail ? "└── " : "├── ") +
            node.value +
            (node.nodeType == NodeType.RED ? "(R)" : "(B)") +
            " [P: " +
            (node.parent == NIL ? "NIL" : node.parent.value) +
            "]"
        );

        if (node.left != NIL || node.right != NIL) {
            if (node.left != NIL) {
                printTree(node.left, prefix + (isTail ? "    " : "│   "), node.right == NIL);
            }

            if (node.right != NIL) {
                printTree(node.right, prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }
    

    private void leftRotate(Node root) {

        Node rightChild = root.right;

        root.right = rightChild.left;

        if (rightChild.left != NIL) {
            rightChild.left.parent = root;
        }

        rightChild.parent = root.parent;

        if (root.parent == NIL) {
            this.root = rightChild;
        } else if (root == root.parent.left) {
            root.parent.left = rightChild;
        } else {
            root.parent.right = rightChild;
        }

        rightChild.left = root; 
        root.parent = rightChild;
    }

    private void rightRotate(Node root) {
        Node leftChild = root.left;

        root.left = leftChild.right;

        if (leftChild.right != NIL) {
            leftChild.right.parent = root;
        }

        leftChild.parent = root.parent;

        if (root.parent == NIL) {
            this.root = leftChild;
        } else if (root == root.parent.left) {
            root.parent.left = leftChild;
        } else {
            root.parent.right = leftChild;
        }

        leftChild.right = root;
        root.parent = leftChild;
    }
}
