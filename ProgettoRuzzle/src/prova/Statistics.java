/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.io.IOException;

/**
 *
 * @author Fabio
 */
public class Statistics implements Constants{
    private int gamesNum;
    private int maxScore;
    private int avWordsNum;
    private int avScore;
    private int totScore;
    private int totWordsFound;
    
    public Statistics() {
        TextFile fin = new TextFile(STATS_FILE, 'r');
        String line;
        String elements[];
        int i;
        for(i = 0; i < 6; i++){
            System.out.println(i);
            try{
                while((line = fin.fromFile()) != null){
                    elements = line.split("=");
                    System.out.println("Robe:" + elements.length);
                    switch(i){
                        case 0:
                            gamesNum = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println("moncone");
                            break;
                        case 1:
                            maxScore = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println("moncone2");
                            break;
                        case 2:
                            avWordsNum = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println("moncone3");
                            break;
                        case 3:
                            avScore = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println("moncone4");
                            break;
                        case 4:
                            totScore = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println("moncone5");
                            break;
                        case 5:
                            totWordsFound = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println("moncone6");
                            break;
                    }
                }
                fin.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public int getGamesNum() {
        return gamesNum;
    }

    public void setGamesNum(int gamesNum) {
        this.gamesNum = gamesNum;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getAvWordsNum() {
        return avWordsNum;
    }

    public void setAvWordsNum(int avWordsNum) {
        this.avWordsNum = avWordsNum;
    }

    public int getAvScore() {
        return avScore;
    }

    public void setAvScore(int avScore) {
        this.avScore = avScore;
    }

    public int getTotScore() {
        return totScore;
    }

    public void setTotScore(int totScore) {
        this.totScore = totScore;
    }

    public int getTotWordsFound() {
        return totWordsFound;
    }

    public void setTotWordsFound(int totWordsFound) {
        this.totWordsFound = totWordsFound;
    }

    public String tostring() {
        return "Number of games=" + gamesNum + "\n" + "Maximum score=" + maxScore +
                "\nAverage number of words found=" + avWordsNum +
                "\nAverage score=" + avScore +"\nTotal score=" + totScore +
                "\nTotal words found=" + totWordsFound;
    }
}
