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
public class HandlerYaml extends BaseHandler {
    private ReaderYaml reader;
    private ArrayList<Reactor> list= new ArrayList<>();
    public HandlerYaml(){
        this.reader = new ReaderYaml();
    }
    @Override
    public ArrayList<Reactor> handle(File file) {
       if (file.getName().matches(".*\\.yaml")){
           try {
                for (Reactor reactor: reader.ReadYaml(file)){
                   reactor.setSource("YAML");
                   list.add(reactor);
               }
           } catch (IOException ex) {
               Logger.getLogger(HandlerJSON.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else{
           nextHandler.handle(file);
       }
        return list;
    }
   

}
