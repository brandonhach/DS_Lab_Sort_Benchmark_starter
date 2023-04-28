package Algo.benchmark.util;

import Apps.WordFrequency;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ITSC 2214 and ITCS 6114 at cs.cci.uncc.edu
 */
public class CollectionGenerator {
    static Random rand = new Random(); 
    /**
     * Generate a random array list
     * @param size
     * @param order
     * @return 
     */
    public static ArrayList<Integer> generateRandomDataList(int size, int order){
        ArrayList<Integer> randList = new ArrayList<Integer>();
        
        for (int i = 0; i < size; i++){
            randList.add(rand.nextInt(size));
        }
        
        if (order == 0) return randList;
        else{
            if (order == 1)
                Collections.sort(randList); 
            else if (order == -1)
                Collections.sort(randList, Collections.reverseOrder());
            
            return randList;
        }
    }
    
    /**
     * Generate a random array list
     * @param size
     * @param order
     * @return 
     */
    public static <E extends Comparable> List<E> generateRandomDataList(List<E> wordList, 
            int size, int order){
        if (wordList == null || size <= 0 || order < -1 || order > 1) return null;
        
        List<E> randList = new LinkedList<>();
        
        for (int i = 0; i < size; i++){
            randList.add(wordList.get(rand.nextInt(size)));
        }
        
        if (order == 0) return randList;
        else{
            if (order == 1)
                Collections.sort(randList); 
            else if (order == -1)
                Collections.sort(randList, Collections.reverseOrder());
            
            return randList;
        }
    }
}
