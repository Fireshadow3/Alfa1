package fiero;

import java.io.*;
import java.util.*;

public class CleanFile {
	static final int MAX_LENGTH = 11;
	static final int MIN_LENGTH = 8;
	static final int ALPHABET_LEN = 26;
	static int TotalLettersInDictionary = 0;
	static int TotalLettersBetweenTwoWords = 0;
	
	public ArrayList<String> deleteWordsAfterSlashCharacter(String input_file, String output_file) throws IOException {
		// file input
		File fin = new File(input_file);
		// file output
		FileWriter fout = new FileWriter(output_file);
		// oggetto reader con codifica dei caratteri UTF-16
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin), "UTF-16"));
		// oggetto writer
		BufferedWriter writer = new BufferedWriter(fout);
		// stringa per leggere ogni riga del file
		String line;
		// ArrayList per rimuovere tutte le parole dopo '/'
		ArrayList<String> elementsInTheFile = new ArrayList<>();
		// Array finale da stampare su file
		String[] finalWords;
		// output arraylist
		ArrayList<String> finalArrayList = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			if ((line.charAt(0) >= 'a' && line.charAt(0) <= 'z') || (line.charAt(0) >= 'A' && line.charAt(0) <= 'Z'))
				elementsInTheFile.add(line);
		}
		// inserisco i caratteri nell'array e stampo
		for (String element : elementsInTheFile) {
			finalWords = element.split("/");
			writer.write(finalWords[0] + "\n");
			finalArrayList.add(finalWords[0]);
		}
		reader.close();
		writer.close();
		return finalArrayList;
	}
	
	public static int getLettersInDictionary() {
		return CleanFile.TotalLettersInDictionary;
	}
	
	public int wordsBetweenEightAndElevenCharacters(String input_file) throws IOException {
		// file input
		File fin = new File(input_file);
		// oggetto reader con codifica dei caratteri UTF-16
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin), "UTF-16"));
		// stringa per leggere ogni riga del file
		String line;
		// ArrayList per rimuovere tutte le parole dopo '/'
		ArrayList<String> words = new ArrayList<>();
		// Array finale da stampare su file
		String[] finalWords;

		while ((line = reader.readLine()) != null) {
			if ((line.charAt(0) >= 'a' && line.charAt(0) <= 'z') || (line.charAt(0) >= 'A' && line.charAt(0) <= 'Z'))
				words.add(line);
		}
		int counter = 0;
		// controllo la lunghezza della parola
		for (String element : words) {
			finalWords = element.split("/");
			if (finalWords[0].length() <= MAX_LENGTH && finalWords[0].length() >= MIN_LENGTH) {
				counter++;
			}
		}
		reader.close();
		return counter;
	}

	// genera anagrammi
	public ArrayList<String> anagramGenerator(String s) {
		ArrayList<String> finalArrayList = new ArrayList<>();
		if (s.length() <= 1)
			finalArrayList.add(s);
		else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				ArrayList<String> temp = anagramGenerator(s.substring(0, i) + s.substring(i + 1));
				for (String element : temp) {
					finalArrayList.add(c + element);
				}
			}
		}
		return finalArrayList;
	}

	// dato un ArrayList, conta il numero di ricorrenze di ogni anagramma
	public int numbersOfAnagramsInAnArrayList(ArrayList<String> input_ArrayList, String input_word) {
		int counter = 0;
		// ArrayList anagrammi
		ArrayList<String> anagrams = anagramGenerator(input_word);
		for (String element : input_ArrayList) {
			for (String s : anagrams) {
				if (s.equalsIgnoreCase(element))
					counter++;
			}
		}
		return counter;
	}

	// calcola ricorrenza delle lettere dell'alfabeto come percentuale
	public int[] frequencyOfAlphabetLetterInDictionary(ArrayList<String> input_ArrayList) {
		// vettore contenente tutte le lettere dell'alfabeto, associate all'indice
		char[] alphabetArray = new char[ALPHABET_LEN];
		// array di frequenze assolute
		int[] alphabetLettersAbsoluteFrequencyArray = new int[ALPHABET_LEN];
		char letter = 'a';
		// inizializzo l'array con le lettere dell'alfabeto
		for (int i = 0; i < ALPHABET_LEN; i++) {
			alphabetArray[i] = letter;
			letter++;
		}
		for (String element : input_ArrayList) {
			element = element.toLowerCase();
			for (int k = 0; k < element.length(); k++, CleanFile.TotalLettersInDictionary++) {
				switch(element.charAt(k)) {
				case 'a':
					alphabetLettersAbsoluteFrequencyArray[0]++;
					break;
				case 'b':
					alphabetLettersAbsoluteFrequencyArray[1]++;
					break;
				case 'c':
					alphabetLettersAbsoluteFrequencyArray[2]++;
					break;
				case 'd':
					alphabetLettersAbsoluteFrequencyArray[3]++;
					break;
				case 'e':
					alphabetLettersAbsoluteFrequencyArray[4]++;
					break;
				case 'f':
					alphabetLettersAbsoluteFrequencyArray[5]++;
					break;
				case 'g':
					alphabetLettersAbsoluteFrequencyArray[6]++;
					break;
				case 'h':
					alphabetLettersAbsoluteFrequencyArray[7]++;
					break;
				case 'i':
					alphabetLettersAbsoluteFrequencyArray[8]++;
					break;
				case 'j':
					alphabetLettersAbsoluteFrequencyArray[9]++;
					break;
				case 'k':
					alphabetLettersAbsoluteFrequencyArray[10]++;
					break;
				case 'l':
					alphabetLettersAbsoluteFrequencyArray[11]++;
					break;
				case 'm':
					alphabetLettersAbsoluteFrequencyArray[12]++;
					break;
				case 'n':
					alphabetLettersAbsoluteFrequencyArray[13]++;
					break;
				case 'o':
					alphabetLettersAbsoluteFrequencyArray[14]++;
					break;
				case 'p':
					alphabetLettersAbsoluteFrequencyArray[15]++;
					break;
				case 'q':
					alphabetLettersAbsoluteFrequencyArray[16]++;
					break;
				case 'r':
					alphabetLettersAbsoluteFrequencyArray[17]++;
					break;
				case 's':
					alphabetLettersAbsoluteFrequencyArray[18]++;
					break;
				case 't':
					alphabetLettersAbsoluteFrequencyArray[19]++;
					break;
				case 'u':
					alphabetLettersAbsoluteFrequencyArray[20]++;
					break;
				case 'v':
					alphabetLettersAbsoluteFrequencyArray[21]++;
					break;
				case 'w':
					alphabetLettersAbsoluteFrequencyArray[22]++;
					break;
				case 'x':
					alphabetLettersAbsoluteFrequencyArray[23]++;
					break;
				case 'y':
					alphabetLettersAbsoluteFrequencyArray[24]++;
					break;
				case 'z':
					alphabetLettersAbsoluteFrequencyArray[25]++;
					break;
				default:
					break;
				}
			}
		}
		return alphabetLettersAbsoluteFrequencyArray;
	}	
	
	public double percentageFrequency(double i) {
		return i * 100;
	}
	
	public ArrayList<String> extraction(ArrayList<String> input_ArrayList, String input_firstWord, String input_lastWord) {
		ArrayList<String> result = new ArrayList<>();
		
		for (String k : input_ArrayList) {
			if (k.compareTo(input_firstWord) >= 0 && k.compareTo(input_lastWord) <= 0) {
				result.add(k);
			}
		}
		return result;
	}
	
	
	// calcola ricorrenza delle lettere dell'alfabeto come percentuale
	// da parola a parola
	public int[] frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(ArrayList<String> input_ArrayList, String input_firstWord, String input_lastWord) {
		// vettore contenente tutte le lettere dell'alfabeto, associate all'indice
		char[] alphabetArray = new char[ALPHABET_LEN];
		// array di frequenze assolute
		int[] alphabetLettersAbsoluteFrequencyArray = new int[ALPHABET_LEN];
		ArrayList<String> charactersBetweenTheTwoWords;
		char letter = 'a';
		// inizializzo l'array con le lettere dell'alfabeto
		for (int i = 0; i < ALPHABET_LEN; i++) {
			alphabetArray[i] = letter;
			letter++;
		}
		// controllo se le parole sono contenute nel dizionario
		if (!(input_ArrayList.contains(input_firstWord) && input_ArrayList.contains(input_lastWord))) {
			System.out.println("The words are not in the dictionary");
			return alphabetLettersAbsoluteFrequencyArray;
		}
		
		charactersBetweenTheTwoWords = extraction(input_ArrayList, input_firstWord, input_lastWord);
		
		for (String element : charactersBetweenTheTwoWords) {
			element = element.toLowerCase();
				for(int k = 0; k < element.length(); k++, CleanFile.TotalLettersInDictionary++) {
					switch(element.charAt(k)) {
					case 'a':
						alphabetLettersAbsoluteFrequencyArray[0]++;
						break;
					case 'b':
						alphabetLettersAbsoluteFrequencyArray[1]++;
						break;
					case 'c':
						alphabetLettersAbsoluteFrequencyArray[2]++;
						break;
					case 'd':
						alphabetLettersAbsoluteFrequencyArray[3]++;
						break;
					case 'e':
						alphabetLettersAbsoluteFrequencyArray[4]++;
						break;
					case 'f':
						alphabetLettersAbsoluteFrequencyArray[5]++;
						break;
					case 'g':
						alphabetLettersAbsoluteFrequencyArray[6]++;
						break;
					case 'h':
						alphabetLettersAbsoluteFrequencyArray[7]++;
						break;
					case 'i':
						alphabetLettersAbsoluteFrequencyArray[8]++;
						break;
					case 'j':
						alphabetLettersAbsoluteFrequencyArray[9]++;
						break;
					case 'k':
						alphabetLettersAbsoluteFrequencyArray[10]++;
						break;
					case 'l':
						alphabetLettersAbsoluteFrequencyArray[11]++;
						break;
					case 'm':
						alphabetLettersAbsoluteFrequencyArray[12]++;
						break;
					case 'n':
						alphabetLettersAbsoluteFrequencyArray[13]++;
						break;
					case 'o':
						alphabetLettersAbsoluteFrequencyArray[14]++;
						break;
					case 'p':
						alphabetLettersAbsoluteFrequencyArray[15]++;
						break;
					case 'q':
						alphabetLettersAbsoluteFrequencyArray[16]++;
						break;
					case 'r':
						alphabetLettersAbsoluteFrequencyArray[17]++;
						break;
					case 's':
						alphabetLettersAbsoluteFrequencyArray[18]++;
						break;
					case 't':
						alphabetLettersAbsoluteFrequencyArray[19]++;
						break;
					case 'u':
						alphabetLettersAbsoluteFrequencyArray[20]++;
						break;
					case 'v':
						alphabetLettersAbsoluteFrequencyArray[21]++;
						break;
					case 'w':
						alphabetLettersAbsoluteFrequencyArray[22]++;
						break;
					case 'x':
						alphabetLettersAbsoluteFrequencyArray[23]++;
						break;
					case 'y':
						alphabetLettersAbsoluteFrequencyArray[24]++;
						break;
					case 'z':
						alphabetLettersAbsoluteFrequencyArray[25]++;
						break;
					default:
						break;
					}	
			}
		}
		return alphabetLettersAbsoluteFrequencyArray;
	}
	
}
