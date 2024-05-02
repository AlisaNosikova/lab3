/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import Chain.BaseHandler;
import Chain.Handler;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Manager {
   private BaseHandler h1;
   private BaseHandler h2;
   private BaseHandler h3;
   private ReaderJSON rJSON;
   private ReaderXML rXML;
   private ReaderYaml rYaml;
   private Storage storage;

    public Manager() {
        this.storage = new Storage();
    }
    public void startChain(File file){
        rJSON = new ReaderJSON();
        rXML = new ReaderXML();
        rYaml = new ReaderYaml();
        h1 = new BaseHandler(rJSON);
        h2 = new BaseHandler(rXML);
        h3= new BaseHandler(rYaml);
        h1.setNext(h2);
        h2.setNext(h3);
        storage.addReactors(h1.handle(file));
    }
    public ArrayList<Reactor> getInfo(){
        return storage.getList();
    }
}
