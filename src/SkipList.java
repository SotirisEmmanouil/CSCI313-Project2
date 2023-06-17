import java.util.Random;

class SkipListNode<K extends Comparable<K>, V> {
    K key;
    V value;
    SkipListNode<K, V>[] next;

    public SkipListNode(K key, V value, int level) {		//skiplistnode constructor which takes in 
        this.key = key;										// a key, a value and a level.
        this.value = value;	
        this.next = new SkipListNode[level + 1];
    }
}

class SkipList<K extends Comparable<K>, V> {
    private static final double DEFAULT_PROBABILITY = 0.5;
    private static final int MAX_LEVEL = 16; // Maximum level for the skip list

    private SkipListNode<K, V> head;
    private int level;
    private Random random;
    private double probability;

    public SkipList() {			//skiplist constructor 
        this.head = new SkipListNode<>(null, null, MAX_LEVEL);
        this.level = 0;
        this.random = new Random();
        this.probability = DEFAULT_PROBABILITY;
    }

    public void skipInsert(K key, V value) {
        SkipListNode<K, V>[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode<K, V> current = head;

        // Find the appropriate position to insert the new node
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key.compareTo(key) < 0) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        // If the key already exists, update the value
        if (current != null && current.key.equals(key)) {
            current.value = value;
        } else {
            // Generate a random level for the new node
            int newLevel = randomLevel();

            // If the new level is higher than the current level, update the update array
            if (newLevel > level) {
                for (int i = level + 1; i <= newLevel; i++) {
                    update[i] = head;
                }
                level = newLevel;
            }

            // Create a new node with the given key, value, and level
            SkipListNode<K, V> newNode = new SkipListNode<>(key, value, newLevel);

            // Update the next pointers
            for (int i = 0; i <= newLevel; i++) {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }
        }
    }

    public V skipSearch(K key) {			//search for a skiplist node using the key 
        SkipListNode<K, V> current = head;

        // Traverse the skip list to find the node with the given key
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key.compareTo(key) < 0) {
                current = current.next[i];
            }
        }

        current = current.next[0];

        // If the key is found, return the corresponding value
        if (current != null && current.key.equals(key)) {
            return current.value;
        }

        return null; // Key not found
    }

    public void skipRemove(K key) {		//remove method 
        SkipListNode<K, V>[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode<K, V> current = head;

        // Find the node to be removed and update the update array
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key.compareTo(key) < 0) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        // If the key is found, remove the node
        if (current != null && current.key.equals(key)) {
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] != current) {
                    break;
                }
                update[i].next[i] = current.next[i];
            }

            // Update the level if necessary
            while (level > 0 && head.next[level] == null) {
                level--;
            }
        }
    }

    // Generates a random level for a new node
    private int randomLevel() {
        int level = 0;
        while (random.nextDouble() < probability && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }
    
    public static void main(String [] args) {
    	
    	SkipList<Integer, String> skipList = new SkipList<>();

    	skipList.skipInsert(1, "12");
    	skipList.skipInsert(2, "26");
    	skipList.skipInsert(3, "47");

    	String value1 = skipList.skipSearch(1); // Retrieve the value associated with key 1
    	System.out.println(value1); // Output: "Value 1"
    	String value2 = skipList.skipSearch(2);
    	System.out.println(value2);
    	String value3 = skipList.skipSearch(3);
    	System.out.println(value3);
    }
}
