/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

/**
 *
 * @author Fabio
 */
import java.util.ArrayList;

public interface FileManagement {
    ArrayList<String> deleteWordsAfterSlashCharacter();
    
    int wordsBetweenEightAndElevenCharacters();
    
    ArrayList<String> wordsBetweenThreeAndSevenLetters();

    ArrayList<String> writeOnFileWordsBetweenThreeAndSevenLetters(String output_file);
    
}

