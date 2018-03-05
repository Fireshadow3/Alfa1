package fiero;

import java.io.*;
import java.util.*;

public class CleanFile {
	
	public void cleanAndPrintAfterSlash(String input_file, String output_file) throws IOException {
		// file input
        File fin = new File(input_file);
        // file output
        FileWriter fout = new FileWriter(output_file);
        // oggetto reader con codifica dei caratteri UTF-16
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fin), "UTF-16"));
        // oggetto writer
        BufferedWriter writer = new BufferedWriter(fout);
        // stringa per leggere ogni riga del file
        String line;
        // ArrayList per rimuovere tutte le parole dopo '/'
        ArrayList<String> words = new ArrayList<>();
        // Array finale da stampare su file
        String[] finalWords;
        
        try{
            while ((line = reader.readLine()) != null){
                if ((line.charAt(0) >= 'a' && line.charAt(0) <= 'z') ||
                        (line.charAt(0) >= 'A' && line.charAt(0) <= 'Z'))
                    words.add(line);
            }
        }catch(NullPointerException e){ // fine della lista / lista vuota
            System.out.println("End");
        }
        // inserisco i caratteri nell'array e stampo
        for (String element : words) {
            finalWords = element.split("/");
            writer.write(finalWords[0] + "\n");
        }
        reader.close();
        writer.close();
	}
	
	// genera anagrammi
	public ArrayList<String> anagram(String s) {
		ArrayList<String> finalArrayList = new ArrayList<>();
		if (s.length() <= 1)
			finalArrayList.add(s);
		else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				ArrayList<String> temp = anagram(s.substring(0, i) + s.substring(i + 1));
				for (String element : temp) {
					finalArrayList.add(c + element);
				}
			}
		}
		return finalArrayList;
	}
	
	// numero anagrammi
	public int numbersOfAnagrams(ArrayList<String> list) {
		int counter = 0;
		for (String element : list) {
			counter++;
		}
		return counter;
	}
	
}


%%%%%%%%%%%%%%%%%%%%%%
%        MAIN        %
%%%%%%%%%%%%%%%%%%%%%%



package fiero;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException{
    	CleanFile c = new CleanFile();
    	
    	String in = "list.txt";   // file input
    	String out = "monco.txt"; // file output
    	
    	String m = "cin";
    	
    	c.cleanAndPrintAfterSlash(in, out);
    
    	System.out.println(c.anagram(m));
    	System.out.println(c.numbersOfAnagrams(c.anagram(m)));
    }

}
