/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import Chain.BaseHandler;
import Chain.Handler;
import Chain.HandlerJSON;
import Chain.HandlerXML;
import Chain.HandlerYaml;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Manager {
   BaseHandler h1;
   BaseHandler h2;
   BaseHandler h3;
   Storage storage = new Storage();
    public void startChain(File file){
        h1 = new HandlerJSON();
        h2 = new HandlerXML();
        h3= new HandlerYaml();
        h1.setNext(h2);
        h2.setNext(h3);
        storage.addReactor(h1.handle(file));
    }
    public ArrayList<Reactor> getInfo(){
        return storage.getList();
    }
}
