/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordHub;

/**
 *
 * @author Fabio
 */
import java.util.ArrayList;

public class AnagramManagement implements Constants{
    static DictionaryManagement dictionary;
    /**
     * Lista contenente tutti gli anagrammi di tutte le sottostringhe di una parola 
     */
    private static ArrayList<ArrayList<String>> substringAnagrams;
    
    public AnagramManagement(String input_file, String output_file){
        dictionary = new DictionaryManagement(input_file, output_file);
        substringAnagrams = new ArrayList<>();
    }
    /**
     * Ottiene il dizionario completo di tutti i metodi ad esso correlati
     * @return dictionary manager
     */
    public static DictionaryManagement getDictionary(){
        return dictionary;
    }
    
    /**
     * Ottiene gli anagrammi delle sottostringhe degli anagrammi di una parola generati dal metodo substringsAnagrams
     *
     * @return Arraylist di arralist di stringhe
     */
    public static ArrayList<ArrayList<String>> getSubstringAnagrams() {
        return substringAnagrams;
    }
    
    /**
     * Genera tutti gli anagrammi di ogni sottostringa della parola data in input
     *
     * @param input_word Stringa delle cui sottostringhe generare gli anagrammi
     * @return           Un arraylist di stringhe contenente le parole scritte sul file
     */
    public static ArrayList<ArrayList<String>> substringsAnagrams(String input_word) {
        for (int i = 0; i < input_word.length(); i++)
            for (int j = i + 1; j <= input_word.length(); j++)
                substringAnagrams.add(dictionary.anagramGenerator(input_word.substring(i, j)));
        return substringAnagrams;
    }
    
    /**
     * Genera gli anagrammi delle sottostringhe di una parola di lunghezza uguale o superiore a 3 lettere
     *
     * @param input_word Stringa di cui generare gli anagrammi
     * @return           Un arraylist di stringhe contenente le parole scritte sul file
     */
    public static ArrayList<ArrayList<String>> substringAnagramsForWordLengthGreaterThanThree(String input_word) {
        if (input_word.length() >= MIN_LENGTH_ANAG && dictionary.file.getWordsInDictionaryArrayList().contains(input_word))
            return substringsAnagrams(input_word);
        else
            return new ArrayList<>();
    }
}

