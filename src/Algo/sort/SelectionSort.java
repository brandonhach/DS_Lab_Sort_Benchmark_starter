package Algo.sort;

import java.util.Comparator;
import java.util.List;

public class SelectionSort {

    /**
     * Selection sort is a sorting algorithm that works by repeatedly finding the
     * minimum element from
     * the unsorted part of an array and placing it at the beginning of the sorted
     * part of the array.
     * The algorithm maintains two subarrays in the given array.
     * Sorted subarray
     * Unsorted subarray
     * Initially, the sorted subarray is empty, and the unsorted subarray contains
     * all the elements of
     * the given array. The algorithm then performs the following steps repeatedly:
     * - Find the minimum element in the unsorted subarray.
     * - Swap the minimum element with the first element of the unsorted subarray.
     * - Move the boundary between the sorted and unsorted subarrays one element to
     * the right.
     * The process repeats until the entire array is sorted.
     * 
     * @param <E>
     * @param list
     * @param comparator
     */
    public static <E> void sort(List<E> list, Comparator<E> comparator) {
        if (list == null || comparator == null)
            return;
        // TODO Implement the selection sort
        if (list == null || comparator == null)
            return;
        // TODO Implement the selection sort
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                E temp = list.get(minIndex);
                list.set(minIndex, list.get(i));
                list.set(i, temp);
            }
        }
    }

}