public class SegmentTree {
    int[] original;
    int[] tree;

    public SegmentTree(int[] original) {
        this.original = original;
        this.tree = new int[4 * original.length];

        buildTree(1, 0, original.length - 1);
    }

    private void buildTree(int node, int low, int high) {
        if (low == high) {
            tree[node] = original[low];
            return;
        }

        int mid = low + (high - low) / 2;

        buildTree(2 * node, low, mid);
        buildTree(2 * node + 1, mid + 1, high);

        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    public int getSum(int node, int trueLow, int trueHigh, int queryLow, int queryHigh) {
        if (queryHigh < trueLow || queryLow > trueHigh) {
            return 0;
        }

        if (queryLow <= trueLow && trueHigh <= queryHigh) {
            return tree[node];
        }

        int trueMid = trueLow + (trueHigh - trueLow) / 2;

        return getSum(2 * node, trueLow, trueMid, queryLow, queryHigh) + getSum(2 * node + 1, trueMid + 1, trueHigh, queryLow, queryHigh);
    }

    public void update(int node, int index, int low, int high, int newValue) {
        if (low == high) {
            original[index] += newValue;
            tree[node] += newValue;
        } else {
            int mid = low + (high - low) / 2;
            if (low <= index && index <= mid) {
                update(2 * node, index, low, mid, newValue);
            } else {
                update(2 * node + 1, index, mid + 1, high, newValue);
            }

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }
}
