/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;

import static Apps.shakespeare_snippet.splitLyrics;
import static Apps.shakespeare_snippet.wordOfInterest;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Shakespeare;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.dictionary.Dictionary;

/**
 *
 * @author ellen
 */
public class Shakespeare_Words {
    private static String[] splitLyrics(String lyrics) {       // splits raw lyrics string into a parsable array
        lyrics = lyrics.replaceAll("\\[.+\\]", "");         // removes the titles of song stage ex [Intro]
        lyrics = lyrics.trim();
        String[] lyricsSplit = lyrics.split("\\s+");

        for (int i = 0; i < lyricsSplit.length; i++) {          // clears special characters from individual terms
            lyricsSplit[i] = lyricsSplit[i].replaceAll("\\W+$", "");
            lyricsSplit[i] = lyricsSplit[i].replaceAll("^\\W+", "");
            lyricsSplit[i] = lyricsSplit[i].trim();
        }

        return lyricsSplit;
    }

    private static boolean wordOfInterest(String word){
        try{
            IndexWord fword = null; 
            Dictionary dict = Dictionary.getInstance();

            fword = dict.lookupIndexWord(POS.NOUN, word);
            if(fword!=null)
                return true;
            fword = dict.lookupIndexWord(POS.ADJECTIVE, word);
            if(fword!=null)
                return true;
            fword = dict.lookupIndexWord(POS.ADVERB, word);
            if(fword!=null)
                return true;
            fword = dict.lookupIndexWord(POS.VERB, word);
            if(fword!=null)
                return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
    public static List<WordFrequency> retrieveShakespeareWords(
            int YOUR_ASSIGNMENT_NUMBER, String Bridges_User_Id, String Bridges_User_API_Key){
        try {
            // initialize JWNL (this must be done before JWNL can be used)
            //JWNL.initialize(new FileInputStream("./config/file_properties.xml"));
        } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
        }
        //Bridges bridges = new Bridges(YOUR_ASSIGNMENT_NUMBER, "YOUR_USER_ID", "YOUR_API_KEY");
        Bridges bridges = new Bridges(YOUR_ASSIGNMENT_NUMBER, Bridges_User_Id, Bridges_User_API_Key);
                // Get a List of Shakespeare objects from Bridges
        DataSource ds = bridges.getDataSource();
        //List<String> wordList = new LinkedList<>();//WordFrequency
        HashMap<String, Integer> word_frequency_pairs = new HashMap<>();
        List<Shakespeare> shksp_list = null;
        try{
            shksp_list = bridges.getDataSource().getShakespeareData("poems", true);//"poems", true);//"plays", "sonnets" //ds.getShakespeareData();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        for (Shakespeare shk_work: shksp_list) {
            System.out.println(shk_work.getTitle());
            // Tokenize
            String[] wordlist = splitLyrics(shk_work.getText());

            //Insert each word to the binary-search-tree-based dictioary
            for (String word : wordlist) {
                //wordList.add(word);
                //if (wordOfInterest(word)){
                if (word.trim().length() > 0){
                    if (word.trim().length() > 0){
                        Integer freq = word_frequency_pairs.get(word); //my_dict.get(word);
                        if (freq == null)
                            word_frequency_pairs.put(word, new Integer(1));
                        else
                            word_frequency_pairs.put(word, new Integer(freq.intValue()+1));
                    }
                }
            }
        }
        
        //System.out.println(wordList.size());
        System.out.println(word_frequency_pairs.size());
        List<WordFrequency> wordList = new LinkedList<>();
        for (String key: word_frequency_pairs.keySet())
            wordList.add(new WordFrequency(key, word_frequency_pairs.get(key).intValue()));
        
        return wordList;
    }
}
