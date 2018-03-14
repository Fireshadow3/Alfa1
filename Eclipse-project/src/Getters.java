package schoolproject;

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
}