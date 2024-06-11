/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import BDtables.ReactorDB;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Matcher {
    public void match(ArrayList<Reactor> reactorsFromFile,ArrayList<ReactorDB> reactorsDB){
        for (ReactorDB reactorDB: reactorsDB){
            for (Reactor reactor: reactorsFromFile){
                 if (reactorDB.getType().equals(reactor.getClassName())){
                     reactorDB.setBurnup(reactor.getBurnup());
                     reactorDB.setFirst_load( reactor.getFirst_load());
                     
                 }
            }
        }
       
    }
}
