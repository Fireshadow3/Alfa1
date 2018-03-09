/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boccaccio;

/**
 *
 * @author Fabio
 */
import java.io.*;
import java.util.*;

public class FileManagement {
	static final int MAX_LENGTH = 11;
	static final int MIN_LENGTH = 8;
        static final int MAX_LENGTH_TO_FILE = 7;
	static final int MIN_LENGTH_TO_FILE = 3;
	
	// inserisce nel file di output tutte le parole prima di '/' del file dato in input
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
	
	// ritorna quante parole sono nel dizionario comprese tra 8 e 11 caratteri
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
        
        public ArrayList<String> WriteOnFileWordsBetweenThreeAndSevenLetters(
        String filename, ArrayList<String> input){
            //Apertuta del file, magari in UNICODE --> Decisione da prendere
            ArrayList<String> StringsOnFile = new ArrayList<>();
            char c;
            
            for(String s : input){
                c = s.charAt(0);
                if(!(c >= 65 && c <= 90)){
                    if(s.length() >= MIN_LENGTH_TO_FILE && s.length() <= MAX_LENGTH_TO_FILE){
                        StringsOnFile.add(s);
                    }
                }
            }
            return StringsOnFile;
        }
}

