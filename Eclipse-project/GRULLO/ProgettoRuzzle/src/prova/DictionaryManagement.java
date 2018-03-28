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
import java.util.List;

public interface DictionaryManagement {
    ArrayList<String> anagramsInAnArrayList(String input_word);

    HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionary();

    List<String> extraction(String input_firstWord, String input_lastWord);

    HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionaryFromAWordToAnother(String input_firstWord,
                                                                                         String input_lastWord);
}

