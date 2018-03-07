package fiero;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException{
    	FileManagement file = new FileManagement();
    	AnagramManagement anag = new AnagramManagement();
    	WordsInDictionaryManagement words = new WordsInDictionaryManagement();
    	ArrayList<String> strings;
    	ArrayList<String> testArrayList;
    	
    	String in = "list.txt";   // file input
    	String out = "monco.txt"; // file output
    	
    	String m = "cin";
    	
    	testArrayList = file.deleteWordsAfterSlashCharacter(in, out);
    	
    	strings = anag.anagramGenerator(m);
    	System.out.println(strings);
    	System.out.println(anag.numbersOfAnagramsInAnArrayList(testArrayList, m));
    	System.out.println("Parole comprese tra 8 e 11 caratteri: " + file.wordsBetweenEightAndElevenCharacters(in));
    	System.out.println();
    	
    	System.out.println("Ricerca lettere nel dizionario: ");
    	int[] testArray = words.frequencyOfAlphabetLetterInDictionary(strings);
    	char l = 'a';
    	for (int i : testArray) {
    		double percentage1 = words.percentageFrequency((double)i / WordsInDictionaryManagement.TotalLettersInDictionary);
    		System.out.println(l + ": " + i + ";\t\tFrequenza relativa: " + percentage1 / 100 + ";\t Frequenza percentuale: " + percentage1 + "%");
    		l++;
    	}
    	
    	System.out.println();
    	
    	System.out.println("Ricerca lettere fra due parole nel dizionario: ");
    	String first = "cni";
    	String last = "nci";
    	char l1 = 'a';
    	int[] array = words.frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(strings, first, last);
    	for (int s : array) {
    		double percentage2 = words.percentageFrequency(s) / WordsInDictionaryManagement.TotalLettersBetweenTwoWords;
    		System.out.println(l1 + ": " + s + ";\t\tFrequenza relativa: " + percentage2 / 100 + ";\t Frequenza percentuale: " + percentage2 + "%");
    		l1++;
    	}
    }

}
