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
public interface Constants {
    /**
     * Massima lunghezza delle parole analizzate
     * {@link FileManagement#wordsBetweenEightAndElevenCharacters()} 
     */
    final int MAX_LENGTH = 11;
    
    /**
     * Minima lunghezza delle parole analizzate
     * {@link FileManagement#wordsBetweenEightAndElevenCharacters()} 
     */
    static final int MIN_LENGTH = 8;
    
    /**
     * Massima lunghezza delle parole analizzate
     * {@link FileManagement#wordsBetweenThreeAndSevenLetters()}
     * {@link DictionaryManagement#startingWord()}
     */
    static final int MAX_LENGTH_TO_FILE = 7;
    
    /**
     * Minima lunghezza delle parole analizzate
     * {@link FileManagement#wordsBetweenThreeAndSevenLetters()} 
     */
    static final int MIN_LENGTH_TO_FILE = 3;
    
    /**
     * Encoding utilizzato dal progetto per leggere e scrivere su file
     * {@link FileManagement#deleteWordsAfterSlashCharacter()}
     * {@link FileManagement#wordsBetweenEightAndElevenCharacters()}
     * {@link Statistics#readStatsFromFile(String, Statistics)}
    */
    static final String ENCODING = "ISO-8859-1";
    
    /**
     * Minima lunghezza delle parole analizzate del dizionario
     * {@link AnagramManagement#substringAnagramsForWordLengthGreaterThanThree(String)}
     */
    static final int MIN_LENGTH_ANAG = 3;
    
    /**
     * Colore di sfondo di ogni bottone una volta selezionato
     * {@link FXMLDocumentController#handleGeneralButtonAction(Button)}
     * {@link FXMLDocumentController#handleGenericKeyTyped(KeyEvent)}
     * {@link FXMLDocumentController#keyPressed(KeyEvent)}
     * {@link FXMLDocumentController#handleBackSpaceKeyTyped(KeyEvent)}
     */
    static final String BUTTON_COLOR = "-fx-background-color: #ff471a";
    
    /**
     * Nome del file del dizionario di parole italiane
     * {@link FXMLDocumentController#changeLanguage()}
     * {@link FXMLDocumentController#initialize(URL, ResourceBundle)}
     */
    static final String ITA_DIC = "in.txt";
    
    /**
     * Nome del file del dizionario di parole inglesi
     * {@link FXMLDocumentController#changeLanguage()}}
     */
    static final String ENG_DIC = "en.txt";
    
    /**
     * Nome del file dove scrivere il record
     * {@link FXMLDocumentController#updateBestScore(int)}
     * {@link FXMLDocumentController#loadBestScore()}
     */
    static final String RECORD_FILE = "bestScore.txt";
    
    /**
     * Nome del file dove salvare le statistiche
     * {@link FXMLDocumentController#initialize(URL, ResourceBundle)}
     * {@link FXMLDocumentController#updateStatistics(int, int, int)}
     */
    static final String STATS_FILE = "Stats.txt";
    
    /**
     * Nome del file in cui salvare le parole,prive delle regole dei suffissi
     */
    static final String CLEAN_DICTIONARY_FILE = "out.txt";
    
    /**
     * Tempo in difficoltà facile
     * {@link FXMLDocumentController#changeTimeValue()}
     */
    static final int EASY_TIME = 120;

    /**
     * Tempo in difficoltà media
     * {@link FXMLDocumentController#changeTimeValue()}
     */
    static final int MEDIUM_TIME = 90;

    /**
     * Tempo in difficoltà difficile
     * {@link FXMLDocumentController#changeTimeValue()}
     */
    static final int HARD_TIME = 60;
}
