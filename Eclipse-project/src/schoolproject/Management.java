package schoolproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Management implements Constants, Getters {
    static int TotalLettersInDictionary = 0;
    static int TotalLettersBetweenTwoWords = 0;
    private String input_file;
    private String output_file;
    private ArrayList<String> wordsInDictionaryArrayList;
    private ArrayList<String> anagramsInDictionary;
    private ArrayList<String> anagrams;
    private ArrayList<ArrayList<String>> substringAnagrams;
    // vettore contenente tutte le lettere dell'alfabeto, associate all'indice
    HashMap<Character, Integer> lettersAbsoluteFrequencyMap;
    HashMap<Character, Integer> freqBetweenTwoWords;

    public Management(String _input_file, String _output_file) {
        this.input_file = _input_file;
        this.output_file = _output_file;
        wordsInDictionaryArrayList = new ArrayList<>();
        anagramsInDictionary = new ArrayList<>();
        anagrams = new ArrayList<>();
        substringAnagrams = new ArrayList<>();
        lettersAbsoluteFrequencyMap = new HashMap<>();
        freqBetweenTwoWords = new HashMap<>();
    }

    /**
     * Ottiene l'ArrayList che contiene le parole del dizionario
     *
     * @return L'array di stringhe contenente tutte le parole del dizionario
     */
    public ArrayList<String> getWordsInDictionaryArrayList() {
        return wordsInDictionaryArrayList;
    }

    /**
     * Ottiene l'ArrayList che contiene gli anagrammi di una parola
     * nel dizionario
     *
     * @return L'array di stringhe contenente tutti gli anagrammi di
     * una parola nel dizionario
     */
    public ArrayList<String> getAnagramsInDictionary() {
        return anagramsInDictionary;
    }

    /**
     * Ottiene un ArrayList degli anagrammi generati
     *
     * @return arraylist di stringhe
     */
    public ArrayList<String> getAnagrams() {
        return anagrams;
    }

    /**
     * Ottiene gli anagrammi delle sottostringhe degli anagrammi di una parola generati dal metodo substringsAnagrams
     *
     * @return arraylist di arralist di stringhe
     */
    public ArrayList<ArrayList<String>> getSubstringAnagrams() {
        return substringAnagrams;
    }

    /**
     * Ottiene un'hashmap con la percentuale assoluta assegnata ad ogni lettera
     *
     * @return un'hashmap di Integer con chiave Character
     */
    public HashMap<Character, Integer> getLettersAbsoluteFrequencyMap() {
        return lettersAbsoluteFrequencyMap;
    }

    /**
     * Ottiene l'ArrayList che contiene le parole del dizionario
     *
     * @return L'array di stringhe contenente tutte le parole del dizionario
     */
    public HashMap<Character, Integer> getFreqBetweenTwoWords() {
        return freqBetweenTwoWords;
    }

    /**
     * Ottiene la variabile che contiene il path del file di input
     *
     * @return Il dizionario originale
     */
    public String getInput_file() {
        return input_file;
    }

    /**
     * Ottiene la variabile che contiene il path del file di output
     *
     * @return Il file di output
     */
    public String getOutput_file() {
        return output_file;
    }


    /**
     * Ottiene l'ArrayList che contiene le parole del dizionario prima del carattere '/'
     *
     * @return Un arraylist di stringhe contenente le parole inserite nel file prima del carattere '/'
     */
    public ArrayList<String> deleteWordsAfterSlashCharacter() {
        // stringa per leggere ogni riga del file
        String line;
        // ArrayList per rimuovere tutte le parole dopo '/'
        ArrayList<String> elementsInFile = new ArrayList<>();
        // Array finale da stampare su file
        String[] finalWords;
        // output arraylist
        try {
            // file input
            File fin = new File(getInput_file());
            // file output
            FileWriter fout = new FileWriter(getOutput_file());
            // oggetto writer
            BufferedWriter writer = new BufferedWriter(fout);
            // oggetto reader con codifica dei caratteri UTF-16
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin), ENCODING));
            while ((line = reader.readLine()) != null) {
                if ((line.charAt(0) >= 'a' && line.charAt(0) <= 'z') ||
                        (line.charAt(0) >= 'A' && line.charAt(0) <= 'Z'))
                    elementsInFile.add(line);
            }
            // inserisco i caratteri nell'array e stampo
            for (String element : elementsInFile) {
                finalWords = element.split("/");
                writer.write(finalWords[0] + "\n");
                getWordsInDictionaryArrayList().add(finalWords[0]);
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsInDictionaryArrayList;
    }

    /**
     * Ottiene un intero a 16 bit che rappresenta il numero di parole del dizionario comprese tra 8 e 11 caratteri
     *
     * @return Un intero a 16 bit che rappresenta il numero delle parole comprese tra 8 e 11 caratteri
     */
    public int wordsBetweenEightAndElevenCharacters() {
        // file input
        File fin = new File(getOutput_file());
        // stringa per leggere ogni riga del file
        String line;
        // ArrayList per rimuovere tutte le parole dopo '/'
        ArrayList<String> words = new ArrayList<>();
        // Array finale da stampare su file
        String[] finalWords;
        int counter = 0;
        try {
            // oggetto reader con codifica dei caratteri UTF-16
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin), ENCODING));
            while ((line = reader.readLine()) != null) {
                if ((line.charAt(0) >= 'a' && line.charAt(0) <= 'z') ||
                        (line.charAt(0) >= 'A' && line.charAt(0) <= 'Z'))
                    words.add(line);
            }
            // controllo la lunghezza della parola
            for (String element : words) {
                finalWords = element.split("/");
                if (finalWords[0].length() <= MAX_LENGTH && finalWords[0].length() >= MIN_LENGTH)
                    counter++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    /**
     * Ottiene un arraylist che contiene le parole del dizionari comprese tra 8 e 7 caratteri che iniziano con una
     * minuscola, eccetto gli acronimi
     *
     * @return Un arraylist di stringhe contenente le parole comprese tra 3 e 7 lettere
     */
    public ArrayList<String> wordsBetweenThreeAndSevenLetters() {
        ArrayList<String> stringsInFile = new ArrayList<>();
        char c;

        for (String s : getWordsInDictionaryArrayList()) {
            if (s.equals(s.toUpperCase()))
                stringsInFile.add(s);
            else {
                c = s.charAt(0);
                if (!(c >= 65 && c <= 90))
                    if (s.length() >= MIN_LENGTH_TO_FILE &&
                            s.length() <= MAX_LENGTH_TO_FILE)
                        stringsInFile.add(s);
            }
        }
        return stringsInFile;
    }

    /**
     * Scrive su un file tutte le parole del dizionario comprese tra 3 e 7 lettere che non iniziano con una maiuscola,
     * fatta eccezione per gli acronimi
     *
     * @return Un arraylist di stringhe contenente le parole scritte sul file
     */
    public ArrayList<String> writeOnFileWordsBetweenThreeAndSevenLetters(String output_file) {
        ArrayList<String> stringsInFile = wordsBetweenThreeAndSevenLetters();
        try {
            FileWriter fout = new FileWriter(output_file);
            BufferedWriter writer = new BufferedWriter(fout);

            for (String s : stringsInFile)
                writer.write(s + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringsInFile;
    }

    /**
     * Genera tutti gli anagrammi di una stringa data in input
     *
     * @param s Parola di cui generare gli anagrammi
     * @return  Un arraylist di stringhe contenente gli anagrammi della parola esaminata
     */
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

    /**
     * Conta il numero di ricorrenze di ogni anagramma di una stringa data in input nel file dizionario
     *
     * @param input_word stringa di cui generare anagrammi
     * @return           Un arraylist di stringhe contenente le parole scritte sul file
     */
    public ArrayList<String> anagramsInAnArrayList(String input_word) {
        ArrayList<String> result = new ArrayList<>();
        // ArrayList anagrammi
        ArrayList<String> anagrams = anagramGenerator(input_word);
        for (String s : getWordsInDictionaryArrayList())
            for (String s1 : anagrams)
                if (s.equalsIgnoreCase(s1))
                    result.add(s);
        return result;
    }

    /**
     * Genera tutti gli anagrammi di ogni sottostringa della parola data in input
     *
     * @param input_word stringa di cui generare gli anagrammi
     * @return           Un arraylist di stringhe contenente le parole scritte sul file
     */
    public ArrayList<ArrayList<String>> substringsAnagrams(String input_word) {
        ArrayList<ArrayList<String>> substringAnagrams = new ArrayList<>();
        int i, j;

        for (i = 0; i < input_word.length(); i++)
            for (j = i + 1; j <= input_word.length(); j++)
                substringAnagrams.add(anagramGenerator(input_word.substring(i, j)));
        return substringAnagrams;
    }

    /**
     * Genera gli anagrammi delle sottostringhe di una parola di lunghezza uguale o superiore a 3 lettere
     *
     * @param word stringa di cui generare gli anagrammi
     * @return     Un arraylist di stringhe contenente le parole scritte sul file
     */
    public ArrayList<ArrayList<String>> substringAnagramsForWordLengthGreaterThanThree(String word) {
        if (word.length() >= MIN_LENGHT_ANAG && getWordsInDictionaryArrayList().contains(word))
            return substringsAnagrams(word);
        else
            return new ArrayList<>();
    }

    /**
     * Calcola ricorrenza delle lettere nel dizionario
     *
     * @return un'hashmap con le varie frequenze delle lettere
     */
    public HashMap<Character, Integer> frequencyOfAlphabetLettersInDictionary() {
        TotalLettersInDictionary = 0;
        HashMap<Character, Integer> LettersAbsoluteFrequency = new HashMap<>();
        for (String s : getWordsInDictionaryArrayList()) {
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++, TotalLettersInDictionary++) {
                char c = s.charAt(i);
                if (LettersAbsoluteFrequency.containsKey(c))
                    LettersAbsoluteFrequency.put(c, LettersAbsoluteFrequency.get(c) + 1);
                else
                    LettersAbsoluteFrequency.put(c, 1);
            }
        }
        return LettersAbsoluteFrequency;
    }

    /**
     * Ritorna il prodotto del numero in input per 100
     *
     * @param i numero sul quale eseguire la moltiplicazione
     * @return  il prodotto tra il numero in input e 100
     */
    public double percentageFrequency(double i) {
        return i * 100;
    }

    /**
     * Ritorna un array list delle stringhe comprese tra due parole date in input
     *
     * @param prima parola
     * @param ultima parola
     * @return una lista di stringhe contenente le parole nel dizionario comprese tra parola e parola
     */
    public List<String> extraction(String input_firstWord, String input_lastWord) {
        int fromIndex = getWordsInDictionaryArrayList().indexOf(input_firstWord),
                toIndex = getWordsInDictionaryArrayList().indexOf(input_lastWord);
        return getWordsInDictionaryArrayList().subList(fromIndex, toIndex);
    }

    /**
     * Calcola la ricorrenza delle lettere dell'alfabeto come percentuale, da una parola ad un'altra
     *
     * @param input_firstWord parola da cui iniziare la ricerca
     * @param input_lastWord  parola che identifica la fine della ricerca
     * @return                un'hashmap con la frequenza delle lettere
     */
    public HashMap<Character, Integer> frequencyOfAlphabetLetterInDictionaryFromAWordToAnother(String input_firstWord,
                                                                                               String input_lastWord) {
        TotalLettersBetweenTwoWords = 0;
        // array di frequenze assolute
        List<String> wordsBetweenTwoWords;
        // controllo se le parole sono contenute nel dizionario
        if (!(getWordsInDictionaryArrayList().contains(input_firstWord) &&
                getWordsInDictionaryArrayList().contains(input_lastWord))) {
            System.out.println("The words are not in the dictionary");
            return getFreqBetweenTwoWords();
        }

        wordsBetweenTwoWords = extraction(input_firstWord, input_lastWord);
        char c = 'a';
        for (String element : wordsBetweenTwoWords) {
            element = element.toLowerCase();
            for (int k = 0; k < element.length(); k++, Management.TotalLettersBetweenTwoWords++) {
                c = element.charAt(k);
                if (getFreqBetweenTwoWords().containsKey(c))
                    getFreqBetweenTwoWords().put
                            (c, getFreqBetweenTwoWords().get(c) + 1);
                else
                    getFreqBetweenTwoWords().put(c, 1);
            }
        }
        return getFreqBetweenTwoWords();
    }
}
