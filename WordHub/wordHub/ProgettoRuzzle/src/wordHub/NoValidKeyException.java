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
public class NoValidKeyException extends Exception {
    /**
     * Descrizione errore generato, mostrato poi all'utente
     */
    private String error;
    
    /**
     * Costruttore per generare l'eccezione
     * @param string descrizione dell'eccezione generata
     */
    public NoValidKeyException(String string) {
        error = string;
    }
    
    /**
     * Ottiene la dfescrizone associata a tale eccezione
     * @return descrizoone eccezione
     */
    public String getError(){
        return error;
    }
}
