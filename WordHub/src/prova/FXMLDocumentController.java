 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import java.util.Stack;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Fabio
 */
public class FXMLDocumentController implements Initializable, Constants {
    @FXML
    private TextField scoreTextField;
    @FXML
    private TextField timeMinTextField;
    @FXML
    private TextField timeSecTextField;
    @FXML
    private TextField wordFromKeyboardTextField;
    @FXML
    private TextArea wordsFoundTextArea;
    @FXML
    private Button letter1Button;
    @FXML
    private Button letter2Button;
    @FXML
    private Button letter3Button;
    @FXML
    private Button letter4Button;
    @FXML
    private Button letter5Button;
    @FXML
    private Button letter6Button;
    @FXML
    private Button letter7Button;
    @FXML
    private ChoiceBox difficultyChoice;
    @FXML
    private TextField bestScoreTextField;
    @FXML
    private Button BTN_Language;
    private Management man;
    private Statistics stats;
    private LinkedList<Button> buttonLinkedList;
    private Stack<Button> selectedButtons;
    private String concWordsTyped;        
    
    public int scoringSwitchCase(int length){
        switch(length){
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
    
    public void handleGeneralButtonAction(Button button) throws IOException {
        int prevScore, actScore, bestScore = Integer.parseInt(bestScoreTextField.getText());
        if (!(button.getStyle().equalsIgnoreCase(BUTTON_COLOR))){
            button.setStyle(BUTTON_COLOR);
            selectedButtons.add(button);
            if (selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    button.getText());
            String s = wordFromKeyboardTextField.getText();
            for (String param : man.getWordsInDictionaryArrayList()) {
                if (s.equalsIgnoreCase(param) && s.length() > 2) {
                    if (!(wordsFoundTextArea.getText().contains(s))){
                        stats.setTotWordsFound(stats.getTotWordsFound() + 1);
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        stats.setTotScore(stats.getTotScore() + actScore);
                        scoreTextField.setText("" + (prevScore + actScore));
                        if(Integer.parseInt(scoreTextField.getText()) > bestScore){
                            bestScoreTextField.setText(scoreTextField.getText());
                            updateBestScore(Integer.parseInt(scoreTextField.getText()));
                            updateStatistics(bestScore, 
                                    stats.getTotScore() / stats.getGamesNum(),
                                    stats.getTotWordsFound() / stats.getGamesNum());
                        }
                        for (Button b : selectedButtons) {
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        concWordsTyped = "";
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(button.isDisabled())){
            button.setStyle(null);
            selectedButtons.remove(button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
    
    public void endGame(Event event){
        letter1Button.setDisable(true);
        letter2Button.setDisable(true);
        letter3Button.setDisable(true);
        letter4Button.setDisable(true);
        letter5Button.setDisable(true);
        letter6Button.setDisable(true);
        letter7Button.setDisable(true);
        wordFromKeyboardTextField.setDisable(true);
    }
    
    public void handleButton1Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter1Button);
    }
    
    public void handleButton2Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter2Button);
    }
    
    public void handleButton3Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter3Button);
    }
    
    public void handleButton4Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter4Button);
    }
        
    public void handleButton5Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter5Button);
    }
    
    public void handleButton6Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter6Button);
    }
        
    public void handleButton7Action(Event event) throws IOException { 
        handleGeneralButtonAction(letter7Button);
    }
    
    public void keyPressed(KeyEvent kEvent) throws IOException {
        System.out.println(kEvent.getCode());
        if(wordFromKeyboardTextField.getSelectedText().isEmpty()){
            String param = "" + kEvent.getText();
            try{
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
            }catch(NoValidKeyException e){
                System.out.println(e.getError());
            }
        }
    }
    
    public void handleWordTextFieldAction(ActionEvent event){
    }
    
    private void handleBackSpaceKeyTyped(KeyEvent kEvent) {
        if(wordFromKeyboardTextField.getText().length() > 0){
            String toDel = "" + wordFromKeyboardTextField.getText().substring(
                    wordFromKeyboardTextField.getText().length() - 1);
            concWordsTyped = new StringBuilder(concWordsTyped).deleteCharAt(
                    concWordsTyped.length() - 1).toString();
            System.out.println(concWordsTyped);
            Button b = null;
            for (Button b1 : selectedButtons) {
                if (b1.getText().equalsIgnoreCase(toDel) && b1.getStyle().equalsIgnoreCase(BUTTON_COLOR)) {
                    b = selectedButtons.pop();
                    b.setStyle(null);
                    if (selectedButtons.size() - 1 >= 0)
                        selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
                    break;
                }
            }
        }
    }
    
    private void handleGenericKeyTyped(KeyEvent kEvent) throws IOException {
        if (kEvent.getCode() == KeyCode.BACK_SPACE)
            handleBackSpaceKeyTyped(kEvent);
        else {
            String string = "" + kEvent.getText();
            for (Button b : buttonLinkedList) {
                if (b.getText().equalsIgnoreCase(string) && !(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
                    int prevScore, actScore, bestScore;
                    bestScore = Integer.parseInt(bestScoreTextField.getText());
                    if (!(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
                        b.setStyle(BUTTON_COLOR);
                        selectedButtons.push(b);
                        if (selectedButtons.size() - 1 > 0)
                            selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
                        concWordsTyped += kEvent.getText().toLowerCase();
                        // System.out.println(concWordsTyped);
                        for (String param : man.getWordsInDictionaryArrayList()) {
                            if (concWordsTyped.equalsIgnoreCase(param) && concWordsTyped.length() > 2) {
                                if (!(wordsFoundTextArea.getText().contains(concWordsTyped))){
                                    wordFromKeyboardTextField.clear();
                                    wordsFoundTextArea.setText(wordsFoundTextArea.getText() +
                                                            (concWordsTyped + "\n"));
                                    prevScore = Integer.parseInt(scoreTextField.getText());
                                    actScore = scoringSwitchCase(concWordsTyped.length());
                                    scoreTextField.setText("" + (prevScore + actScore));
                                    if(Integer.parseInt(scoreTextField.getText()) > bestScore){
                                        bestScoreTextField.setText(scoreTextField.getText());
                                        bestScore = Integer.parseInt(scoreTextField.getText());
                                        updateBestScore(bestScore);
                                    }
                                    for (Button b1 : selectedButtons) {
                                        b1.setStyle(null);
                                        b1.setDisable(false);
                                    }
                                    concWordsTyped = "";
                                    selectedButtons.removeAllElements();
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private void buttonSetText(char c, int randomNumberFrom0To6){
        switch(randomNumberFrom0To6){
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
    
    public void buttonInitialization(String word){
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
    
    public void updateBestScore(int newBestScore) throws IOException{
        TextFile f = new TextFile(RECORD_FILE, 'w');
        f.toFile("" + newBestScore);
        f.close();
    }
    
    public void updateStatistics(int maxScore, int avScore, int avWordsNum) throws IOException{
        TextFile fout = new TextFile(STATS_FILE, 'W');
        stats.setMaxScore(maxScore);
        stats.setAvScore(avScore);
        stats.setAvWordsNum(avWordsNum);
        fout.toFile(stats.tostring());
        fout.close();
    }
    
    public void loadBestScore() throws FileNotFoundException, UnsupportedEncodingException, IOException{
        TextFile f = new TextFile(RECORD_FILE, 'r');
        String line;
        while((line = f.fromFile()) != null)
            bestScoreTextField.setText(line);
        f.close();
    }
    
        public void changeLanguage(){
        //Se è italiano cambio in inglese
        if(BTN_Language.getText().equals("IT")){
            //Cambio il dizionario
            //Creo un nuovo dizionario con la lingua diversa però
            Management new_dictionary;
            new_dictionary = new Management(ENG_DIC, "out.txt");
            man = new_dictionary;
            //Cambio label
            //BUG non posso settarlo subito su "EN", ma posso cambiarlo in altro e poi in EN
            BTN_Language.setText("EN");
            //BTN_Language.setText("EN");
        }
        //Se è inglese cambio in italiano
        else if(BTN_Language.getText().equals("EN")){
            //Cambio il dizionario
            //Creo un nuovo dizionario con la lingua diversa però
            Management new_dictionary;
            new_dictionary = new Management(ITA_DIC, "out.txt");
            man = new_dictionary;
            //Cambio label
            BTN_Language.setText("IT");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        man = new Management(ITA_DIC, "out.txt");
        try{
            stats = new Statistics();
        }catch(Exception e){
            System.err.println("Errore nel caricamento delle statistiche");
        }
        man.deleteWordsAfterSlashCharacter();
        stats.setGamesNum(stats.getGamesNum() + 1);
        selectedButtons = new Stack<>();
        concWordsTyped = "";
        try {
            loadBestScore();
        }catch(Exception e){
            System.err.println("Errore nell'apertura del file");
        }
        /*
        String word = man.wordGenerationFromRandomLetters(letter1Button.getText(),
                letter2Button.getText(), letter3Button.getText(), 
                letter4Button.getText(), letter5Button.getText(),
                letter6Button.getText(), letter7Button.getText());
        wordsWithSevenCharacters = man.wordOfSevenCharacterInDictionary(word);
        */
        String s = man.startingWord();
        buttonInitialization(s);
        buttonLinkedList = new LinkedList<>(Arrays.asList(letter1Button, letter2Button,
                letter3Button, letter4Button, letter5Button, letter6Button, letter7Button));
        scoreTextField.setText("0");
        scoreTextField.setEditable(false);
        bestScoreTextField.setEditable(false);
        timeMinTextField.setEditable(false);
        timeSecTextField.setEditable(false);
        wordsFoundTextArea.setEditable(false);
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
                if(wordFromKeyboardTextField.getCaretPosition() != 
                        wordFromKeyboardTextField.getText().length() - 1)
                    wordFromKeyboardTextField.end();
            }
        });
        wordFromKeyboardTextField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(((event.getCode() == KeyCode.UP) || (event.getCode() == KeyCode.RIGHT) ||
                    (event.getCode() == KeyCode.DOWN) || (event.getCode() == KeyCode.LEFT))){
                    event.consume();
                    wordFromKeyboardTextField.end();
                }
            }
        });
        EventHandler<KeyEvent> tabListener = evt -> {
            if(!wordFromKeyboardTextField.getSelectedText().isEmpty()){
                if (evt.getCode() == KeyCode.BACK_SPACE){
                    evt.consume();
                    wordFromKeyboardTextField.end();
                }
            }
        };
        wordFromKeyboardTextField.addEventHandler(KeyEvent.ANY, tabListener);
        /*timeSecTextField.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
            }
        });*/
        /*wordFromKeyboardTextField.addEventFilter(KeyEvent.KEY_PRESSED, 
            new EventHandler<KeyEvent>(){
                public void handle(KeyEvent kEv){
                    for(Button b : buttonLinkedList)
                        if(b.getText().equalsIgnoreCase(kEv.getText()) || 
                                kEv.getCode() == KeyCode.BACK_SPACE)
                            return;
                    kEv.consume();
                    StringBuffer sb;
                    System.out.println(wordFromKeyboardTextField.getText().length());
                    //if(!(((kEv.getCode() == KeyCode.UP) || (kEv.getCode() == KeyCode.RIGHT) ||
                      //  (kEv.getCode() == KeyCode.DOWN) || (kEv.getCode() == KeyCode.LEFT)))){
                    if(kEv.getText().compareToIgnoreCase("a") >= 0 && kEv.getText().compareToIgnoreCase("z") <= 0){  
                        if(wordFromKeyboardTextField.getText().length() > 1){
                            sb = new StringBuffer(wordFromKeyboardTextField.getText());
                        }else{
                            sb = new StringBuffer(wordFromKeyboardTextField.getText() + " ");
                            System.out.println("monco");
                        }
                        sb.deleteCharAt(wordFromKeyboardTextField.getText().length() - 1);
                        wordFromKeyboardTextField.setText(new String(sb.toString()));
                        wordFromKeyboardTextField.end();
                    }
                }
            });
                    /*KeyEvent press = new KeyEvent("backspace", wordFromKeyboardTextField,
                            KeyEvent.KEY_PRESSED, "", "", KeyCode.BACK_SPACE, 
                            false, false, false, false);
                    wordFromKeyboardTextField.fireEvent(press);*/
                    /*if(wordFromKeyboardTextField.getText().length() > 0)
                        wordFromKeyboardTextField.deletePreviousChar();
                    else
                        wordFromKeyboardTextField.clear();
                    /*wordFromKeyboardTextField.setText(
                            wordFromKeyboardTextField.getText().substring(0, 
                                    wordFromKeyboardTextField.getText().length() - 1));
                    kEv.consume();
                }
            });
        */
        // QUESTO SERVE PER RIGENERARE LE LETTERE IN MODO CHE FORMINO UNA PAROLA
        // CHE E NEL DIZIONARIO
        /*while(wordsWithSevenCharacters.isEmpty()){
            buttonInitializing();
            word = man.wordGenerationFromRandomLetters(letter1Button.getText(),
                            letter2Button.getText(), letter3Button.getText(), 
                            letter4Button.getText(), letter5Button.getText(),
                            letter6Button.getText(), letter7Button.getText());
            wordsWithSevenCharacters = man.wordOfSevenCharacterInDictionary(word);
        }
        */
         
        
        // QUESTO RIGUARDA IL MENU CON LA SCELTA DEL TEMPO IN BASE ALLA DIFFICOLTA
        /* 
        ChoiceBox difficultyChoice = new ChoiceBox();
        difficultyChoice.setItems(FXCollections.observableArrayList
        ("New Document", "Open ", new Separator(), "Save", "Save as"));
        scoreTextField.setEditable(false);
        timeMinTextField.setEditable(false);
        timeSecTextField.setEditable(false);
        wordFromKeyboardTextField.setEditable(false);
        wordsFoundTextArea.setDisable(true);
        letter1Button.setDisable(true);
        letter2Button.setDisable(true);
        letter3Button.setDisable(true);
        letter4Button.setDisable(true);
        letter5Button.setDisable(true);
        letter6Button.setDisable(true);
        letter7Button.setDisable(true); 
        */
    }
}
