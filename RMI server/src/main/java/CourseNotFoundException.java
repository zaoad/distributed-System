/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaoad
 */
public class CourseNotFoundException extends Exception{
    String s;

    public CourseNotFoundException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Course not found " + s ;
    }
    
}
