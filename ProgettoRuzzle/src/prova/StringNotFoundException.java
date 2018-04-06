/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettobello;

/**
 *
 * @author Fabio
 */
public class StringNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    String error;

    public StringNotFoundException() {
        error = null;
    }

    public StringNotFoundException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (error == null)
            return "StringWasNotFound";
        else
            return error;
    }
}
