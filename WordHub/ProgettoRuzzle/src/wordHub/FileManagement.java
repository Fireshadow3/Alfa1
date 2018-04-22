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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManagement implements Constants{    
    /**
     * Nome del file di origine del dizionario 
     */    
    private static String input_file;
    
    /**
     * Nome del file nel quale vengono scritte le parole dopo l'eliminazione delle regole dei suffissi
     */
    private static String output_file;
    
    /**
     * ArrayList di stringhe che contiene tutte le parole in output_file
     */
    private static ArrayList<String> wordsInDictionaryArrayList;
    
    public FileManagement(String input_file, String output_file){
        this.input_file = input_file;
        this.output_file = output_file;
        wordsInDictionaryArrayList = new ArrayList<>();
    }
    
    /**
     * Ottiene la variabile che contiene il path del file di input
     *
     * @return Il dizionario originale
     */
    public static String getInput_file() {
        return input_file;
    }

    /**
     * Ottiene la variabile che contiene il path del file di output
     *
     * @return Il file di output
     */
    public static String getOutput_file() {
        return output_file;
    }   
        
    /**
     * Ottiene l'Arraylist che contiene le parole del dizionario
     *
     * @return L'array di stringhe contenente tutte le parole del dizionario
     */
    public static ArrayList<String> getWordsInDictionaryArrayList() {
        return wordsInDictionaryArrayList;
    }
    
    /**
     * Ottiene l'arraylist che contiene le parole del dizionario prima del carattere '/'
     *
     * @return Un arraylist di stringhe contenente le parole inserite nel file prima del carattere '/'
     */
    public static ArrayList<String> deleteWordsAfterSlashCharacter() {
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
                writer.write(finalWords[0]);
                writer.newLine();
                wordsInDictionaryArrayList.add(finalWords[0]);
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return wordsInDictionaryArrayList;
    }
    
        
    /**
     * Ottiene un intero a 16 bit che rappresenta il numero di parole del dizionario comprese tra 8 e 11 caratteri
     *
     * @return Un intero a 16 bit che rappresenta il numero delle parole comprese tra 8 e 11 caratteri
     */
    public static int wordsBetweenEightAndElevenCharacters() {
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
            System.out.println(e);
        }
        return counter;
    }
    
    /**
     * Ottiene un arraylist che contiene le parole del dizionario comprese tra 3 e 7 caratteri che iniziano con una
     * minuscola, eccetto gli acronimi
     *
     * @return Un arraylist di stringhe contenente le parole comprese tra 3 e 7 lettere
     */
    public static ArrayList<String> wordsBetweenThreeAndSevenLetters() {
        ArrayList<String> stringsInFile = new ArrayList<>();
        char c = 'a';

        for (String s : wordsInDictionaryArrayList) {
            if ((s.length() >= MIN_LENGTH_TO_FILE &&
                    s.length() <= MAX_LENGTH_TO_FILE)){
                c = s.charAt(0);
                if(s.equals(s.toUpperCase()) || !(c >= 65 && c <= 90))
                    stringsInFile.add(s);
            }
        }
        return stringsInFile;
    }
    
    
    /**
     * Scrive su un file tutte le parole del dizionario comprese tra 3 e 7 caratteri che iniziano con una
     * minuscola, eccetto gli acronimi
     *
     * @param output_file File su cui scrivere le parole
     * @return            Un arraylist di stringhe contenente le parole scritte sul file
     */
    public static ArrayList<String> writeOnFileWordsBetweenThreeAndSevenLetters(String output_file) {
        ArrayList<String> stringsInFile = wordsBetweenThreeAndSevenLetters();
        try {
            FileWriter fout = new FileWriter(output_file);
            BufferedWriter writer = new BufferedWriter(fout);

            for (String s : stringsInFile){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return stringsInFile;
    }
}

