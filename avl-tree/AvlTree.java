public class AvlTree {
    
    Node root;

    public AvlTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int bf = getBalanceFactor(root);

        // LL case
        if (bf > 1 && value < root.left.value) {
            return rightRotation(root);
        }
        
        // RR case
        if (bf < -1 && value > root.right.value) {
            return leftRotation(root);
        }
        
        // LR case
        if (bf > 1 && value > root.left.value) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }

        // RL case
        if (bf < -1 && value < root.right.value) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.value) {
            root.left = delete(root.left, value);
        } else if (value > root.value) {
            root.right = delete(root.right, value);
        } else {

            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node next = getMinNode(root.right);
            root.value = next.value;

            root.right = delete(root.right, next.value);
        }
        
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int bf = getBalanceFactor(root);
 
          // LL case
        if (bf > 1 && value < root.left.value) {
            return rightRotation(root);
        }
 
         // RR case
        if (bf < -1 && value > root.right.value) {
            return leftRotation(root);
        }
 
          // LR case
        if (bf > 1 && value > root.left.value) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }

         // RL case
        if (bf < -1 && value < root.right.value) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    public boolean search(int value) {
        Node current = root;
        
        while (current != null) {
            if (value == current.value) return true;

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    private int getBalanceFactor(Node root) {
        if (root == null) return 0;

        return getHeight(root.left) - getHeight(root.right);
    }

    private Node getMinNode(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node rightRotation(Node root) {
        
        Node ll = root.left;
        Node temp = ll.right;

        ll.right = root;
        root.left = temp;

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        ll.height = 1 + Math.max(getHeight(ll.left), getHeight(ll.right));

        return ll;
    }

    private Node leftRotation(Node root) {
        
        Node rr = root.right;
        Node temp = rr.left;

        rr.left = root;
        root.right = temp;

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        rr.height = 1 + Math.max(getHeight(rr.left), getHeight(rr.right));

        return rr;
    }
    
    private int getHeight (Node root) {
        if (root == null) return 0;
        return root.height;
    }
}
