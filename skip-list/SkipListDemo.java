public class SkipListDemo {
    public static void main(String[] args) {
        SkipList s = new SkipList();

        s.insert(1);
        s.insert(2);
        s.insert(5);
        s.insert(8);

        s.insert(9);

        System.out.println(s.search(8));
        System.out.println(s.search(10));

        s.insert(10);
        System.out.println(s.search(10));

        System.out.println(s.delete(19) == true ? "Element deleted" : "Element not deleted");
        System.out.println(s.delete(10) == true ? "Element deleted" : "Element not deleted");
    }
}
