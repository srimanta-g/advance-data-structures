import java.util.*;

public class BloomFilter {
    private int size;
    private double fpProb;
    private int hashCount;
    private BitSet bitArray;

    public BloomFilter (int itemCount, double fpProb) {
        this.fpProb = fpProb;
        this.size = getSize(itemCount, fpProb);
        this.hashCount = getHashCount(this.size, itemCount);
        this.bitArray = new BitSet(this.size);
        System.out.println(this.size + " " + this.hashCount);
    }

    private int getSize(int itemCount, double fpProb) {
        double m = -(itemCount * Math.log(fpProb)) / Math.pow(Math.log(2), 2);
        return (int) m;
    }

    private int getHashCount (int size, int itemCount) {
        return (int) ((size / itemCount) * Math.log(2));
    }

    public void add(String word) {
        int hash1 = word.hashCode();
        int hash2 = Integer.rotateLeft(hash1, 16);

        for (int i=0; i<this.hashCount; i++) {
            int index = Math.floorMod(hash1 + i * hash2, this.size);
            this.bitArray.set(index);
        }
    }

    public boolean check(String word) {
        int hash1 = word.hashCode();
        int hash2 = Integer.rotateLeft(hash1, 16);

        for (int i=0; i<this.hashCount; i++) {
            int index = Math.floorMod(hash1 + i * hash2, this.size);
            if (this.bitArray.get(index) == false) return false;
        }
        return true;
    }
}
