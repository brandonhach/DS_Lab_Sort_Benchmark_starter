package Algo.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <E> void merge(List<E> list, Comparator<E> comparator, int low, int mid, int high) {
        int mergedSize = high - low + 1; // Size of merged partition
        List<E> mergedNumbers = new ArrayList<E>(mergedSize); // Temporary array for merged numbers
        int mergePos; // Position to insert merged number
        int leftPos; // Position of elements in left partition
        int rightPos; // Position of elements in right partition

        mergePos = 0;
        leftPos = low; // Initialize left partition position
        rightPos = mid + 1; // Initialize right partition position

        // TODO implement the merge algorithm
        if (mergedSize <= 0)
            return;

        while (leftPos <= mid && rightPos <= high) {
            E itemInLeft = list.get(leftPos);
            E itemInRight = list.get(rightPos);

            if (comparator.compare(itemInLeft, itemInRight) < 0) {
                mergedNumbers.add(itemInLeft);
                leftPos++;
            } else {
                mergedNumbers.add(itemInRight);
                rightPos++;
            }
            mergePos++;
        }

        while (leftPos <= mid) {
            E itemInLeft = list.get(leftPos);
            mergedNumbers.add(itemInLeft);
            leftPos++;
        }

        while (rightPos <= high) {
            E itemInRight = list.get(rightPos);
            mergedNumbers.add(itemInRight);
            rightPos++;
        }

        for (int i = 0; i < mergedNumbers.size(); i++) {
            list.set(low + i, mergedNumbers.get(i));
        }
    }

    /**
     * Merge sort is a popular comparison-based sorting algorithm that follows the
     * divide-and-conquer
     * paradigm. The algorithm works by recursively dividing the input array into
     * two halves, sorting
     * the two halves independently using merge sort, and then merging the two
     * sorted halves to produce
     * the final sorted array.
     * 
     * @param <E>
     * @param list
     * @param comparator
     * @param low
     * @param high
     */
    public static <E> void sort(List<E> list, Comparator<E> comparator, int low, int high) {
        int j;

        if (low < high) {
            j = (low + high) / 2; // Find the midpoint in the partition

            // TODO Recursively sort left and right partitions

            sort(list, comparator, low, j); // first half
            sort(list, comparator, j + 1, high); // seconfd half

            // TODO Merge left and right partition in sorted order
            merge(list, comparator, low, j, high);

        }
    }

}