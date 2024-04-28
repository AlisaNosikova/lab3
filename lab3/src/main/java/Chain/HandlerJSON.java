/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chain;

import Readers.Reactor;
import Readers.ReaderJSON;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class HandlerJSON extends BaseHandler{
    private ReaderJSON reader;
    private ArrayList<Reactor> list;
     @Override
    public ArrayList<Reactor> handle(File file) {
       if (file.getName().matches(".*\\.json")){
           try {
              // reader.ReaderJSON();
               for (Reactor reactor: reader.ReaderJSON()){
                   reactor.setSource("JSON");
                   list.add(reactor);
               }
               return list;
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
