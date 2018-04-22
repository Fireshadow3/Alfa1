/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordHub;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static wordHub.AnagramManagement.*;
import static wordHub.Constants.BUTTON_COLOR;
import static wordHub.Constants.ENG_DIC;
import static wordHub.Constants.ITA_DIC;
import static wordHub.FileManagement.getWordsInDictionaryArrayList;

/**
 *
 * @author Fabio
 */
public class FXMLDocumentController implements Initializable, Constants {
    @FXML
    /**
     * Primo bottone (in alto a sinistra)
     */
    private Button letter1Button;

    @FXML
    /**
     * Secondo bottone (in alto a destra)
     */
    private Button letter2Button;

    @FXML
    /**
     * Terzo bottone (in centro a sinistra)
     */
    private Button letter3Button;

    @FXML
    /**
     * Quarto bottone (al centro)
     */
    private Button letter4Button;

    @FXML
    /**
     * Quinto bottone (in centro a destra)
     */
    private Button letter5Button;

    @FXML
    /**
     * Sesto bottone (in basso a sinistra)
     */
    private Button letter6Button;

    @FXML
    /**
     * Settimo bottone (in basso a destra)
     */
    private Button letter7Button;

    @FXML
    /**
     * Bottone per terminare il gioco e chiudere la finestra
     */
    private Button endGameButton;
    
    @FXML
    /**
     * Bottone per il cambio della lingua
     */
    private Button BTN_Language;
    
    /**
     * Lista dei bottoni
     */
    private LinkedList<Button> buttonLinkedList;

    /**
     * Pila dei bottoni selezionati
     */
    private Stack<Button> selectedButtons;
    
    /**
     * Oggetto che serve a reperire il dizionario, su cui viene applicato il controllo della parola digitata
     */
    static AnagramManagement anagram;
    
    @FXML
    /**
     * TextField in cui viene scritta la parola digitata (premendo i bottoni o digitando da tastiera)
     */
    private TextField wordFromKeyboardTextField;

    @FXML
    /**
     * TextArea dove vengono scritte tutte le parole trovate
     */
    private TextArea wordsFoundTextArea;
    
    /**
     * Parola digitata dall'utente
     */
    private String concWordsTyped;
    
    @FXML
    /**
     * TextField dove vengono scritti i minuti rimanenti
     */
    private TextField timeMinTextField;

    @FXML
    /**
     * TextField dove vengono scritti i secondi rimanenti
     */
    private TextField timeSecTextField;
    
    /**
     * Timer del gioco
     */
    private Timer timer;

    /**
     * Processo che gestisce il timer
     */
    private TimerTask task;

    /**
     * Secondi passati dall'avvio del timer
     */
    private int secondsPassed;
     
    /**
     * Classe per memorizzare le statistiche (numero di partite, punteggio massimo, punteggio medio, numero medio di parole trovate)
     */
    private Statistics stats;
     
    @FXML
    /**
     * TextField dove viene memorizzato il record
     */
    private TextField bestScoreTextField;
     
    @FXML
    /**
     * TextField dove viene registrato il punteggio attuale
     */
    private TextField scoreTextField;
    
    /**
     * Lista delle difficoltà
     */
    private ObservableList<String> difficulties = FXCollections.observableArrayList("Easy", "Medium", "Hard");

    @FXML
    /**
     * ComboBox per la scelta della difficoltà
     */
    private ComboBox<String> diffBox;
    
    /**
     * Gestisce la pressione di un bottone generico
     * @param button bottone premuto
     * @throws IOException
     */
    public void handleGeneralButtonAction(Button button) throws IOException {
        int prevScore, actScore, bestScore = Integer.parseInt(
                bestScoreTextField.getText());
        if (!(button.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
            button.setStyle(BUTTON_COLOR);
            selectedButtons.add(button);
            if (selectedButtons.size() - 1 > 0) {
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            }
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                            button.getText());
            String s = wordFromKeyboardTextField.getText();
            for (String param : getWordsInDictionaryArrayList()) {
                if (s.equalsIgnoreCase(param) && s.length() > 2) {
                    if (!(wordsFoundTextArea.getText().contains(s))) {
                        stats.setTotWordsFound(stats.getTotWordsFound() + 1);
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        stats.setTotScore(stats.getTotScore() + actScore);
                        scoreTextField.setText("" + (prevScore + actScore));
                        if (Integer.parseInt(scoreTextField.getText()) > bestScore) {
                            bestScoreTextField.setText(scoreTextField.getText());
                            updateBestScore(Integer.parseInt(scoreTextField.getText()));
                            bestScore = Integer.parseInt(scoreTextField.getText());
                        }
                        stats.updateStatistics(bestScore, stats.getTotScore() / 
                                stats.getGamesNum(), stats.getTotWordsFound() / 
                                    stats.getGamesNum());
                        for (Button b : selectedButtons) {
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        concWordsTyped = "";
                        selectedButtons.clear();
                    }
                }
            }
        } else if (!(button.isDisabled())) {
            button.setStyle(null);
            selectedButtons.remove(button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0,
                            wordFromKeyboardTextField.getText().length() - 1));
            if (selectedButtons.size() - 1 >= 0) {
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
            }
        }
    }

