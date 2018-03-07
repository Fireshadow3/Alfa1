package fiero;

import java.io.*;
import java.util.ArrayList;

public class FileManagement {
	static final int MAX_LENGTH = 11;
	static final int MIN_LENGTH = 8;
	
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
}
