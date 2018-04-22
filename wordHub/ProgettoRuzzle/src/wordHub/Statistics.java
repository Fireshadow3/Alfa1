/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordHub;

import java.io.IOException;

/**
 *
 * @author Fabio
 */
public class Statistics implements Constants{
    /**
     * Numero di partite effettuate dall'utente
     */
    private int gamesNum;
    
    /**
     * Punteggio massimo accumulato
     */
    private int maxScore;
    
    /**
     * Numero medio di parole trovate 
     */
    private int avWordsNum;
    
    /**
     * Punteggio medio
     */
    private int avScore;
    
    /**
     * Punteggio totale, ottenuto come cumulato di tutte le partite
     */
    private int totScore;
    
    /**
     * Parole trovate totali, ottenute come cumulato di tutte le partite
     */
    private int totWordsFound;
    
    /*public Statistics() {
        TextFile fin = new TextFile(STATS_FILE, 'r');
        String line;
        String[] elements = new String[6];
        int i;
        for (i = 0; i < 6; i++) {
            try {
                while ((line = fin.fromFile()) != null){
                    elements = line.split("=");
                    System.out.println("Robe:" + elements.length);
                    switch(i){
                        case 0:
                            gamesNum = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println(gamesNum);
                            break;
                        case 1:
                            maxScore = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println(maxScore);
                            break;
                        case 2:
                            avWordsNum = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println(avWordsNum);
                            break;
                        case 3:
                            avScore = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println(avScore);
                            break;
                        case 4:
                            totScore = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println(totScore);
                            break;
                        case 5:
                            totWordsFound = Integer.parseInt(elements[elements.length - 1]);
                            System.out.println(totWordsFound);
                            break;
                    }
                }
                fin.close();
            }catch(IOException | NumberFormatException e){
                System.out.println(e);
            }
        }
    }*/
    
    /**
     * Ottiene il numero di partite effettuate
     * @return numero di partite effettuate 
     */
    public int getGamesNum() {
        return gamesNum;
    }

    /**
     * Aggiorna il numero di partite effettuate
     * @param gamesNum nuovo numero di partite effettuate
     */
    public void setGamesNum(int gamesNum) {
        this.gamesNum = gamesNum;
    }
    
    /**
     * Ottiene il punteggio massimo
     * @return punteggio max
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Aggiorna il punteggio max
     * @param maxScore nuovo punteggio max
     */
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * Ottiene numero medio parole trovate
     * @return parole trovate medie
     */
    public int getAvWordsNum() {
        return avWordsNum;
    }

    /**
     * Aggiorna numero medio parole trovate
     * @param avWordsNum nuovo numero medio di parole trovate
     */
    public void setAvWordsNum(int avWordsNum) {
        this.avWordsNum = avWordsNum;
    }

    /**
     * Ottiene punteggio medio
     * @return punteggio medio
     */
    public int getAvScore() {
        return avScore;
    }
    
    /**
     * Aggiorna punteggio medio
     * @param avScore nuovo punteggio medio
     */
    public void setAvScore(int avScore) {
        this.avScore = avScore;
    }

    /**
     * Ottiene punti totali accumulati
     * @return punti totali accumulati
     */
    public int getTotScore() {
        return totScore;
    }

    /**
     * Aggiorna punti totali
     * @param totScore nuovo numero di punti totali
     */
    public void setTotScore(int totScore) {
        this.totScore = totScore;
    }

    /**
     * Ottiene numero totale di parole trovate
     * @return parole trovate totali
     */
    public int getTotWordsFound() {
        return totWordsFound;
    }

    /**
     * Aggiorna totale di parole trovate
     * @param totWordsFound nuovo numero di parole trovate in totale
     */
    public void setTotWordsFound(int totWordsFound) {
        this.totWordsFound = totWordsFound;
    }
    
