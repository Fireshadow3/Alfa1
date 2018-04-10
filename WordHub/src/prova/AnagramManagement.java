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

public interface AnagramManagement {
    ArrayList<String> anagramGenerator(String s);

    ArrayList<ArrayList<String>> substringsAnagrams(String input_word);

    ArrayList<ArrayList<String>> substringAnagramsForWordLengthGreaterThanThree(String input_word);
}