    /**
     * Termina la partita e chiude la finestra
     *
     * @param event evento di pressione su endGameButton
     */
    public void endGame(Event event) {
        endGameButton.setDisable(true);
        letter1Button.setDisable(true);
        letter2Button.setDisable(true);
        letter3Button.setDisable(true);
        letter4Button.setDisable(true);
        letter5Button.setDisable(true);
        letter6Button.setDisable(true);
        letter7Button.setDisable(true);
        BTN_Language.setDisable(true);
        timeMinTextField.setText("" + 0);
        timeSecTextField.setText("" + 0);
        timeMinTextField.setDisable(true);
        timeSecTextField.setDisable(true);
        timer.cancel();
        wordFromKeyboardTextField.setDisable(true);
        diffBox.setDisable(true);
    }

    /**
     * Gestisce la pressione del primo bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton1Action(Event event) throws IOException {
        handleGeneralButtonAction(letter1Button);
    }

    /**
     * Gestisce la pressione del secondo bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton2Action(Event event) throws IOException {
        handleGeneralButtonAction(letter2Button);
    }

    /**
     * Gestisce la pressione del terzo bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton3Action(Event event) throws IOException {
        handleGeneralButtonAction(letter3Button);
    }

    /**
     * Gestisce la pressione del quarto bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton4Action(Event event) throws IOException {
        handleGeneralButtonAction(letter4Button);
    }

    /**
     * Gestisce la pressione del quinto bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton5Action(Event event) throws IOException {
        handleGeneralButtonAction(letter5Button);
    }

    /**
     * Gestisce la pressione del sesto bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton6Action(Event event) throws IOException {
        handleGeneralButtonAction(letter6Button);
    }

    /**
     * Gestisce la pressione del settimo bottone
     *
     * @param event evento bottone premuto
     * @throws IOException
     */
    public void handleButton7Action(Event event) throws IOException {
        handleGeneralButtonAction(letter7Button);
    }
    
