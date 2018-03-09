/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boccaccio;
import java.util.*;
/**
 *
 * @author Fabio
 */
public class test {
    public static void main(String[] args) {
        AnagramManagement anagrammos = new AnagramManagement();
        WordsInDictionaryManagement word = new WordsInDictionaryManagement();
        FileManagement file = new FileManagement();
        ArrayList<ArrayList<String>> substringsAnagrams;
        ArrayList<String> stringsOnFile;
        HashMap<Character, Integer> af;
        
        ArrayList<String> pippo = new ArrayList<>(Arrays.asList("ab", "B", "Cin", "D","inc", "nci"));
        System.out.println(anagrammos.AnagramsInAnArrayList(pippo, "ab"));
        
        af = word.frequencyOfAlphabetLetterInDictionary(pippo);
        System.out.println(af);
        
        af = word.frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(pippo, "cin", "inc");
        System.out.println(af);
        
        substringsAnagrams = anagrammos.SubstringsAnagrams("cane");
        System.out.println(substringsAnagrams);
        
        substringsAnagrams = anagrammos.SubstringAnagramsForWordLenghtGreaterThanThree("ab", pippo);
        System.out.println(substringsAnagrams);
        
        stringsOnFile = file.WriteOnFileWordsBetweenThreeAndSevenLetters("boccaccio.txt", pippo);
        System.out.println(stringsOnFile);
    }
}
