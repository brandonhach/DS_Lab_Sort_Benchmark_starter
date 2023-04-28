/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algo.benchmark;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Algo.sort.InsertionSort;
import Algo.sort.MergeSort;
import Algo.sort.SelectionSort;
import Apps.Shakespeare_Words;
import Apps.WordFrequency;
import viz.BridgesLineChart;

/**
 *
 * @author ITSC 2214 and ITCS 6114 at cs.cci.uncc.edu
 */
public class BenchmarkSort {
    public final static int REPEATS = 10;
    public final static int DEFAULT_MIN_LIST_SIZE = 100;
    public final static int DEFAULT_MAX_LIST_SIZE = 1000;
    public final static int DEFAULT_LIST_SIZE_UPDATE = 50;

    public final static int NUMBER_OF_SORTS = 3;
    public final static int INSERTION_SORT = 1;
    public final static int SELECTION_SORT = 2;
    public final static int MERGE_SORT = 3;

    public final static String[] Y_legends = { "Insertion sort", "Selection sort", "Merge sort" };

    public final static int BEST_CASE = 1;
    public final static int WORST_CASE = 2;
    public final static int AVERAGE_CASE = 3;
    public final static int INPUT_INSTANCE_SIZE = 0;

    /**
     * Test framework for one examination of sort algorithms by giving an input
     * instance
     * 
     * @param numbers
     * @param isPrint Print the execution running time for each algorithm
     * @return
     */
    public static <E extends Comparable> long[] benchmark(List<E> list, Comparator<E> comparator, boolean isPrint) {
        long startTime;

        if (list == null)
            return null;

        int size = list.size();
        long duration[] = new long[NUMBER_OF_SORTS + 1];
        duration[INPUT_INSTANCE_SIZE] = size;
        List<E> tmp = new ArrayList<>(list.size());
        for (int i = 0; i < size; i++)
            tmp.add(list.get(i));

        /* initial call to quicksort with index */
        startTime = System.nanoTime();
        InsertionSort.sort(tmp, comparator);
        duration[INSERTION_SORT] = System.nanoTime() - startTime;
        if (isPrint)
            System.out.print(duration[INPUT_INSTANCE_SIZE] + "\t" + duration[INSERTION_SORT] + "\t");

        for (int i = 0; i < size; i++)
            tmp.set(i, list.get(i));
        startTime = System.nanoTime();
        SelectionSort.sort(tmp, comparator);
        duration[SELECTION_SORT] = System.nanoTime() - startTime;
        if (isPrint)
            System.out.print(duration[SELECTION_SORT] + "\t");

        for (int i = 0; i < size; i++)
            tmp.set(i, list.get(i));
        startTime = System.nanoTime();
        MergeSort.sort(tmp, comparator, 0, size - 1);
        duration[MERGE_SORT] = System.nanoTime() - startTime;
        if (isPrint)
            System.out.println(duration[MERGE_SORT]);

        return duration;
    }

    /**
     * Examine sort algorithms in average case
     * 
     * @param size_start
     * @param size_end
     * @param size_updates
     * @param title
     */
    public static <E extends Comparable> void testShakespeane(List<E> wordList,
            Comparator<E> comparator,
            int size_start,
            int size_end, int size_updates, String title, int caseid,
            int Bridges_task_id, String Bridges_user_id, String Bridges_user_api_key) {
        int times = (size_end - size_start + 1) % size_updates == 0
                ? (size_end - size_start + 1) / size_updates
                : (size_end - size_start + 1) / size_updates + 1;
        int count = 0;
        long duration[][] = new long[times][NUMBER_OF_SORTS + 1];
        long tmp_duration[];
        long total_duration[] = new long[NUMBER_OF_SORTS + 1];

        List<E> list = null, sortedList = null;

        double[] X = new double[times];

        ArrayList<ArrayList<Double>> Ys = new ArrayList<>(times);
        for (int exp_idx = 0; exp_idx < times; exp_idx++)
            Ys.add(new ArrayList<Double>(NUMBER_OF_SORTS));

        for (int i = size_start; i <= size_end; i += size_updates) {
            X[count] = i;
            for (int k = 0; k < NUMBER_OF_SORTS + 1; k++)
                total_duration[k] = 0;

            for (int j = 0; j < REPEATS; j++) {
                list = Algo.benchmark.util.CollectionGenerator.generateRandomDataList(wordList, i, caseid);
                // WordFrequency [] array = Algo.testset.util.Common.copy2Array(list);
                tmp_duration = benchmark(list, comparator, false);

                for (int k = 0; k < NUMBER_OF_SORTS + 1; k++)
                    total_duration[k] += tmp_duration[k];
            }

            for (int k = 0; k < NUMBER_OF_SORTS + 1; k++) {
                duration[count][k] = total_duration[k] / REPEATS;
                if (k > 0)
                    Ys.get(count).add(new Double(duration[count][k]));
            }

            count++;
        }

        for (int i = 0; i < count; i++)
            System.out.println(duration[i][INPUT_INSTANCE_SIZE]
                    + "\t" + duration[i][INSERTION_SORT]
                    + "\t" + duration[i][SELECTION_SORT]
                    + "\t" + duration[i][MERGE_SORT]);

        BridgesLineChart.linechart(X, Ys, Y_legends,
                Bridges_task_id, Bridges_user_id,
                Bridges_user_api_key, title);

    }

    public static void main(String[] argv) {
        int Bridges_task_id = 36;
        String Bridges_user_id = "brandonhach"; // TODO Please change it to be your own user ID on Bridges
        String Bridges_user_api_key = "581218346755";// TODO Please change it to be your own API Key on Bridges
        List<WordFrequency> wordList = Shakespeare_Words.retrieveShakespeareWords(
                Bridges_task_id, Bridges_user_id, Bridges_user_api_key);

        int start = DEFAULT_MIN_LIST_SIZE;
        int end = (wordList.size() > DEFAULT_MAX_LIST_SIZE ? DEFAULT_MAX_LIST_SIZE : wordList.size());
        int update = DEFAULT_LIST_SIZE_UPDATE;

        System.out.println("================Comparison (Best Case)================");
        System.out.println("Input instance size\tInsertion Sort\tSelection Sort\tMerge Sort");
        testShakespeane(wordList, WordFrequency.Comparators.word_comparison,
                start, end, update, "Comparison (Best Case)", 1,
                Bridges_task_id + 1, Bridges_user_id, Bridges_user_api_key);

        System.out.println("================Comparison (Worst Case)================");
        System.out.println("Input instance size\tInsertion Sort\tSelection Sort\tMerge Sort");
        testShakespeane(wordList, WordFrequency.Comparators.word_comparison,
                start, end, update, "Comparison (Worst Case)", -1,
                Bridges_task_id + 2, Bridges_user_id, Bridges_user_api_key);

        System.out.println("================Comparison (Average Case)================");
        System.out.println("Input instance size\tInsertion Sort\tSelection Sort\tMerge Sort");
        testShakespeane(wordList, WordFrequency.Comparators.word_comparison,
                start, end, update, "Comparison (Average Case)", 0,
                Bridges_task_id + 3, Bridges_user_id, Bridges_user_api_key);
    }
}
