package schoolproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DictionaryManagement {
    int wordsBetweenEightAndElevenCharacters();

    ArrayList<String> wordsBetweenThreeAndSevenLetters();

    ArrayList<String> anagramsInAnArrayList(String input_word);

    HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionary();

    List<String> extraction(String input_firstWord, String input_lastWord);

    HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionaryFromAWordToAnother(String input_firstWord,
                                                                                         String input_lastWord);
}
