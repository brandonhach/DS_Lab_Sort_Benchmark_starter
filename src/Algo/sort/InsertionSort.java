package Algo.sort;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<E> {

    /**
     * Insertion sort is a simple, in-place comparison-based sorting algorithm that
     * works by
     * building a sorted subarray one element at a time. The algorithm maintains two
     * subarrays
     * in the given array:
     * Sorted subarray
     * Unsorted subarray
     * Initially, the sorted subarray contains the first element of the given array,
     * and the
     * unsorted subarray contains the remaining elements. The algorithm then
     * performs the
     * following steps repeatedly:
     * Select the first element from the unsorted subarray.
     * Insert the element into its correct position in the sorted subarray by
     * comparing it
     * with each element in the sorted subarray and shifting the elements one
     * position to
     * the right until the correct position is found.
     * Move the boundary between the sorted and unsorted subarrays one element to
     * the right.The process repeats until the entire array is sorted.
     * 
     * @param <E>
     * @param list
     * @param comparator
     */
    public static <E> void sort(List<E> list, Comparator<E> comparator) {
        int j;
        E target;
        if (list == null || comparator == null)
            return;

        // TODO Implement the insertion sort
        for (int i = 1; i < list.size(); i++) {
            j = i;
            target = list.get(i);

            while (j > 0 && comparator.compare(target, list.get(j - 1)) < 0) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, target);
        }

    }

}
