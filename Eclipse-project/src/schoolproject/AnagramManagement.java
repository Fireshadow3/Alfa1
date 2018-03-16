package schoolproject;

import java.util.ArrayList;

public interface AnagramManagement {
    ArrayList<String> anagramGenerator(String s);

    ArrayList<ArrayList<String>> substringsAnagrams(String input_word);

    ArrayList<ArrayList<String>> substringAnagramsForWordLengthGreaterThanThree(String input_word);
}
