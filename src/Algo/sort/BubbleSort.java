package Algo.sort;

import java.util.Comparator;
import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Qiong
 */
public class BubbleSort {
    public static void sort(int numbers [], int numbersSize) {
        int i=0,k=0,temp=0;
        int is_sorted=1;
        for(k = 0;k < numbersSize -1; k++) {//n
            is_sorted=1;
            for(i=0;i < numbersSize - k - 1;i++) {//      best case loop will run for n-1 times best case complexity is n
                        //      worst case (descending order) the loop will run for
                if(numbers[i] > numbers[i+1] ) {// (n-1)+(n-2)+(n-3)...3+2+1 times = (n^2)/2-n/2 times
                    temp=numbers[i];            // worst case time complexity is n^2
                    numbers[i]=numbers[i+1];    // avg case=(best case + worst case)/2 
                    numbers[i+1]=temp;          // (n^2+n)/2=n^2
                    is_sorted=0;                // avg case time complexity is n^2
                }
            }
            if(is_sorted==1) {
                break;
            }
        }
    }

    public static <E> void sort(List<E> list, Comparator<E> comparator) {
        int i=0,k=0;
        E temp;
        int is_sorted=1;
        for(k = 0;k < list.size() -1; k++) {//n
            is_sorted=1;
            for(i=0;i < list.size() - k - 1;i++) {//      best case loop will run for n-1 times best case complexity is n
                        //      worst case (descending order) the loop will run for
                if(comparator.compare(list.get(i), list.get(i+1)) > 0 ) {// (n-1)+(n-2)+(n-3)...3+2+1 times = (n^2)/2-n/2 times
                    temp=list.get(i);            // worst case time complexity is n^2
                    list.set(i, list.get(i+1));    // avg case=(best case + worst case)/2 
                    list.set(i+1, temp);           // (n^2+n)/2=n^2
                    is_sorted=0;                // avg case time complexity is n^2
                }
            }
            if(is_sorted==1) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int numbers [] = {10, 7, 6, 5, 4, 3, 2, 1};
        int n = 8;
        int i=0,k=0,temp=0;
        int is_sorted=1;
        System.out.print("UNSORTED: ");
        for (i = 0; i < n; ++i) {
                System.out.print(numbers[i] + " ");
        }
        System.out.println();
        /*for(k=0;k < n-1;k++) {//n
            is_sorted=1;
            for(i=0;i < n-k-1;i++) {//      best case loop will run for n-1 times best case complexity is n
                        //      worst case (descending order) the loop will run for
                if(numbers[i] > numbers[i+1] ) {// (n-1)+(n-2)+(n-3)...3+2+1 times = (n^2)/2-n/2 times
                    temp=numbers[i];            // worst case time complexity is n^2
                    numbers[i]=numbers[i+1];    // avg case=(best case + worst case)/2 
                    numbers[i+1]=temp;          // (n^2+n)/2=n^2
                    is_sorted=0;                // avg case time complexity is n^2

                }
            }
            if(is_sorted==1) {
                    break;
            }
        }*/
        BubbleSort.sort(numbers, n);
        System.out.print("SORTED: ");
        for (i = 0; i < n; ++i) {
                System.out.print(numbers[i] + " ");
        }
        System.out.println();

    }
}
