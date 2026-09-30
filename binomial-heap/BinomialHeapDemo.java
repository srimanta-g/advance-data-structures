public class BinomialHeapDemo {
    public static void main(String[] args) {

        BinomialHeap bHeap1 = new BinomialHeap();

        bHeap1.insert(100);
        bHeap1.insert(2);
        bHeap1.insert(301);

        System.out.println(bHeap1.getMin() + " " + bHeap1.getSize());

        BinomialHeap bHeap2 = new BinomialHeap();

        bHeap2.insert(5);
        bHeap2.insert(3);
        bHeap2.insert(1);

        System.out.println(bHeap2.getMin() + " " + bHeap2.getSize());
        
        bHeap1.merge(bHeap2);

        System.out.println(bHeap1.getMin() +  " " + bHeap1.getSize());
    }
}