        /**
     * Aggiorna le statistiche, scrivendole sul file corrispondente
     * @param maxScore punteggio massimo
     * @param avScore punteggio medio
     * @param avWordsNum numero medio di parole trovate
     * @throws IOException 
     */
    public void updateStatistics(int maxScore, int avScore, int avWordsNum) throws IOException {
        // TextFile fout = new TextFile(STATS_FILE, 'W');
        setMaxScore(maxScore);
        setAvScore(avScore);
        setAvWordsNum(avWordsNum);
        writeStatsToFile(STATS_FILE, tostring());
        /*
            System.out.println(stats.tostring());
        for(int i = 0; i < 6; i++){
            switch(i){
                case 0:
                    fout.toFile("Number of games=" + stats.getGamesNum() + "\n");
                    break;
                case 1:
                    fout.toFile("Maximum score=" + stats.getMaxScore());
                    break;
                case 2:
                    fout.toFile("Average score=" + stats.getAvScore());
                    break;
                case 3:
                    fout.toFile("Average number of words found=" + stats.getAvWordsNum());
                    break;
                case 4:
                    fout.toFile("Total score=" + stats.getTotScore());
                    break;
                case 5:
                    fout.toFile("Total words found=" + stats.getTotWordsFound());
                    break;
            }
        }
         */
        //fout.close();
    }
    
    /**
     * Reperisce una stringa contenente tutte le statistiche
     * @return stringa che mostra le statistiche
     */
    public String tostring() {
        return "Number of games=" + gamesNum + "\n" + "Maximum score=" + maxScore +
                "\nAverage number of words found=" + avWordsNum +
                "\nAverage score=" + avScore +"\nTotal score=" + totScore +
                "\nTotal words found=" + totWordsFound;
    }
    
    /**
     * Carica statistiche da file
     * @param input_file nome del file di origine
     * @param stat oggetto di tipo Statistics da inizializzare
     */
    public static void readStatsFromFile(String input_file, Statistics stat) {
        // stringa per leggere ogni riga del file
        String line;
        String startLine;
        // Array finale da stampare su file
        String[] elements;
        String[] startElements;
        // output arraylist
        try {
            TextFile fin = new TextFile(STATS_FILE, 'r');
            // file input
            //File fin = new File(getInput_file());
            // oggetto reader con codifica dei caratteri UTF-16
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin), ENCODING));
            for(int i = 0; i < 6; i++){
                while ((startLine = fin.fromFile()) != null) {
                        System.out.println(startLine);
                        // startElements = startLine.split("\n");
                            elements = startLine.split("=");
                            switch(i){
                                case 0:
                                    stat.setGamesNum(Integer.parseInt(elements[elements.length - 1]));
                                    System.out.println(stat.getGamesNum());
                                    break;
                                case 1:
                                    stat.setMaxScore(Integer.parseInt(elements[elements.length - 1]));
                                    System.out.println(stat.getMaxScore());
                                    break;
                                case 2:
                                    stat.setAvWordsNum(Integer.parseInt(elements[elements.length - 1]));
                                    System.out.println(stat.getAvWordsNum());
                                    break;
                                case 3:
                                    stat.setAvScore(Integer.parseInt(elements[elements.length - 1]));
                                    System.out.println(stat.getAvScore());
                                    break;
                                case 4:
                                    stat.setTotScore(Integer.parseInt(elements[elements.length - 1]));
                                    System.out.println(stat.getTotScore());
                                    break;
                                case 5:
                                    stat.setTotWordsFound(Integer.parseInt(elements[elements.length - 1]));
                                    System.out.println(stat.getTotWordsFound());
                                    break;
                            }
                        }
                    }
                fin.close();
            //reader.close();
        } catch (IOException e) {
            System.out.println("Eccezione lettura file:" + e);
        }
    }
    
    /**
     * Aggiorna le statistiche, scrivendole su file
     * @param output_file nome del file di output
     * @param line testo da scrivere nel file
     */
    public static void writeStatsToFile(String output_file, String line) {
        try {
            // file output
            //FileWriter fout = new FileWriter(output_file);
            // oggetto writer
            // BufferedWriter writer = new BufferedWriter(fout);
            // inserisco i caratteri nell'array e stampo
            System.out.println(line);
            TextFile writer = new TextFile(STATS_FILE, 'W');
            writer.toFile(line);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
