/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;

import java.util.Comparator;

/**
 *
 * @author ellen
 */
public class WordFrequency implements Comparable<WordFrequency>{
    String word;
    int frequency;
    
    public WordFrequency(String aWord, int aFrequency){
        this.word = aWord;
        this.frequency = aFrequency;
    }
    
    public String getWord(){
        return this.word;
    }
    
    public int getFrequency(){
        return this.frequency;
    }
    
    public void setWord(String aWord){
        this.word = aWord;
    }
    
    public void setFrequency(int aFrequency){
        this.frequency = aFrequency;
    }

    @Override
    public int compareTo(WordFrequency t) {
        if (this.getWord().equalsIgnoreCase(t.getWord()))
            return 0;
        else
            return this.getWord().compareTo(t.getWord());
    }
    
    public static class Comparators {
        public static Comparator<WordFrequency> word_comparison = new Comparator<WordFrequency>(){
            @Override
            public int compare(WordFrequency wf1, WordFrequency wf2){
                if (wf1.getWord().equalsIgnoreCase(wf2.getWord()))
                    return 0;
                else
                    return wf1.getWord().compareTo(wf2.getWord());
            }
        };
    }
}
