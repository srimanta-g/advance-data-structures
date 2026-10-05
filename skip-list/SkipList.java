import java.util.Random;
public class SkipList {
    
    private static final int MAX_LEVEL = 16;
    
    private final Node head;
    private int currentLevel;
    
    private final Random random = new Random();

    public SkipList() {
        this.head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        this.currentLevel = 1;
    }

    private int getRandomLevel() {
        int level = 1;

        while (this.random.nextBoolean() && level < MAX_LEVEL) {
            level ++;
        }

        return level;
    }
    

    public boolean search(int value) {
        Node current = head;

        for (int level = currentLevel - 1; level >= 0; level --) {
            while (current.next[level] != null && current.next[level].value < value) {
                current = current.next[level];
            }
        }

        current = current.next[0];

        return current != null && current.value == value;
    }

    public void insert(int value) {
        Node[] prev = new Node[MAX_LEVEL];
        Node current = head;

        for (int level = currentLevel - 1; level >= 0; level --) {
            while (current.next[level] != null && current.next[level].value < value) {
                current = current.next[level];
            }
            prev[level] = current;
        }

        int newLevel = getRandomLevel();

        if (newLevel > currentLevel) {
            for (int level = currentLevel; level < newLevel; level ++) {
                prev[level] = head;
            }
        }

        currentLevel = newLevel;

        Node newNode = new Node(value, newLevel);

        for (int level = currentLevel - 1; level >= 0; level --) {
            newNode.next[level] = prev[level].next[level];
            prev[level].next[level] = newNode;
        }
    }

    public boolean delete(int value) {
        Node[] prev = new Node[MAX_LEVEL];

        Node current = head;

        for (int level = currentLevel - 1; level >= 0; level --) {
            while (current.next[level] != null && current.next[level].value < value) {
                current = current.next[level];
            }

            prev[level] = current;
        }

        current = current.next[0];

        if (current == null || current.value != value) return false;

        for (int level = 0; level < currentLevel; level ++) {
            if (prev[level].next[level] != current) break;
            prev[level].next[level] = current.next[level];
        }

        while (currentLevel > 1 && head.next[currentLevel - 1] == null) {
            currentLevel --;
        }

        return true;
    }
}
