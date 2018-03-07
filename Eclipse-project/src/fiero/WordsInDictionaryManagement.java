package fiero;

import java.util.ArrayList;

public class WordsInDictionaryManagement {
	static final int ALPHABET_LEN = 26;
	static int TotalLettersInDictionary = 0;
	static int TotalLettersBetweenTwoWords = 0;

	// calcola ricorrenza delle lettere dell'alfabeto
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
			for (int k = 0; k < element.length(); k++, WordsInDictionaryManagement.TotalLettersInDictionary++)
				switchCaseAlphabetFrequency(element, k, alphabetLettersAbsoluteFrequencyArray);
		}
		return alphabetLettersAbsoluteFrequencyArray;
	}	
	
	public double percentageFrequency(double i) {
		return i * 100;
	}
	
	// ritorna un array list delle stringhe comprese tra due parole date in input
	public ArrayList<String> extraction(ArrayList<String> input_ArrayList, String input_firstWord, String input_lastWord) {
		ArrayList<String> result = new ArrayList<>();
		
		for (String k : input_ArrayList)
			if (k.compareTo(input_firstWord) >= 0 && k.compareTo(input_lastWord) <= 0)
				result.add(k);
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
			for (int k = 0; k < element.length(); k++, WordsInDictionaryManagement.TotalLettersBetweenTwoWords++)
				switchCaseAlphabetFrequency(element, k, alphabetLettersAbsoluteFrequencyArray);
		}
		return alphabetLettersAbsoluteFrequencyArray;
	}
	
	// switch - case per trovare la frequenza delle lettere dell'alfabeto nel dizionario
	public void switchCaseAlphabetFrequency(String element, int k, int[] alphabetLettersAbsoluteFrequencyArray) {
		switch (element.charAt(k)) {
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
	
	
	//#TODO FUNZIONA MA SOLO SE UNA PAROLA NON HA COME INIZIALE PER DUE VOLTE LA STESSA LETTERA
	//		DA SISTEMARE
	
	/**
	 * Dato un ArrayList di stringhe ed una stringa cerca la stringa tramite ricerca binaria nell'array, ritornando la posizione.
	 * @param strings ArrayList in cui cercare la parola
	 * @param wordToFind parola da trovare
	 * @return posizione della parola nell'ArrayList
	 * @throws StringNotFoundException nel caso non riesca a trovare la parola
	 */
	public int findWordInDictionaryUsingBinarySearch(ArrayList<String> strings, String wordToFind) throws StringNotFoundException {
		//Converto strings in un array
		//String a = strings.toArray(); SBAGLIATO
		//https://stackoverflow.com/questions/5061640/make-arraylist-toarray-return-more-specific-types
		String[] a = strings.toArray(new String[strings.size()]);
		
		//Comincio una ricerca binaria iterativa
		int init = 0, end, middle;
		
		//Imposto la fine a lunghezza array - 1
		end = a.length;
		
		//Finchè start e end non si incontrano
		while(init <= end){
			//Imposto il centro a (inizio+fine)/2
			middle = (init + end) / 2;
			//Se il centro corrisponde alla stringa allora ho finito la ricerca
			if (a[middle].equals(wordToFind))
				// return a[center];
				return middle;
			//Se la parola al centro viene prima della parola da cercare sposto l'inizio a centro
			if (a[middle].charAt(0) < wordToFind.charAt(0))
				init = middle + 1;
			//Se la parola al centro viene dopo della parola da cercare sposto la fine a centro
			else
				end = middle - 1;
		}
		//Se arrivo qui la stringa non è stata trovata e sollevo quindi un eccezione
		throw new StringNotFoundException("String not found during binary search");
	}
	
}
