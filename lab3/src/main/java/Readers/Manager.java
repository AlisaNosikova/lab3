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
   private Storage storage;
   private BaseHandler startHandler;

    public Manager() {
        this.storage = new Storage();
        BaseHandler handlerJSON = new BaseHandler(new ReaderJSON());
        BaseHandler handlerXML = new BaseHandler(new ReaderXML());
        BaseHandler handlerYaml= new BaseHandler( new ReaderYaml());
        startHandler = handlerJSON;
        handlerJSON.setNext(handlerXML);
        handlerXML.setNext(handlerYaml);
    }
    public void useChain(File file){
        storage.addReactors( startHandler.handle(file));
    }
    public ArrayList<Reactor> getInfo(){
        return storage.getList();
    }
}
