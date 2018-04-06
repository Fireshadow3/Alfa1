/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.awt.event.KeyListener;
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
    private LinkedList<Button> buttonLinkedList;
    private Stack<Button> selectedButtons;
    private String concWordsTyped;
    
    public Management init(){
        Management man = new Management("in.txt", "out.txt");
        man.deleteWordsAfterSlashCharacter();
        return man;
    } 
    
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
    
    public void handleGeneralButtonAction(Button button) {
        int prevScore, actScore;
        Management m = init();
        if (!(button.getStyle().equalsIgnoreCase(BUTTON_COLOR))){
            button.setStyle(BUTTON_COLOR);
            selectedButtons.add(button);
            if (selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    button.getText());
            String s = wordFromKeyboardTextField.getText();
            for (String param : m.getWordsInDictionaryArrayList()) {
                if (s.equalsIgnoreCase(param) && s.length() > 2) {
                    if (!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
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
    
    public void handleButton1Action(Event event) { 
        handleGeneralButtonAction(letter1Button);
    }
    
    public void handleButton2Action(Event event) { 
        handleGeneralButtonAction(letter2Button);
    }
    
    public void handleButton3Action(Event event) { 
        handleGeneralButtonAction(letter3Button);
    }
    
    public void handleButton4Action(Event event) { 
        handleGeneralButtonAction(letter4Button);
    }
        
    public void handleButton5Action(Event event) { 
        handleGeneralButtonAction(letter5Button);
    }
    
    public void handleButton6Action(Event event) { 
        handleGeneralButtonAction(letter6Button);
    }
        
    public void handleButton7Action(Event event) { 
        handleGeneralButtonAction(letter7Button);
    }
    
    public void keyPressed(KeyEvent kEvent) {
        if (!wordFromKeyboardTextField.getSelectedText().isEmpty() &&
                kEvent.getCode() == KeyCode.BACK_SPACE) {
            int lenToDel = wordFromKeyboardTextField.getSelectedText().length();
            for (; lenToDel > 0; lenToDel--){
                String param = "" + wordFromKeyboardTextField.getSelectedText().charAt(lenToDel - 1);
                for (Button b : selectedButtons) { 
                    if (b.getText().equalsIgnoreCase(param) && b.getStyle().equalsIgnoreCase(BUTTON_COLOR)) {
                        b.setStyle(null);
                        b.setDisable(false);
                    }
                }
            }
        }else {    
            String param = "" + kEvent.getText();
            for (Button x : buttonLinkedList) {
                if (x.getText().equalsIgnoreCase(param) || kEvent.getCode() == KeyCode.BACK_SPACE) {
                    handleGenericKeyTyped(kEvent);
                    break;
                }else {
                    System.out.println("No valid key");
                    System.out.println("Param -> " + param);
                    System.out.println("Text -> " + x.getText());
                }
            }
        }
    }
    
    public void handleWordTextFieldAction(ActionEvent event){
    }
    
    private void handleBackSpaceKeyTyped(KeyEvent kEvent) {
        if(wordFromKeyboardTextField.getText().length() > 0){
            String toDel = "" + wordFromKeyboardTextField.getText().substring(
                    wordFromKeyboardTextField.getText().length() - 1);
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
    
    private void handleGenericKeyTyped(KeyEvent kEvent) {
        if (kEvent.getCode() == KeyCode.BACK_SPACE)
            handleBackSpaceKeyTyped(kEvent);
        else {
            String string = "" + kEvent.getText();
            for (Button b : buttonLinkedList) {
                if (b.getText().equalsIgnoreCase(string) && !(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
                    int prevScore, actScore;
                    Management m = init();
                    if (!(b.getStyle().equalsIgnoreCase(BUTTON_COLOR))) {
                        b.setStyle(BUTTON_COLOR);
                        selectedButtons.push(b);
                        System.out.println(selectedButtons);
                        if (selectedButtons.size() - 1 > 0)
                            selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
                        concWordsTyped += kEvent.getText().toLowerCase();
                        // System.out.println(concWordsTyped);
                        for (String param : m.getWordsInDictionaryArrayList()) {
                            if (concWordsTyped.equalsIgnoreCase(param) && concWordsTyped.length() > 2) {
                                if (!(wordsFoundTextArea.getText().contains(concWordsTyped))){
                                    wordFromKeyboardTextField.clear();
                                    wordsFoundTextArea.setText(wordsFoundTextArea.getText() +
                                                            (concWordsTyped + "\n"));
                                    wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                                    prevScore = Integer.parseInt(scoreTextField.getText());
                                    actScore = scoringSwitchCase(concWordsTyped.length());
                                    scoreTextField.setText("" + (prevScore + actScore));
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
                    /*
                    else if(!(b.isDisabled())){
                        b.setStyle(null);
                        selectedButtons.remove(b);
                        if (wordFromKeyboardTextField.getText().length() > 1) {
                            wordFromKeyboardTextField.setText(
                                    wordFromKeyboardTextField.getText().substring(0, 
                                            wordFromKeyboardTextField.getText().length() - 1));
                        }else {
                            StringBuilder monco = new StringBuilder(wordFromKeyboardTextField.getText());
                            monco.deleteCharAt(0);
                            wordFromKeyboardTextField.setText(monco.toString());                        
                        }
                        if (selectedButtons.size() - 1 >= 0)
                            selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
                    }
                    */
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Management man = new Management("in.txt", "out.txt");
        // buttonInitializing();
        man.deleteWordsAfterSlashCharacter();
        selectedButtons = new Stack<>();
        concWordsTyped = "";
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
        timeMinTextField.setEditable(false);
        timeSecTextField.setEditable(false);
        wordsFoundTextArea.setDisable(true);
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
