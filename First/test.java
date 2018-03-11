/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boccaccio;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Fabio
 */
public class test {
    public static void main(String[] args) throws IOException {
        AnagramManagement anagrammos = new AnagramManagement();
        WordsInDictionaryManagement word = new WordsInDictionaryManagement();
        FileManagement file = new FileManagement();
        ArrayList<ArrayList<String>> substringsAnagrams;
        ArrayList<String> stringsOnFile;
        HashMap<Character, Integer> af;
        
        ArrayList<String> pippo = new ArrayList<>(Arrays.asList("AIDS", "Abbado", "ab","està", "nci"));
        System.out.println(anagrammos.AnagramsInAnArrayList(pippo, "ab"));
        
        stringsOnFile = file.WriteOnFileWordsBetweenThreeAndSevenLetters("Pippo.txt", pippo);
        System.out.println(stringsOnFile);
    }
}
