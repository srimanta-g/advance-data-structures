public class AvlTreeDemo {
    public static void main(String[] args) {
        AvlTree avl = new AvlTree();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);

        System.out.println(avl.search(1));
        System.out.println(avl.search(4));

        avl.delete(1);

        System.out.println(avl.search(1));
    }
}
