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
public class WordsInDictionaryManagement {
    	static final int ALPHABET_LEN = 26;
	static int TotalLettersInDictionary = 0;
	static int TotalLettersBetweenTwoWords = 0;

	// calcola ricorrenza delle lettere dell'alfabeto
	public HashMap<Character, Integer> frequencyOfAlphabetLetterInDictionary(ArrayList<String> input_ArrayList) {
            TotalLettersInDictionary = 0;
            HashMap<Character, Integer> LettersAbsoluteFrequency = new HashMap<>();
            for(String s : input_ArrayList){
                s = s.toLowerCase();
                for(int i = 0; i < s.length(); i++, TotalLettersInDictionary++){
                    char c = s.charAt(i);
                    if(LettersAbsoluteFrequency.containsKey(c)){
                        LettersAbsoluteFrequency.put(c, LettersAbsoluteFrequency.get(c) + 1);
                    }else{
                        LettersAbsoluteFrequency.put(c, 1);
                    }
                }
            }
            return LettersAbsoluteFrequency;
	}	
	
	public double percentageFrequency(double i) {
		return i * 100;
	}
	
	// ritorna un array list delle stringhe comprese tra due parole date in input
	public ArrayList<String> extraction(ArrayList<String> input_ArrayList, String input_firstWord, String input_lastWord) {
		ArrayList<String> result = new ArrayList<>();
                String k = null;
		
		for (String x : input_ArrayList)
                    if (x.toLowerCase().compareTo(input_firstWord) >= 0 && x.toLowerCase().compareTo(input_lastWord) <= 0)
                            result.add(x.toLowerCase());
		return result;
	}
	
	// calcola ricorrenza delle lettere dell'alfabeto come percentuale
	// da parola a parola
	public HashMap<Character, Integer> frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(ArrayList<String> input_ArrayList, String input_firstWord, String input_lastWord) {
                TotalLettersBetweenTwoWords = 0;
		// vettore contenente tutte le lettere dell'alfabeto, associate all'indice
		HashMap<Character, Integer> LettersAbsoluteFrequencyMap = new HashMap<>();
		// array di frequenze assolute
		ArrayList<String> WordsBetweenTheTwoWords;
		// controllo se le parole sono contenute nel dizionario
		if (!(input_ArrayList.contains(input_firstWord) && input_ArrayList.contains(input_lastWord))) {
			System.out.println("The words are not in the dictionary");
			return LettersAbsoluteFrequencyMap;
		}
		
		WordsBetweenTheTwoWords = extraction(input_ArrayList, input_firstWord, input_lastWord);
                System.out.println("Topolino hackerino: " + WordsBetweenTheTwoWords);
		char c = 'a';
		for (String element : WordsBetweenTheTwoWords) {
			element = element.toLowerCase();
			for (int k = 0; k < element.length(); k++, WordsInDictionaryManagement.TotalLettersBetweenTwoWords++)
                            c = element.charAt(k);
                            if(LettersAbsoluteFrequencyMap.containsKey(c)){
                                LettersAbsoluteFrequencyMap.put(c, LettersAbsoluteFrequencyMap.get(c) + 1);
                            }else{
                                LettersAbsoluteFrequencyMap.put(c, 1);
                            }
		}
		return LettersAbsoluteFrequencyMap;
	}
}
