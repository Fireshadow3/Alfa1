package fiero;
import java.io.*;

public class TextFile {
    private final char mode; // modalità di apertura del file
    BufferedReader reader;   // oggetto importato dal package java.io per la lettura
    BufferedWriter writer;   // oggetto importato dal package java.io per la scrittura
    
    /*
        Il costruttore accetta come parametri il nome del file da aprire(String)
        e la modalità con la quale aprirlo(char; 'w' o 'W' = scrittura; 
        'r' o 'R' = lettura), istanziando ,a seconda della modalità di apertura,
        gli oggetti necessari.
    */
    
    public TextFile(String filename, char mode){
        this.mode = mode;
        
        try{
            if(this.mode == 'W' || this.mode == 'w'){
                writer = new BufferedWriter(new FileWriter(filename));
            }else if(this.mode == 'R' || this.mode == 'r'){
                reader = new BufferedReader(new FileReader(filename));
            }
        }catch(IOException e){
            System.out.println("Modalit� di accesso non accettata,"
                    + " le uniche due alternative sono 'w'( o 'W') per la scrittura "
                    + "e 'r'(o 'R') per la lettura!");
        }
    }
    
    /*
        Il metodo toFile accetta come parametro una stringa, 
        la stessa che verrà poi scritta su file.
    */
    
    public void toFile(String line) throws IOException{
        writer.write(line);
        writer.newLine();
    }
    
    /*
        Il metodo fromFile legge una stringa e la restituisce anche se è nulla;
        la gestione della fine del file è affidata al metodo che chiama questa 
        funzione.
    */
    
    public String fromFile() throws IOException{
        String tmp;
        tmp = reader.readLine();
        return tmp;
    }
    
    /*
        Il metodo close elimina le istanze degli oggetti di lettura o scrittura 
        su file.    
    */
    
    public void close() throws IOException{
        if(this.mode == 'w' || this.mode == 'W'){
            writer.close();
        }else{
            reader.close();
        }
    }
}

