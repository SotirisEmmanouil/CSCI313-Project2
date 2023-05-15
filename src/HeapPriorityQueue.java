import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {


        protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
        
        
        public HeapPriorityQueue() {
                 super();
                 
        }
      
         public HeapPriorityQueue(Comparator<K> comp) {
                   super(comp);
                 
         }
         public HeapPriorityQueue(K[ ] keys, V[ ] values) {
                 super();
                 for (int j=0; j < Math.min(keys.length, values.length); j++)
                     heap.add(new PQEntry<>(keys[j], values[j]));
                         heapify();
         }
       
         protected void heapify() {
           int startIndex = parent(size()-1);
                for (int j=startIndex; j >= 0; j--)
                           downheap(j);
             }
        
             protected int parent(int j) {
                     return (j-1) / 2;
                     }
             protected int left(int j) {
            	 return 2*j + 1;
             }
            	 
            	 protected int right(int j) {
                     return 2*j + 2;
                     }
             protected boolean hasLeft(int j) {
                return left(j) < heap.size();
                     
             }
             protected boolean hasRight(int j) {
                     return right(j) < heap.size();
             }
          
             protected void swap(int i, int j) {
                         Entry<K,V> temp = heap.get(i);
                         heap.set(i, heap.get(j)); 
                         heap.set(j, temp);
                         
                       }
             protected void upHeap(int j) {
                          if (j <= 1)
                            return;
                          int p = parent(j);
                          if (Compare(heap.get(j), heap.get(p)) >= 1)
                            return;
                          swap(j, p);
                          upHeap(p);
                        }
              
             protected int downheap(int j) {
                         if(left(j) > heap.size()) 
                        	 return j;
                            int leftIndex = left(j);
                            int smallChildIndex = leftIndex;
                             if (hasRight(j)) { 
                             int rightIndex = right(j);
                             if (Compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                             smallChildIndex = rightIndex;
                             }
                            if(Compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                             swap(j, smallChildIndex);
                             j = smallChildIndex;
                             j = smallChildIndex;
                             return downheap(2*j + 1);
                         
             
							}				

                         
                        public int size() {
                           return heap.size();
                        } 
                        
                        public Entry<K,V> Min() {
                         if (heap.isEmpty()) return null;
                         return heap.get(0); 
                        }
             
                        
             

             public Entry<K,V> Insert(K key, V value) throws IllegalArgumentException {
              checkKey(key);
              Entry<K,V> newest = new PQEntry<>(key,value);
              heap.add(newest);
              upHeap(heap.size() - 1);
              return newest;
                    }

             public Entry<K,V> RemoveMin() {
              if (heap.isEmpty()) return null;
             Entry<K,V> answer = heap.get(0);
              swap(0, heap.size() - 1);
              heap.remove(heap.size() - 1);
              downheap(0);
              return answer;    
             }
             
          public Entry<K, V> min() {
                 return null;
         }

         public Entry<K, V> removeMin() {
                 return null;
         }
  
         
   public static void main(String[] args) {
	   
	   Integer   [] values = {3,24, 5, 6};
	   Character [] keys = {'a','q','W','R'};

	   HeapPriorityQueue heap = new HeapPriorityQueue(values,keys);
	  
          
              }
   	
   
   	}
             

         
