import java.util.Comparator;

public interface PriorityQueue<K,V> {
 int size();
 boolean isEmpty();
 Entry<K,V> Insert(K key, V value) throws IllegalArgumentException;
 Entry<K,V> min();
 Entry<K,V> removeMin();


 }
