/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chain;

import Readers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class HandlerXML extends BaseHandler{
    private ReaderXML reader;
    private ArrayList<Reactor> list = new ArrayList<>();
    public HandlerXML(){
        this.reader = new ReaderXML();
    }
    @Override
    public ArrayList<Reactor> handle(File file) {
       if (file.getName().matches(".*\\.xml")){
           try {
                for (Reactor reactor: reader.ReadXML(file)){
                   reactor.setSource("XML");
                   list.add(reactor);
               }
           } catch (IOException ex) {
               Logger.getLogger(HandlerJSON.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else{
           return nextHandler.handle(file);
       }
       return list;
    }

}
