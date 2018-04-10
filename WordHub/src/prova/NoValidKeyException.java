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
public class NoValidKeyException extends Exception {
    private String error;
    
    public NoValidKeyException(String string) {
        error = string;
    }
    
    public String getError(){
        return error;
    }
}
