/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Storage {
    private ArrayList<Reactor> currentList;
    public Storage(){
        
    }
    public void addReactors(ArrayList<Reactor> reactors){
        currentList = reactors;
    }
    public ArrayList<Reactor> getList(){
        return currentList;
    }
}