    /**
     * Cambia la lingua del dizionario
     */
    public void changeLanguage() throws IOException {
        if (BTN_Language.getText().equals("IT")) {
            BTN_Language.setText("EN");
            AnagramManagement new_dictionary = new AnagramManagement(ENG_DIC, "out.txt");
            FXMLDocumentController.anagram = new_dictionary;
            FileManagement.deleteWordsAfterSlashCharacter();
            String s = dictionary.startingWord();
            for(Button b : selectedButtons){
                if(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))
                    handleGeneralButtonAction(b);
            }
            selectedButtons.removeAllElements();
            buttonInitialization(s);
        } else if (BTN_Language.getText().equals("EN")) {
            BTN_Language.setText("IT");
            AnagramManagement new_dictionary = new AnagramManagement(ITA_DIC, "out.txt");
            FXMLDocumentController.anagram = new_dictionary;
            FileManagement.deleteWordsAfterSlashCharacter();
            String s = AnagramManagement.dictionary.startingWord();
            buttonInitialization(s);
        }
    }
    
        /**
     * Assegna casualmente una lettera ai bottoni
     * @param c lettera
     * @param randomNumberFrom0To6 numero casuale tra 0 e 6
     */
    void buttonSetText(char c, int randomNumberFrom0To6) {
        switch (randomNumberFrom0To6) {
            case 0:
                letter1Button.setText("" + c);
                break;
            case 1:
                letter2Button.setText("" + c);
                break;
            case 2:
                letter3Button.setText("" + c);
                break;
            case 3:
                letter4Button.setText("" + c);
                break;
            case 4:
                letter5Button.setText("" + c);
                break;
            case 5:
                letter6Button.setText("" + c);
                break;
            case 6:
                letter7Button.setText("" + c);
                break;
        }
    }

    /**
     * Distribuisce casualmente le lettere di una parola di 7 lettere data tra i bottoni
     * @param word parola data di 7 lettere
     */
    public void buttonInitialization(String word) {
        Random rand = new Random();
        char c;
        int index;
        ArrayList<Integer> numbersSelected = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            index = rand.nextInt(word.length());
            while (numbersSelected.contains(index)) {
                index = rand.nextInt(word.length());
            }
            buttonSetText(c, index);
            numbersSelected.add(index);
        }
        System.out.println(word);
    }
    
    /**
     * Case per assegnare il punteggio alla parola trovata, in base alla sua lunghezza
     *
     * @param length lunghezza della parola trovata
     * @return punteggio relativo alla parola trovata
     */
    public int scoringSwitchCase(int length) {
        switch (length) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 7;
            case 7:
                return 11;
            default:
                return 0;
        }
    }

    /**
     * Gestisce l'inserimento di un carattere da tastiera
     *
     * @param kEvent evento della digitazione da tastiera
     * @throws IOException
     */
    public void keyPressed(KeyEvent kEvent) throws IOException {
        System.out.println(kEvent.getCode());
        if (wordFromKeyboardTextField.getSelectedText().isEmpty()) {
            String param = "" + kEvent.getText();
            try {
                for (Button x : buttonLinkedList) {
                    if ((x.getText().equalsIgnoreCase(param) && !x.getStyle().equalsIgnoreCase(BUTTON_COLOR))
                            || kEvent.getCode() == KeyCode.BACK_SPACE) {
                        handleGenericKeyTyped(kEvent);
                        return;
                    }
                }
                throw new NoValidKeyException("No valid key: the key pressed "
                        + "may not belong to any button, or it may have been already pressed(in case "
                        + "that a letter does not occurs more than once in the buttons' text)");
            } catch (NoValidKeyException e) {
                System.out.println(e.getError());
            }
        }
    }

    /**
     * Gestisce la digitazione del backspace
     *
     * @param kEvent evento digitazione del backspace
     */
    void handleBackSpaceKeyTyped(KeyEvent kEvent) {
        if (wordFromKeyboardTextField.getText().length() > 0) {
            String toDel = "" + wordFromKeyboardTextField.getText().substring(
                    wordFromKeyboardTextField.getText().length() - 1);
            System.out.println(concWordsTyped);
            Button b = null;
            for (Button b1 : selectedButtons) {
                if (b1.getText().equalsIgnoreCase(toDel) && b1.getStyle().equalsIgnoreCase(BUTTON_COLOR)) {
                    b = selectedButtons.pop();
                    b.setStyle(null);
                    concWordsTyped = new StringBuilder(concWordsTyped).deleteCharAt(
                            concWordsTyped.length() - 1).toString();
                    if (selectedButtons.size() - 1 >= 0) {
                        selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Gestisce la digitazione di un carattere generico
     *
     * @param kEvent evento della digitazione da tastiera
     * @throws IOException
     */
    void handleGenericKeyTyped(KeyEvent kEvent) throws IOException {
        if (kEvent.getCode() == KeyCode.BACK_SPACE) {
            handleBackSpaceKeyTyped(kEvent);
        } else {
            String string = "" + kEvent.getText();
            for (Button b : buttonLinkedList) {
                if (b.getText().equalsIgnoreCase(string) && !(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
                    int prevScore, actScore, bestScore;
                    bestScore = Integer.parseInt(bestScoreTextField.getText());
                    if (!(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
                        b.setStyle(BUTTON_COLOR);
                        selectedButtons.push(b);
                        if (selectedButtons.size() - 1 > 0) {
                            selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
                        }
                        concWordsTyped += kEvent.getText().toLowerCase();
                        for (String param : getWordsInDictionaryArrayList()) {
                            if (concWordsTyped.equalsIgnoreCase(param) && concWordsTyped.length() > 2) {
                                if (!(wordsFoundTextArea.getText().contains(concWordsTyped))) {
                                    wordFromKeyboardTextField.clear();
                                    wordsFoundTextArea.setText(wordsFoundTextArea.getText()
                                            + (concWordsTyped + "\n"));
                                    prevScore = Integer.parseInt(scoreTextField.getText());
                                    actScore = scoringSwitchCase(concWordsTyped.length());
                                    scoreTextField.setText("" + (prevScore + actScore));
                                    if (Integer.parseInt(scoreTextField.getText()) > bestScore) {
                                        bestScoreTextField.setText(scoreTextField.getText());
                                        bestScore = Integer.parseInt(scoreTextField.getText());
                                        updateBestScore(bestScore);
                                    }
                                    for (Button b1 : selectedButtons) {
                                        b1.setStyle(null);
                                        b1.setDisable(false);
                                    }
                                    selectedButtons.removeAllElements();
                                    concWordsTyped = "";
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
     
    /**
     * Imposta il timer in base alla difficoltà selezionata
     * @param time_value tempo scelto in base alla difficoltà
     */
    public void timerManager(int time_value) {
        secondsPassed = 0;
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                timeMinTextField.setText("" + (time_value - secondsPassed) / 60);
                timeSecTextField.setText("" + (time_value - secondsPassed) % 60);
                Event event = null;
                if (secondsPassed == time_value) {
                    endGame(event);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    
    public void choiceBoxDiff() throws InterruptedException {
        diffBox.getItems().clear();
        diffBox.getItems().addAll("Easy", "Medium", "Hard");
        diffBox.setOnAction(event -> {
            String selectedChoice = diffBox.getValue();
            if (selectedChoice.equalsIgnoreCase("easy")) {
                timerManager(EASY_TIME);
            } else if (selectedChoice.equalsIgnoreCase("medium")) {
                timerManager(MEDIUM_TIME);
            } else if (selectedChoice.equalsIgnoreCase("hard")) {
                timerManager(HARD_TIME);
            }
            diffBox.setDisable(true);
        });
        changeTimeValue();
    }

    /**
     * Abilita tutti i bottoni e le TextField e fa partire il timer
     */
    public void changeTimeValue() {
        wordFromKeyboardTextField.setEditable(true);
        endGameButton.setDisable(false);
        BTN_Language.setDisable(false);
        letter1Button.setDisable(false);
        letter2Button.setDisable(false);
        letter3Button.setDisable(false);
        letter4Button.setDisable(false);
        letter5Button.setDisable(false);
        letter6Button.setDisable(false);
        letter7Button.setDisable(false);
    }
    
    /**
     * Scrive su file il nuovo record
     * @param newBestScore nuovo record
     * @throws IOException 
     */
    public void updateBestScore(int newBestScore) throws IOException {
        Statistics.writeStatsToFile(RECORD_FILE, "" + newBestScore);
    }

    /**
     * Carica il miglior punteggio da file
     * @throws FileNotFoundException file non trovato
     * @throws UnsupportedEncodingException codifica non supportata
     * @throws IOException 
     */
    public void loadBestScore() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        TextFile f = new TextFile(RECORD_FILE, 'r');
        String line;
        while ((line = f.fromFile()) != null) {
            bestScoreTextField.setText(line);
        }
        f.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anagram = new AnagramManagement(ITA_DIC, "out.txt");
        Statistics stats = new Statistics();
        try {
            Statistics.readStatsFromFile(STATS_FILE, stats);
            System.out.println("Statistiche iniziali:" + stats.tostring());
        } catch (Exception e) {
            System.out.println("Marco nero:" + e);
        }
        FileManagement.deleteWordsAfterSlashCharacter();
        stats.setGamesNum(stats.getGamesNum() + 1);
        selectedButtons = new Stack<>();
        concWordsTyped = "";
        try {
            loadBestScore();
        } catch (IOException e) {
            System.out.println("lezzo" + e);
        }
        String s = anagram.dictionary.startingWord();
        buttonInitialization(s);
        buttonLinkedList = new LinkedList<>(Arrays.asList(letter1Button, letter2Button,
                letter3Button, letter4Button, letter5Button, letter6Button, letter7Button));
        scoreTextField.setText("0");
        wordFromKeyboardTextField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent inputevent) {
                if (!wordFromKeyboardTextField.getSelectedText().isEmpty()) {
                    wordFromKeyboardTextField.deselect();
                }
            }
        });
        wordFromKeyboardTextField.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!wordFromKeyboardTextField.getSelectedText().isEmpty()) {
                    wordFromKeyboardTextField.deselect();
                }
            }
        });
        wordFromKeyboardTextField.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (wordFromKeyboardTextField.getCaretPosition()
                        != wordFromKeyboardTextField.getText().length() - 1) {
                    wordFromKeyboardTextField.end();
                }
            }
        });
        wordFromKeyboardTextField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (((event.getCode() == KeyCode.UP) || (event.getCode() == KeyCode.RIGHT)
                        || (event.getCode() == KeyCode.DOWN) || (event.getCode() == KeyCode.LEFT))) {
                    event.consume();
                    wordFromKeyboardTextField.end();
                }
            }
        });
        EventHandler<KeyEvent> tabListener = evt -> {
            if (!wordFromKeyboardTextField.getSelectedText().isEmpty()) {
                if (evt.getCode() == KeyCode.BACK_SPACE) {
                    evt.consume();
                    wordFromKeyboardTextField.end();
                }
            }
        };
        wordFromKeyboardTextField.addEventHandler(KeyEvent.ANY, tabListener);
        scoreTextField.setEditable(false);
        timeMinTextField.setEditable(false);
        timeSecTextField.setEditable(false);
        wordFromKeyboardTextField.setEditable(false);
        wordsFoundTextArea.setEditable(false);
        bestScoreTextField.setEditable(false);
        endGameButton.setDisable(true);
        BTN_Language.setDisable(true);
        letter1Button.setDisable(true);
        letter2Button.setDisable(true);
        letter3Button.setDisable(true);
        letter4Button.setDisable(true);
        letter5Button.setDisable(true);
        letter6Button.setDisable(true);
        letter7Button.setDisable(true);
    }
}
