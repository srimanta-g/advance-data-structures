public class BloomFilterDemo {
    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(20, 0.05);

        String[] words = new String[] {"abound","abounds","abundance","abundant","accessible",
                "bloom","blossom","bolster","bonny","bonus","bonuses",
                "coherent","cohesive","colorful","comely","comfort",
                "gems","generosity","generous","generously","genial"};

        String[] someInsertedWords = new String[] {"colorful","comely","comfort", "gems","generosity","generous","generously"};

        for (String word : words) {
            
            if (bloomFilter.check(word)) {
                System.out.println(word + ", is probably present.");
            } else {
                bloomFilter.add(word);
                System.out.println(word + ", is successfully inserted in the records.");
            }
        }

        for (String insertedWord : someInsertedWords) {
            if (bloomFilter.check(insertedWord)) {
                 System.out.println(insertedWord + ", is probably present.");
             } else {
                 bloomFilter.add(insertedWord);
                 System.out.println(insertedWord + ", is successfully inserted in the records.");
             }
        }
    }
}
