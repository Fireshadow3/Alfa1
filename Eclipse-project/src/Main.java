package fiero;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException{
    	CleanFile c = new CleanFile();
    	ArrayList<String> strings;
    	ArrayList<String> testArrayList;
    	
    	String in = "list.txt";   // file input
    	String out = "monco.txt"; // file output
    	
    	String m = "cin";
    	
    	testArrayList = c.deleteWordsAfterSlashCharacter(in, out);
    	
    	strings = c.anagramGenerator(m);
    	System.out.println(strings);
    	System.out.println(c.numbersOfAnagramsInAnArrayList(testArrayList, m));
    	System.out.println("Parole comprese tra 8 e 11 caratteri: " + c.wordsBetweenEightAndElevenCharacters(in));
    	System.out.println();
    	
    	int[] testArray = c.frequencyOfAlphabetLetterInDictionary(strings);
    	char l = 'a';
    	/*
    	for (int i : testArray) {
    		double percentage = c.percentageFrequency((double)i / CleanFile.getManz());
    		System.out.println(l + ": " + i + ";\t\tFrequenza relativa: " + percentage / 100 + ";\t Frequenza percentuale: " + percentage + "%");
    		l++;
    	}
    	*/
    	String first = "cni";
    	String last = "nci";
    	int[] array = c.frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(strings, first, last);
    	for (int s : array) {
    		System.out.println("Lettera: " + l + " -> " + s);
    		l++;
    	}
    	
    }

}
