/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;

/**
 *
 * @author ellen
 */
import Apps.WordFrequency;
import java.util.List;
import java.util.Random;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.Array1D;
import bridges.base.Array2D;
import bridges.base.Color;
import bridges.data_src_dependent.Shakespeare;
import java.io.FileInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.dictionary.Dictionary;


// a program fragment to access the Shakespeare data (plays, poems)
public class shakespeare_snippet {
    static final int WIDTH = 20;
    public static String[] splitLyrics(String lyrics) {       // splits raw lyrics string into a parsable array
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

    public static boolean wordOfInterest(String word){
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
    
    public static WordFrequency linearSearch(LinkedList<WordFrequency> wordList, String target){
        if (wordList == null || target == null) return null;
        
        int index1D = 0, row = 0, col = 0;
        for (WordFrequency word_freq : wordList){
            col = index1D % WIDTH;
            row = index1D / WIDTH;
            
            if (word_freq.getWord().equalsIgnoreCase(target))
                return word_freq;
            index1D++;
        }
        return null;
    }
    
    public static <E> int search(List<E> list, Comparator<E> comparator, E targetValue){
        //System.out.println(Arrays.toString(array));
        for (int i = 0; i< list.size(); i++)
            if (comparator.compare(list.get(i), targetValue)==0) 
                return i;

        return -1;
    }
    
    public static void main(String[] args) throws Exception {
        try {
            // initialize JWNL (this must be done before JWNL can be used)
            JWNL.initialize(new FileInputStream("./config/file_properties.xml"));
        } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
        }
        //create the Bridges object
        //Bridges bridges = new Bridges(YOUR_ASSIGNMENT_NUMBER, "YOUR_USER_ID", "YOUR_API_KEY");
        Bridges bridges = new Bridges(57, "qcheng1", "409743559662");
        // set title
        bridges.setTitle("Benchmarking search methods");
        // set description
        bridges.setDescription("Test...."); //TO CHANGE IT
        
        // Get a List of Shakespeare objects from Bridges
        DataSource ds = bridges.getDataSource();
        //List<String> wordList = new LinkedList<>();//WordFrequency
        HashMap<String, Integer> word_frequency_pairs = new HashMap<>();
        List<Shakespeare> shksp_list = bridges.getDataSource().getShakespeareData("poems", true);//"poems", true);//"plays", "sonnets" //ds.getShakespeareData();
        for (Shakespeare shk_work: shksp_list) {
            System.out.println(shk_work.getTitle());
            // Tokenize
            String[] wordlist = splitLyrics(shk_work.getText());

            //Insert each word to the binary-search-tree-based dictioary
            for (String word : wordlist) {
                //wordList.add(word);
                if (wordOfInterest(word)){
                    Integer freq = word_frequency_pairs.get(word); //my_dict.get(word);
                    if (freq == null)
                        word_frequency_pairs.put(word, new Integer(1));
                    else
                        word_frequency_pairs.put(word, new Integer(freq.intValue()+1));
                }
            }
        }
        
        //System.out.println(wordList.size());
        System.out.println(word_frequency_pairs.size());
        List<WordFrequency> wordList = new LinkedList<>();
        for (String key: word_frequency_pairs.keySet())
            wordList.add(new WordFrequency(key, word_frequency_pairs.get(key).intValue()));
        
        // Copy the List of Shakespeare objects to an array
        int rows = (word_frequency_pairs.size() + WIDTH -1)/WIDTH;
        int cols = WIDTH;
        Array2D<String> arr  = new Array2D<>(rows, cols);//Shakespeare
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int index1D = row * WIDTH + col;
                if (index1D >= word_frequency_pairs.size()) break;
                arr.getElement(row, col).setValue(String.valueOf(wordList.get(index1D).getWord()
                        + ":" + wordList.get(index1D).getFrequency())); //word_frequency_pairs.get(index1D))); 
                arr.getElement(row, col).setLabel(String.valueOf(arr.getElement(row, col).getValue()));//.getTitle()));
                if (row % 2 == 0)
                    arr.getElement(row, col).setColor("green");
                else
                    arr.getElement(row, col).setColor("yellow");
            }
        }

        // tell Bridges what datastructure to visualize
        bridges.setDataStructure(arr);

        // visualize the list
	bridges.visualize();
    }
}
