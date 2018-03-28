/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

/**
 *
 * @author Fabio
 */
import java.util.ArrayList;
import java.util.HashMap;

public interface Getters {
    public ArrayList<String> getWordsInDictionaryArrayList();

    public ArrayList<String> getAnagramsInDictionary();

    public ArrayList<String> getAnagrams();

    public ArrayList<ArrayList<String>> getSubstringAnagrams();

    public HashMap<Character, Integer> getLettersAbsoluteFrequencyMap();

    public HashMap<Character, Integer> getFreqBetweenTwoWords();

    public String getInput_file();

    public String getOutput_file();
    
    public ArrayList<String> getWordsLongSeven();
}
