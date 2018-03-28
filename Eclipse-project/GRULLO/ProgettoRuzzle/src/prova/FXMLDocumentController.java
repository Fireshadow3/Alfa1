/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import java.util.Stack;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author Fabio
 */
public class FXMLDocumentController implements Initializable {
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
    
    private ArrayList<String> wordsWithSevenCharacters;
    
    private Stack<Button> selectedButtons;
    
    private Stack<String> selectedKeys;
    
    private HashMap<String, Button> startKeys;
    
    private static final Integer STARTTIME = 15; 
    
    private Timeline timeline;
    
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    
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
    
    public void handleButton1Action(Event event) {
        int prevScore, actScore;
        Management m = init();
        if(!(letter1Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter1Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter1Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter1Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter1Button.isDisabled())){
            letter1Button.setStyle(null);
            selectedButtons.remove(letter1Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
    
    public void handleButton2Action(Event event) { 
        int prevScore, actScore;
        Management m = init();
        if(!(letter2Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter2Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter2Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter2Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter2Button.isDisabled())){
            letter2Button.setStyle(null);
            selectedButtons.remove(letter2Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
        
    public void handleButton3Action(Event event) { 
        int prevScore, actScore;
        Management m = init();
        if(!(letter3Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter3Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter3Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter3Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter3Button.isDisabled())){
            letter3Button.setStyle(null);
            selectedButtons.remove(letter3Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
    
    public void handleButton4Action(Event event) { 
        int prevScore, actScore;
        Management m = init();
        if(!(letter4Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter4Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter4Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter4Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter4Button.isDisabled())){
            letter4Button.setStyle(null);
            selectedButtons.remove(letter4Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
        
    public void handleButton5Action(Event event) { 
        int prevScore, actScore;
        Management m = init();
        if(!(letter5Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter5Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter5Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter5Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter5Button.isDisabled())){
            letter5Button.setStyle(null);
            selectedButtons.remove(letter5Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
    
    public void handleButton6Action(Event event) { 
        int prevScore, actScore;
        Management m = init();
        if(!(letter6Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter6Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter6Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter6Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter6Button.isDisabled())){
            letter6Button.setStyle(null);
            selectedButtons.remove(letter6Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
        
    public void handleButton7Action(Event event) { 
        int prevScore, actScore;
        Management m = init();
        if(!(letter7Button.getStyle().equalsIgnoreCase("-fx-background-color: #ff471a"))){
            letter7Button.setStyle("-fx-background-color: #ff471a");
            selectedButtons.add(letter7Button);
            if(selectedButtons.size() - 1 > 0)
                selectedButtons.get(selectedButtons.size() - 2).setDisable(true);
            wordFromKeyboardTextField.setText(wordFromKeyboardTextField.getText() + 
                    letter7Button.getText());
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    if(!(wordsFoundTextArea.getText().contains(s))){
                        wordFromKeyboardTextField.clear();
                        wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                        wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                        prevScore = Integer.parseInt(scoreTextField.getText());
                        actScore = scoringSwitchCase(s.length());
                        scoreTextField.setText("" + (prevScore + actScore));
                        for(Button b : selectedButtons){
                            b.setStyle(null);
                            b.setDisable(false);
                        }
                        selectedButtons.clear();
                    }
                }    
            }
        }else if(!(letter7Button.isDisabled())){
            letter7Button.setStyle(null);
            selectedButtons.remove(letter7Button);
            wordFromKeyboardTextField.setText(
                    wordFromKeyboardTextField.getText().substring(0, 
                            wordFromKeyboardTextField.getText().length() - 1));
            if(selectedButtons.size() - 1 >= 0)
                selectedButtons.get(selectedButtons.size() - 1).setDisable(false);
        }
    }
    
    public void keyPressed(KeyEvent event) {
        Management m = init();
        int prevScore, actScore;
        String key = event.getCharacter();
        if(startKeys.containsKey(key)){
            startKeys.get(key).setStyle("-fx-background-color: #ff471a");
            selectedKeys.add(key);
            String s = wordFromKeyboardTextField.getText();
            for(String param : m.getWordsInDictionaryArrayList()){
                if(s.equalsIgnoreCase(param) && s.length() > 2){
                    wordFromKeyboardTextField.clear();
                    wordsFoundTextArea.setText(wordsFoundTextArea.getText() + (s + "\n"));
                    wordsFoundTextArea.setStyle("-fx-text-color: #2f2f1e");
                    prevScore = Integer.parseInt(scoreTextField.getText());
                    actScore = scoringSwitchCase(s.length());
                    scoreTextField.setText("" + (prevScore + actScore));
                }
            }
        }
    }
    
    private void buttonSetText(char c, int n){
        switch(n){
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
    
    public void buttonInitialization2(String word){
        Random rand = new Random();
        char c;
        int index;
        ArrayList<Integer> numbersSelected = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            c = word.charAt(i);
            index = rand.nextInt(word.length());
            while(numbersSelected.contains(index)){
                index = rand.nextInt(word.length());
            }
            buttonSetText(c, index);
            numbersSelected.add(index);
        }
    }
    
    
    
    public void buttonInitializing(){
        Random rand = new Random();
        int[] x = new int[7];
        char c;
        for(int i = 0; i < 7; i++){
            x[i] = rand.nextInt(26);
        }
        letter1Button.setText("A");
        letter2Button.setText("B");
        letter3Button.setText("A");
        letter4Button.setText("N");
        letter5Button.setText("E");
        letter6Button.setText("S");
        letter7Button.setText("E");
        /*
        letter1Button.setText("" + (char) (x[0] + 65));
        letter2Button.setText("" + (char) (x[1] + 65));
        letter3Button.setText("" + (char) (x[2] + 65));
        letter4Button.setText("" + (char) (x[3] + 65));
        letter5Button.setText("" + (char) (x[4] + 65));
        letter6Button.setText("" + (char) (x[5] + 65));
        letter7Button.setText("" + (char) (x[6] + 65));*/
    }
    
    public void keysInitialization(){
        startKeys.put(letter1Button.getText(), letter1Button);
        startKeys.put(letter2Button.getText(), letter2Button);
        startKeys.put(letter3Button.getText(), letter3Button);
        startKeys.put(letter4Button.getText(), letter4Button);
        startKeys.put(letter5Button.getText(), letter5Button);
        startKeys.put(letter6Button.getText(), letter6Button);
        startKeys.put(letter7Button.getText(), letter7Button);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Management man = new Management("in.txt", "out.txt");
        // buttonInitializing();
        man.deleteWordsAfterSlashCharacter();
        selectedButtons = new Stack<>();
        selectedKeys = new Stack<>();
        startKeys = new HashMap<>();
        
        /*String word = man.wordGenerationFromRandomLetters(letter1Button.getText(),
                letter2Button.getText(), letter3Button.getText(), 
                letter4Button.getText(), letter5Button.getText(),
                letter6Button.getText(), letter7Button.getText());
        wordsWithSevenCharacters = man.wordOfSevenCharacterInDictionary(word);*/
        String s = man.startingWord();
        buttonInitialization2(s);
        keysInitialization();
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
        }*/
         
        
        // QUESTO RIGUARDA IL MENU CON LA SCELTA DEL TEMPO IN BASE ALLA DIFFICOLTA
        /* ChoiceBox difficultyChoice = new ChoiceBox();
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
        letter7Button.setDisable(true); */
    }    
    
}
