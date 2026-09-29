public class BinomialTree {
    
    private int size;
    private int order;
    private BinomialTreeNode root;

    
    public BinomialTree (int order) {
        this.size = 0;
        this.order = order;

        this.root = new BinomialTreeNode(this.order);

        createTree(root);
    }

    private void createTree (BinomialTreeNode node) {
        int childrenCount = node.numOfChildren;

        if (childrenCount == 0) return;

        for (int i=0; i<childrenCount; i++) {
            node.children[i] = new BinomialTreeNode(i);

            createTree(node.children[i]);
        }
    }

    public int getSize () {
        return this.size;
    }

    public boolean isEmpty () {
        return this.size == 0;
    }

    public void insert (int value) {
        insert(root, value);   
    }

    public void printTree () {
        printTree(root);
    }

    private void printTree (BinomialTreeNode node) {
        if (node.value != -1) System.out.println(node.value);

        for (int i=0; i<node.numOfChildren; i++) {
            printTree(node.children[i]);    
        }
    }

    private boolean insert (BinomialTreeNode node, int value) {
        if (node.value == -1) {
            node.value = value;
            return true;
        }

        for (int i=0;i<node.numOfChildren; i++) {
            if (insert(node.children[i], value)) return true;
        }

        return false;
    }

    private class BinomialTreeNode {
        int value;
        int numOfChildren;
        BinomialTreeNode[] children;

        BinomialTreeNode (int numOfChildren) {
            this.value = -1;
            this.numOfChildren = numOfChildren;
            this.children = new BinomialTreeNode[numOfChildren];
        }
    }

}
