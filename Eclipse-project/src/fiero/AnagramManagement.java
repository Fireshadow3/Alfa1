package fiero;

import java.util.ArrayList;

public class AnagramManagement {
	
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
	public int numbersOfAnagramsInAnArrayList(ArrayList<String> input_ArrayList, String input_word) {
		int counter = 0;
		// ArrayList anagrammi
		ArrayList<String> anagrams = anagramGenerator(input_word);
		for (String element : input_ArrayList)
			for (String s : anagrams)
				if (s.equalsIgnoreCase(element))
					counter++;
		return counter;
	}
}
