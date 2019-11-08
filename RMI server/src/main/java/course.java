/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaoad
 */
public class course {
    private String id;
    private String name;
    private int capacity;
    

    public course(String id, String name,int capacity) {
        this.id = id;
        this.name = name;
        this.capacity=capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
