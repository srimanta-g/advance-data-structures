public class BinomialTreeDemo {
    public static void main(String[] args) {
        BinomialTree bt = new BinomialTree(3);

        bt.insert(1);
        bt.insert(2);
        bt.insert(3);

        System.out.println("Size of the tree is : " + bt.getSize());

        bt.printTree();
    }
}
