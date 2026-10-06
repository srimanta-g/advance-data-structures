public class SegmentTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 1, 4, 1}; // [1, 3, 6, 10, 11, 15, 16] - running sum

        SegmentTree st = new SegmentTree(arr);


        System.out.println(st.getSum(1, 0, arr.length - 1, 0, 2));
        System.out.println(st.getSum(1, 0, arr.length - 1, 2, 3));

        st.update(1, 1, 0, arr.length - 1, 2);
        
        // [1, 5, 8, 12, 13, 17, 18]

        System.out.println(st.getSum(1, 0, arr.length - 1, 0, 2));
    }
}
