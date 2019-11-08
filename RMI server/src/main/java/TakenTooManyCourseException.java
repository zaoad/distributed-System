/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaoad
 */
public class TakenTooManyCourseException extends Exception {
    String s;

    public TakenTooManyCourseException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "TakenTooManyCourseException{" + "s=" + s + '}';
    }
    
}
