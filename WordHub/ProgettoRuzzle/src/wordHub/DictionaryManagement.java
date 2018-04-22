/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordHub;

/**
 *
 * @author Fabio
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DictionaryManagement implements Constants{
    static FileManagement file;
    /**
     * Contatore del numero totale di lettere contenute nel dizionario
     */
    static int TotalLettersInDictionary = 0;
    
    /**
     * Contatore del numero di lettere contenute nel dizionario tra 2 parole  
     */
    static int TotalLettersBetweenTwoWords = 0;
    
    /**
     * Lista degli anagrammi di una parola 
     */
    private static ArrayList<String> anagrams;
    
    /**
     * Lista delle parole del dizionario lunghe 7 caratteri 
     */
    private static ArrayList<String> wordsLongSeven;
    
    /**
     * HashMap contenente tutte le lettere delle parole nel dizionario,
     * con associata la frequenza con cui esse ricorrono 
     */
    static HashMap<Character, Integer> lettersAbsoluteFrequencyMap;
    
    /**
     * HashMap contenente tutte le lettere delle parole nel dizionario tra due parole,
     * con associata la frequenza con cui esse ricorrono in tale sottolista 
     */
    static HashMap<Character, Integer> freqBetweenTwoWords;
    
    public DictionaryManagement(String input_file, String output_file){
        file = new FileManagement(input_file, output_file);
        anagrams = new ArrayList<>();
        wordsLongSeven = new ArrayList<>();
        lettersAbsoluteFrequencyMap = new HashMap<>();
        freqBetweenTwoWords = new HashMap<>();
    }
    
    /**
     * Ottiene l'oggetto FileManagement, da cui reperire il dizionario
     * @return file manager
     */
    public static FileManagement getFile(){
        return file;
    }
    
    /**
     * Ottiene un Arraylist degli anagrammi generati
     *
     * @return Arraylist di stringhe
     */
    public static ArrayList<String> getAnagrams() {
        return anagrams;
    }
    
    /**
     * Ottiene l'Arraylist che contiene le parole del dizionario con più di 7
     * caratteri
     *
     * @return L'array di stringhe contenente tutte le parole del dizionario con
     * più di 7 caratteri
     */
    public static ArrayList<String> getWordsLongSeven(){
        return wordsLongSeven;
    }
    
    /**
     * Ottiene una hashmap con la percentuale assoluta assegnata ad ogni lettera
     *
     * @return Una hashmap di Integer con chiave Character
     */
    public static HashMap<Character, Integer> getLettersAbsoluteFrequencyMap() {
        return lettersAbsoluteFrequencyMap;
    }

    /**
     * Ottiene l'arraylist che contiene le parole del dizionario
     *
     * @return L'array di stringhe contenente tutte le parole del dizionario
     */
    public static HashMap<Character, Integer> getFreqBetweenTwoWords() {
        return freqBetweenTwoWords;
    }
    
    /**
     * Genera tutti gli anagrammi di una stringa data in input
     *
     * @param s Stringa di cui generare gli anagrammi
     * @return  Un arraylist di stringhe contenente gli anagrammi della parola esaminata
     */
    public static ArrayList<String> anagramGenerator(String s) {
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
    
    /**
     * Ottiene le ricorrenze di ogni anagramma di una stringa data in input nel file dizionario
     *
     * @param input_word Stringa di cui generare anagrammi
     * @return           Un arraylist di stringhe contenente le parole scritte sul file
     */
    public static ArrayList<String> anagramsInAnArrayList(String input_word) {
        ArrayList<String> result = new ArrayList<>();
        // ArrayList anagrammi
        anagrams = anagramGenerator(input_word);
        for (String s : file.getWordsInDictionaryArrayList())
            for (String s1 : anagrams)
                if (s.equalsIgnoreCase(s1))
                    result.add(s);
        return result;
    }
    
    /**
     * Calcola ricorrenza delle lettere nel dizionario
     *
     * @return Una hashmap con le varie frequenze delle lettere
     */
    public static HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionary() {
        TotalLettersInDictionary = 0;
        for (String s : file.getWordsInDictionaryArrayList()) {
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++, TotalLettersInDictionary++) {
                char c = s.charAt(i);
                if (lettersAbsoluteFrequencyMap.containsKey(c))
                    lettersAbsoluteFrequencyMap.put(c, lettersAbsoluteFrequencyMap.get(c) + 1);
                else
                    lettersAbsoluteFrequencyMap.put(c, 1);
            }
        }
        return lettersAbsoluteFrequencyMap;
    }
    
    /**
     * Ritorna un array list delle stringhe comprese tra due parole date in input
     *
     * @param input_firstWord Stringa da cui iniziare
     * @param input_lastWord  Stringa a cui finire
     * @return                Una lista di stringhe contenente le parole nel dizionario comprese tra parola e parola
     */
    public static List<String> extraction(String input_firstWord, String input_lastWord) {
        int fromIndex = file.getWordsInDictionaryArrayList().indexOf(input_firstWord),
                toIndex = file.getWordsInDictionaryArrayList().indexOf(input_lastWord);
        return file.getWordsInDictionaryArrayList().subList(fromIndex, toIndex + 1);
    }
    
    /**
     * Calcola la ricorrenza delle lettere dell'alfabeto come percentuale, da una parola ad un'altra
     *
     * @param input_firstWord Stringa da cui iniziare la ricerca
     * @param input_lastWord  Stringa che identifica la fine della ricerca
     * @return                Una hashmap con la frequenza delle lettere
     */
    public static HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionaryFromAWordToAnother(String input_firstWord,
                                                                                                String input_lastWord) {
        TotalLettersBetweenTwoWords = 0;
        // array di frequenze assolute
        List<String> wordsBetweenTwoWords;
        // controllo se le parole sono contenute nel dizionario
        if (!(file.getWordsInDictionaryArrayList().contains(input_firstWord) &&
                file.getWordsInDictionaryArrayList().contains(input_lastWord))) {
            System.out.println("The words are not in the dictionary");
            return freqBetweenTwoWords;
        }

        wordsBetweenTwoWords = extraction(input_firstWord, input_lastWord);
        char c = 'a';
        for (String element : wordsBetweenTwoWords) {
            element = element.toLowerCase();
            for (int k = 0; k < element.length(); k++, DictionaryManagement.TotalLettersBetweenTwoWords++) {
                c = element.charAt(k);
                if (freqBetweenTwoWords.containsKey(c))
                    freqBetweenTwoWords.put
                            (c, freqBetweenTwoWords.get(c) + 1);
                else
                    freqBetweenTwoWords.put(c, 1);
            }
        }
        return freqBetweenTwoWords;
    }
    
        /**
    * Soluzione alternativa(e a mio parere più conveniente) per l'inizializzazione
    * del gioco: trovo tutte le parole del dizionario che hanno sette lettere
    * (inserendole in una linked list), sorteggio una parola a caso e ne distribuisco 
    * le lettere casualmente sui pulsanti(vedere versione alternativa di 
    * buttonInitialization)
    *
    * @return parola estratta casualmente
    */
    public String startingWord(){
        Random rand = new Random();
        for(String s : file.getWordsInDictionaryArrayList())
            if(s.length() == MAX_LENGTH_TO_FILE)
                wordsLongSeven.add(s);
        int index = rand.nextInt(wordsLongSeven.size());
        String x = wordsLongSeven.get(index);
        return x;
    }
    
    public static String wordGenerationFromRandomLetters(String first, String second, String third,
            String fourth, String fifth, String sixth, String seventh){
        return "" + first + second + third + fourth + fifth + sixth + seventh;
    }
    
    public static ArrayList<String> wordOfSevenCharacterInDictionary(String input_word) {
        return anagramsInAnArrayList(input_word);
    }
}

