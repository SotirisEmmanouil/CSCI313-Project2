import java.util.Arrays;

public class mergeSort {
    
    public static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return; 	//base case which checks if array is sorted or completely empty
        }
        
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid]; //create two arrays with 
        
        
        for (int i = 0; i < mid; i++) {	 //fill each of the two arrays
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }
        
        // Recursively sort the two arrays
        mergeSort(left);
        mergeSort(right);
        
        // Merge the sorted arrays 
        merge(array, left, right);
    }
    
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0; // Index for merged array
        
        // Merge elements from left and right arrays into the merged array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements from left array
        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }
        
        // Copy remaining elements from right array
        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
    
    public static void main(String[] args) {
        int[] array = {9, 5, 1, 3, 8, 4, 2, 7, 6};
        
        System.out.println("Original array: " + Arrays.toString(array));
        mergeSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
