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

public class AnagramManagement {
	private static final int MIN_LENGHT = 3;
	// genera anagrammi
	public ArrayList<String> anagramGenerator(String s) {
		ArrayList<String> finalArrayList = new ArrayList<>();
		if (s.length() <= 1)
			finalArrayList.add(s);
		else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				ArrayList<String> temp = anagramGenerator(s.substring(0, i) + s.substring(i + 1));
				for (String element : temp)
					finalArrayList.add(c + element);
			}
		}
		return finalArrayList;
	}

	// dato un ArrayList, conta il numero di ricorrenze di ogni anagramma
	public ArrayList<String> AnagramsInAnArrayList(ArrayList<String> input_ArrayList, String input_word) {
		ArrayList<String> result = new ArrayList<>();
		// ArrayList anagrammi
		ArrayList<String> anagrams = anagramGenerator(input_word);
                for (String s : anagrams)
                        if (Arrays.binarySearch(input_ArrayList.toArray(), s) >= 0)
                                result.add(s);
		return result;
	}
        
        public ArrayList<ArrayList<String>> SubstringsAnagrams(String input_word){
            ArrayList<ArrayList<String>> substringAnagrams = new ArrayList<>();
            int i, j;
            
            for(i = 0; i < input_word.length(); i++){
                for(j = i + 1; j <= input_word.length(); j++){
                    substringAnagrams.add(anagramGenerator(input_word.substring(i, j)));
                }
            }
            return substringAnagrams;
        }
        
        public ArrayList<ArrayList<String>> SubstringAnagramsForWordLenghtGreaterThanThree(String word,
                ArrayList<String> input_arraylist){
            if(word.length() >= MIN_LENGHT && input_arraylist.contains(word)){
                return SubstringsAnagrams(word);
            }else{
                return new ArrayList<>();
            }
        }
}
