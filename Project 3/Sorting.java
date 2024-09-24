import java.util.Arrays;
public class Sorting {
    public static <T extends Comparable<? super T>> void heapSort(T[] array) {
        int n = array.length;
        for(int i = (n / 2) - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for(int i = n - 1; i >= 0; i--) {
            T temp = array[0];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }
    public static <T extends Comparable<? super T>> void heapify(T[] array, int length, int parent) {
        int left = (2 * parent) + 1;
        int right = (2 * parent) + 2;
        int largest = parent;

        if(left < length && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }
        if(right < length && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }
        if(largest != parent) {
            T temp = array[parent];
            array[parent] = array[largest];
            array[largest] = temp;
            heapify(array, length, parent);
        }
    }
}
