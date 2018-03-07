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
    	
    	System.out.println("Ricerca lettere nel dizionario: ");
    	int[] testArray = c.frequencyOfAlphabetLetterInDictionary(strings);
    	char l = 'a';
    	for (int i : testArray) {
    		double percentage1 = c.percentageFrequency((double)i / CleanFile.getLettersInDictionary());
    		System.out.println(l + ": " + i + ";\t\tFrequenza relativa: " + percentage1 / 100 + ";\t Frequenza percentuale: " + percentage1 + "%");
    		l++;
    	}
    	
    	System.out.println();
    	
    	System.out.println("Ricerca lettere fra due parole nel dizionario: ");
    	String first = "cni";
    	String last = "nci";
    	char l1 = 'a';
    	int[] array = c.frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(strings, first, last);
    	for (int s : array) {
    		double percentage2 = c.percentageFrequency(s) / CleanFile.getLettersInDictionaryBetweenTwoWords();
    		System.out.println(l1 + ": " + s + ";\t\tFrequenza relativa: " + percentage2 / 100 + ";\t Frequenza percentuale: " + percentage2 + "%");
    		l1++;
    	}
    }

}
